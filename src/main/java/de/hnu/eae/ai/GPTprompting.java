package de.hnu.eae.ai;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.List;


// Nera, 07.01.2024: Added HTTP Posting for GPT integration.
// Nera, 10.01.2024: Changed DocumentReading to multiple files

public class GPTprompting {
    // Class-level instances of DocumentProcessor and TextTokenizer
    private DocumentProcessor docProcessor;
    private TextTokenizer tokenizer;

    // Constructor to initialize the DocumentProcessor and TextTokenizer instances
    public GPTprompting() {
        this.docProcessor = new DocumentProcessor();
        this.tokenizer = new TextTokenizer();
    }
    
    public String processDocumentsAndAskQuestion(List<String> filePaths, String question){
        // OpenAI API key
        String apiKey = "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"; // Add API Key
        
        // StringBuilder to accumulate the content from all documents
        StringBuilder contentBuilder = new StringBuilder();

        // Loop through each file path in the list
        for (String filePath : filePaths) {
            // Process each document to extract its content
            String documentContent = docProcessor.processDocument(filePath);
            // Tokenize the extracted content
            String[] tokens = tokenizer.tokenize(documentContent);

            // Append each token to the contentBuilder
            for (String token : tokens) {
                contentBuilder.append(token).append(" ");
            }
        }

        // Truncate the combined content to a maximum length
        String combinedContent = truncateContent(contentBuilder.toString(), 3500); // max length
        // Combine the truncated content with the user's question
        String combinedInput = combinedContent + "\n\nQuestion: " + question;

        // Make the API request and print out the response
        return askChatGPT(combinedInput, apiKey);
    }

    // Helper method to truncate content to a specific maximum length
    private String truncateContent(String content, int maxLength) {
        if (content.length() > maxLength) {
            return content.substring(0, maxLength);
        }
        return content;
    }

   public String askChatGPT(String combinedInput, String apiKey) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // URL for the ChatGPT API endpoint
            String chatGPTUrl = "https://api.openai.com/v1/chat/completions";

            // Setting up the HTTP POST request
            HttpPost request = new HttpPost(chatGPTUrl);
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            // Prepare the JSON payload for the request
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("model", "gpt-3.5-turbo");
            jsonPayload.put("messages", new JSONArray()
                .put(new JSONObject()
                    .put("role", "system")
                    .put("content", "Explain to a student taking a course on the topic. Base your answer on the material provided, if provided."))
                .put(new JSONObject()
                    .put("role", "user")
                    .put("content", combinedInput)));

            // Attach the JSON payload to the request
            StringEntity requestEntity = new StringEntity(jsonPayload.toString());
            request.setEntity(requestEntity);

            /// Execute the request
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // Extract the response content
            String responseString = EntityUtils.toString(response.getEntity());

            // Parse the response string to JSON
            JSONObject jsonResponse = new JSONObject(responseString);

            // Navigate through the JSON structure to extract the content
            JSONArray choices = jsonResponse.getJSONArray("choices");
            if (choices.length() > 0) {
                JSONObject firstChoice = choices.getJSONObject(0);
                JSONObject message = firstChoice.getJSONObject("message");
                String content = message.getString("content");

                return content;  // Return the extracted content
            } else {
                return "No response received from the API.";
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}


/* Call GPTprompting via REST API */
