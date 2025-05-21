# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:21

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/com.bottlesnake-1.0-SNAPSHOT.jar app.jar

# Expose the port (update if different)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
