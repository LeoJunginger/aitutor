# Use Open Liberty base image with Java 17
FROM openliberty/open-liberty:full-java17-openj9-ubi

# Add Liberty server configuration file
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/

# Add PostgreSQL driver
COPY --chown=1001:0 src/main/liberty/config/lib/postgresql-42.7.1.jar /config/lib/

# Add your application WAR
COPY --chown=1001:0 target/aitutor.war /config/apps/

# Default ports for HTTP and HTTPS
EXPOSE 9080 9443

# Configure server
RUN configure.sh