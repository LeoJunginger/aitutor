package de.hnu.eae;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hnu.eae.ai.GPTprompting;
import de.hnu.eae.data.CourseDAO;
import de.hnu.eae.data.Course;

import java.util.Arrays;

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
    public Response askCourse(GPTRequest request) {
        GPTprompting gptPrompting = new GPTprompting();

        // Fetch course details from the database
        Course course = courseDAO.findCoursebyName(request.getCourseName());
        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Course not found").build();
        }

        // Get the material path from the course
        String materialPath = course.getMaterialPath();

        // Call GPTprompting to process the documents and get a response from GPT
        String gptResponse = gptPrompting.processDocumentsAndAskQuestion(Arrays.asList(materialPath), request.getQuestion());

        // Return the response from GPT API to the client
        return Response.ok(gptResponse).build();
    }


    public static class GPTRequest {
        private String courseName;
        private String question;
        // add more fields if needed
    
        // Getter for courseName
        public String getCourseName() {
            return courseName;
        }
    
        // Setter for courseName
        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    
        // Getter for question
        public String getQuestion() {
            return question;
        }
    
        // Setter for question
        public void setQuestion(String question) {
            this.question = question;
        }
    
        // Add getters and setters for other fields as needed
    }
    
}
