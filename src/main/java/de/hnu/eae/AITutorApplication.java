package de.hnu.eae;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class AITutorApplication extends ResourceConfig {
    public AITutorApplication() {
        packages("de.hnu.eae");
        register(MultiPartFeature.class); // Register MultiPartFeature
    }
}
