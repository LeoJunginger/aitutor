package de.hnu.eae;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hnu.eae.ai.GPTprompting;
import de.hnu.eae.data.CourseDAO;
import de.hnu.eae.data.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// REST API for GPT request, necessary to allign with separation of concerns
// Client request (Frontend) - HTTP request GPTpromptingWS endpoint -> 
// Encapsulate request (question, course) into GPTRequest object
// GPTRequest object used to call GPTprompting
// REsponse from GPTpromüting via GPTpromptingWS API to client


@Stateless
@Path("/gptprompting")
public class GPTpromptingWS {

    @Inject
    private CourseDAO courseDAO;

    @POST
    @Path("/ask")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response askCourse(String request) {

        Jsonb jsonb = JsonbBuilder.create();

        GPTRequest gptrequest = jsonb.fromJson(request, GPTRequest.class);

        GPTprompting gptprompting = new GPTprompting();

        List<String> materialPath = new ArrayList<>();
        materialPath.add(gptrequest.getFilePaths());


        // Call GPTprompting to process the documents and get a response from GPT
        String gptResponse = gptprompting.processDocumentsAndAskQuestion(materialPath, gptrequest.getQuestion());

        // Return the response from GPT API to the client
        return Response.ok(gptResponse).build();
    }


    public static class GPTRequest {
        private String question;
        private String filePaths;
    
        

        

        public String getFilePaths() {
            return filePaths;
        }

        public void setFilePaths(String filePaths) {
            this.filePaths = filePaths;
        }

        public GPTRequest() {
            super();
        }

        // Getter for question
        public String getQuestion() {
            return question;
        }
    
        // Setter for question
        public void setQuestion(String question) {
            this.question = question;
        }
    


        @Override
        public String toString() {
            return "GPTRequest [question=" + question + ", filePaths=" + filePaths + "]";
        }
    }
    
}
