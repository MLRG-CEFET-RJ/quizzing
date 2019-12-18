#Build
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package

#Deploy
FROM openjdk:8-jdk-alpine
WORKDIR /app
VOLUME /tmp
COPY --from=MAVEN_BUILD /build/target/app-1.0.jar /app/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
