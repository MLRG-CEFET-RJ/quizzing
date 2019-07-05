FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/quizzing-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
