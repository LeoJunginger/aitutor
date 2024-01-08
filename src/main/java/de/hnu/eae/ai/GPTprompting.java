package de.hnu.eae.ai;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

// Nera, 07.01.2024: Added HTTP Posting for GPT integration.

public class GPTprompting {

    public static void main(String[] args) {
        // Create an instance of GPTprompting
        GPTprompting gptPrompting = new GPTprompting();

        // Path to document
        String filePath = "src/test/java/de/hnu/eae/UnderstandingLibertyServer.pdf"; // Adjust path after data base implementation
        String question = "How does Liberty Server's dynamic loading and modular design benefit developers in cloud-native and Java application environments?";
        // Call the method on the instance
        String answer = gptPrompting.processDocumentAndAskQuestion(filePath, question);
        
        System.err.println(answer);
    }

    public String processDocumentAndAskQuestion(String filePath, String question){
        // OpenAI API key
        String apiKey = "YOUR_API_KEY"; // Add API Key
        
        // Create instances of document processor and tokenizer
        DocumentProcessor docProcessor = new DocumentProcessor();
        TextTokenizer tokenizer = new TextTokenizer();

        // Process document and tokenize its content
        String documentContent = docProcessor.processDocument(filePath);
        String [] tokens = tokenizer.tokenize(documentContent);

        // Concatenate String tokens to request
        StringBuilder contentBuilder = new StringBuilder();
        for (String token : tokens) {
            contentBuilder.append(token).append(" ");
        }
        String tokenizedContent = contentBuilder.toString();

        //Combine document content with question
        int maxContentLength = 3000; // Adjust as needed, max length input + output 4096
        if (tokenizedContent.length() > maxContentLength) {
            tokenizedContent = tokenizedContent.substring(0,maxContentLength);
        }
        String combinedInput = tokenizedContent + "\n\nQuestion: " + question;
        System.err.println(combinedInput); // Delete later

        // Make the API request and print out the response
        return askChatGPT(combinedInput, apiKey);
    }

    public String askChatGPT(String documentContent, String apiKey) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // URL for the ChatGPT endpoint
            String chatGPTUrl = "https://api.openai.com/v1/engines/text-davinci-003/completions";

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
    }}


/* Call GPTprompting via REST API */
