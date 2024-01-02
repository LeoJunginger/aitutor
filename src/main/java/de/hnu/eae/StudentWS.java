package de.hnu.eae;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import de.hnu.eae.data.Student;


@Stateless
@Path("student")
public class StudentWS {
    @PersistenceContext(unitName = "AITutorDB")
    private EntityManager em;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student createStudent(Student student) {
        em.persist(student);
        return student;
    }

    @GET
    @Path("{matrNr}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("matrNr") long matrNr) {
        return em.find(Student.class,matrNr);
    }

    @DELETE
    @Path("{matrNr}")
    public void deleteStudent(@PathParam("matrNr") long matrNr) {
        em.remove(em.find(Student.class,matrNr));
    }

}
