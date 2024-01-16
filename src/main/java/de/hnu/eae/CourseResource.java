package de.hnu.eae;

import de.hnu.eae.data.Course;
import de.hnu.eae.data.CourseDAO;
import de.hnu.eae.data.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.InputStream;

/**
 * Represents a resource for managing courses.
 */
@javax.ws.rs.Path("/courses")
public class CourseResource {

    @Inject
    private CourseDAO courseDAO;

    /**
     * Creates a new course.
     * 
     * @param course the course to create
     * @return the response indicating the status of the operation
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCourse(Course course) {
        courseDAO.createCourse(course);
        return Response.status(Response.Status.CREATED).entity("Course created successfully").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses() {
        // get all courses from database via entity manager
        User U1 = new User(999, "uname1", "pwd1", "role1", "User_fn", "User_ln",  new Date(10000), "deutsch");
        Set<User> S1 = new HashSet<>();
        S1.add(U1);

        Course A1 = new Course("TAM", "TAM Description", "Weeger", "../src/main/java/de/hnu/eae/ai/pdfs/UnderstandingLibertyServer.pdf", S1);
        Course A2 = new Course("TAM2", "TAM Description", "Weeger", "../src/main/java/de/hnu/eae/ai/pdfs/UnderstandingLibertyServer.pdf", S1);
        
        List<Course> L1 = new ArrayList<>();

        L1.add(A1);
        L1.add(A2);
        
        return L1;
    }

    /**
     * Uploads material for a specific course.
     * 
     * @param courseId         the ID of the course
     * @param fileInputStream  the input stream of the file to upload
     * @param fileMetaData     the metadata of the file to upload
     * @return the response indicating the status of the operation
     */
    @POST
    @javax.ws.rs.Path("/{courseId}/upload-material")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadMaterial(@PathParam("courseId") Long courseId,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) {
        Course course = courseDAO.findCourse(courseId);
        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Course not found").build();
        }

        try {
            Path uploadDir = Path.of("/path/in/container");
            Path filePath = uploadDir.resolve(fileMetaData.getFileName());
            Files.copy(fileInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            course.setMaterialPath(filePath.toString());
            courseDAO.updateCourse(course);

            return Response.ok("File uploaded successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error uploading file").build();
        }
    }
}
