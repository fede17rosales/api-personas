# Usar JDK 11
FROM eclipse-temurin:11-jdk-alpine AS build

# Copiar el jar generado
COPY target/personas-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8081

# Correr la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
