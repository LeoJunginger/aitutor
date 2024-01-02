# Use an Open Liberty base image with Java 11
FROM openliberty/open-liberty:full-java11-openj9-ubi

# Copy the Liberty server configuration files (assuming they are in the 'src/main/liberty/config' directory)
COPY --chown=1001:0 src/main/liberty/config /config/

# Copy the built WAR file from the Maven target folder to the Liberty deployment directory
COPY --chown=1001:0 target/aitutor.war /config/apps/

# Set the default HTTP and HTTPS ports for Liberty
EXPOSE 9080 9443

# Run the Liberty server script
RUN configure.sh