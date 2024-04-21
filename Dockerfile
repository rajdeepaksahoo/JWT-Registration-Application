FROM ubuntu:latest
LABEL authors="rajdeepaksahoo"
COPY target/Registration-Application-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]