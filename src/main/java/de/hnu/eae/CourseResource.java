package de.hnu.eae;

import de.hnu.eae.data.Course;
import de.hnu.eae.data.CourseDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
            Path uploadDir = Path.of("/path/to/upload/directory");
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
