
#### Minimal Docker

📗 1. Create the `Dockerfile`:
```bash
FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

📗 2. Build the image
```
docker build . -t ff4j/sample-springboot:mini
```

📗 3. Run the image
```
docker run -p 8080:8080 ff4j/sample-springboot:mini
```

📗 4. Call the layer
```
docker history ff4j/sample-springboot:mini
```

Output
```
IMAGE               CREATED             CREATED BY                                      SIZE                COMMENT
7c229fbdfff9        13 minutes ago      ENTRYPOINT ["java" "-jar" "/app.jar"]           0B                  buildkit.dockerfile.v0
<missing>           13 minutes ago      COPY target/*.jar app.jar # buildkit            40.7MB              buildkit.dockerfile.v0
<missing>           13 minutes ago      ARG JAR_FILE=target/*.jar                       0B                  buildkit.dockerfile.v0
<missing>           13 minutes ago      EXPOSE map[8080/tcp:{}]                         0B                  buildkit.dockerfile.v0
<missing>           10 days ago         /bin/sh -c #(nop)  CMD ["jshell"]               0B                  
<missing>           10 days ago         /bin/sh -c #(nop)  ENV JAVA_HOME=/opt/java/o…   0B                  
<missing>           10 days ago         /bin/sh -c set -eux;     apk add --no-cache …   325MB               
<missing>           10 days ago         /bin/sh -c #(nop)  ENV JAVA_VERSION=jdk-11.0…   0B                  
<missing>           10 days ago         /bin/sh -c apk add --no-cache tzdata --virtu…   14.2MB              
<missing>           10 days ago         /bin/sh -c #(nop)  ENV LANG=en_US.UTF-8 LANG…   0B                  
<missing>           2 weeks ago         /bin/sh -c #(nop)  CMD ["/bin/sh"]              0B                  
<missing>           2 weeks ago         /bin/sh -c #(nop) ADD file:f17f65714f703db90…   5.57MB 
```

#### Layered Jar

[post](https://blog.tratif.com/2020/05/20/spring-tips-2-layered-jars-with-spring-boot-2-3-0/)
[slides](https://docs.google.com/presentation/d/1BjzY4j3g2OrsBq4k0-We-3Chu4O4ZxRvUNOGj2Y8xxU/mobilepresent?slide=id.g899d98b7ad_0_58)

Edit pom
```xml
<plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
	            <configuration>
	     		 <layers>
                  <enabled>true</enabled>
                 </layers>
                </configuration>
            </plugin>
```

First of all, we need to create a layered JAR. By default, Spring Boot splits the data in four layers:
- dependencies – includes all non snapshot dependency JARs
- spring-boot-loader – includes loader for JARs and classes
- snapshot-dependencies – includes all snapshot dependency JARs
- application – includes application code and resource files

📗 1. Create the `Dockerfile`:
```bash
TBD
```

📗 2. Build the image
```
docker build . -t ff4j/sample-springboot:layers
```

📗 3. Run the image
```
docker run -p 8080:8080 ff4j/sample-springboot:layers
```

https://docs.docker.com/develop/develop-images/dockerfile_best-practices/
https://cloud.google.com/solutions/best-practices-for-building-containers

Dockerize
https://github.com/jwilder/dockerize




   