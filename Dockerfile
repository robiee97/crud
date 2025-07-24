# Use official OpenJDK image
FROM openjdk:17-jdk-slim
# Create app directory
WORKDIR /app
# Copy the jar file (change name to match your jar)
COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar
# Expose the default Spring Boot port
EXPOSE 8080
# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
