# FF4j Sample Series

This sample uses the `spring-boot-starter` and define some extra beans to:
- Create a `ff4j` bean full in-memory
- Create the web console bean and expose it to proper url `/ff4j-web-console`
- Secure the web console with a user and password.

To run the app :
```
mvn spring-boot:run
```

The application should be running on [http://localhost:5001](http://localhost:5001).

Setup is fully control with the `application.yaml` file:

```yaml
ff4j:
  webconsole:
    enable: true
    url: /ff4j-web-console
    secure: true
    username: admin
    password: ff4j

```

Cheers.