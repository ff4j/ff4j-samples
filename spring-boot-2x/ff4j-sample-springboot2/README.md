# FF4j Spring Boot sample #


## Running the example
You can start this sample Spring Boot 2 application with
```
mvn spring-boot:run
```

## Docker

To run this in the docker container just do the following from inside the project directory
```
mvn package
docker build --tag=ff4jdemo .
docker run -p 8080:8080 -d ff4jdemo
```

## Using the example
When running the example you can access it by going to [http://localhost:8080](http://localhost:8080)

There is a REST endpoint [/message](http://localhost:8080/message) that will return a simple message property from application.properties. It will add a new feature flag called "Friends". If you enable turn on that feature flag with the web console and then hit the [/message](http://localhost:8080/message) endpoint again it will display a different message.

### Java Version
Note that this runs under both Java 8 and OpenJDK 11.
