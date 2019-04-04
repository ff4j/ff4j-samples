FROM adoptopenjdk/openjdk11:latest

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/ff4j-sample-springboot2-1.8.jar

# Add the application's jar to the container
ADD ${JAR_FILE} ff4j-sample-springboot2-1.8.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ff4j-sample-springboot2-1.8.jar"]
