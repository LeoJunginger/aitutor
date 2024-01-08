package de.hnu.eae.ai;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

// Nera, 07.01.2024: Can be deleted; HTTP Post for prompting included in GPTprompting.java

@ApplicationScoped
public class GPT3Service {
    private OpenAiService service;

    @Inject
    public GPT3Service() {
        // Initialize OpenAiService with API key
        // Retrieve API key from a secure location, such as environment variables
        this.service = new OpenAiService(System.getenv("OPENAI_API_KEY"));
    }

    public String askQuestion(String prompt) {
        try {
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .prompt(prompt)
                    .model("text-davinci-003")
                    .maxTokens(100)
                    .build();

            return service.createCompletion(completionRequest)
                          .getChoices()
                          .stream()
                          .findFirst()
                          .map(choice -> choice.getText().trim())
                          .orElse("No response generated");
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in generating response";
        }
    }
}
