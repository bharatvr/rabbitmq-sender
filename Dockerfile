FROM openjdk:8-jdk-alpine

RUN apk update && apk add bash

RUN  apk add curl

VOLUME /tmp
COPY target/rabbitmq-sender-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# to run docker image locally : docker run -p 8081:8081 rabbitmq-producer:1.0.0