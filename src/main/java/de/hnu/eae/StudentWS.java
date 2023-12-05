package de.hnu.eae;

import java.util.HashMap;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hnu.eae.data.Student;

@Stateless
@Path("student")
public class StudentWS {
    private HashMap<Long, Student> students =new HashMap<Long, Student>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student createStudent (Student student){
        students.put(student.getMatrNr(), student);
        return student;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(long matrNr){
        return students.get(matrNr);
    }
}
