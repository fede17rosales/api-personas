FROM eclipse-temurin:11-jdk-alpine AS build

COPY target/personas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
