# FF4j Sample Series

This sample uses the `spring-boot-starter` and define some extra beans to:
- Create a `ff4j` bean full in-memory
- Create the web console bean and expose it to proper url `/ff4j-web-console`
- Create REST API expose it to proper url `/api/ff4j`

To run the app :
```
mvn spring-boot:run
```

The application is now running on `http://localhost:8080`


This sample could also be run as a demo as such it can be deployed as a docker image
```yaml
mvn clean package dockerfile:build
```

Once the image is built you can run it with
```
docker run -p 8080:8080 ff4j/ff4j-sample-springboot2x:1.8.5
```


Cheers.