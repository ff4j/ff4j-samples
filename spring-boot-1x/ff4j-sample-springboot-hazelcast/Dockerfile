FROM openjdk:8-jre-alpine

COPY target/*.jar /opt/app.jar

CMD java -jar -server /opt/app.jar

EXPOSE 8080