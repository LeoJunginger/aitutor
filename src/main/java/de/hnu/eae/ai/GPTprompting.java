package de.hnu.eae.ai;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

    public static void main(String[] args) {
       // Create an instance of GPTprompting
       GPTprompting gptPrompting = new GPTprompting();

       // List of example file paths (replace with actual files from db after backend implementation)
       List<String> filePaths = Arrays.asList(
           "src/test/java/de/hnu/eae/UnderstandingLibertyServer.pdf",
           "src/test/java/de/hnu/eae/UnderstandingLibertyServer.pdf"
       );

       // Example question
       String question = "How does Liberty Server's dynamic loading works?";

       // Call the method on the instance
       String answer = gptPrompting.processDocumentsAndAskQuestion(filePaths, question);
       
       System.out.println(answer);
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

    public String askChatGPT(String documentContent, String apiKey) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // URL for the ChatGPT endpoint
            String chatGPTUrl = "https://api.openai.com/v1/chat/gpt-3.5-turbo/completions";

            // Setting up the request
            HttpPost request = new HttpPost(chatGPTUrl);
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            // Prepare JSON payload with document content
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("prompt", documentContent);
            jsonPayload.put("max_tokens", 100);  // Adjust token limit as needed; Total tokens available 4096 including input

            // Attach payload to the request
            StringEntity requestEntity = new StringEntity(jsonPayload.toString());
            request.setEntity(requestEntity);

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // Extract the response content
                String responseString = EntityUtils.toString(response.getEntity());
                return responseString;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}


/* Call GPTprompting via REST API */
