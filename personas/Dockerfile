FROM maven:3.8.8-eclipse-temurin-11 AS build


WORKDIR /app
COPY pom.xml .
COPY src ./src


RUN mvn clean package -DskipTests


FROM eclipse-temurin:11-jre-alpine


WORKDIR /app
COPY --from=build /app/target/personas-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

