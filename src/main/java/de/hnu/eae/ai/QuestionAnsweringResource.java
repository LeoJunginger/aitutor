package de.hnu.eae.ai;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ai")
public class QuestionAnsweringResource {

    @Inject
    private DocumentProcessor documentProcessor;

    @Inject
    private TextTokenizer textTokenizer;

    @Inject
    private GPT3Service gpt3Service;

    @POST
    @Path("/process-and-ask")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response processDocumentAndAskQuestion(String filePath, String question) {
        String documentText = documentProcessor.processDocument(filePath);
        if (documentText == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to process document.")
                           .build();
        }

        String[] tokens = textTokenizer.tokenize(documentText);
        // Pass these tokens to the AI service or use them in another way

        String answer = gpt3Service.askQuestion(question);

        return Response.ok(answer).build();
    }
}
