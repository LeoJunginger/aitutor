package de.hnu.eae;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("tutor")
public class TutorWS {

    @GET
    public String sayHello() {
        return "Hello, world";
    }
    
}
