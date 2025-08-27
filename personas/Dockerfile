# Usar JDK 17 (puede ser 11 si querés)
FROM eclipse-temurin:17-jdk-alpine as build

# Copiar el jar generado
COPY target/personas-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8081

# Correr la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
