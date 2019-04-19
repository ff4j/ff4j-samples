# FF4j Spring Boot sample #

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

Afterwards the application will be available under [http://localhost:8080](http://localhost:8080)

Note that this runs under both Java 8 and OpenJDK 11.
