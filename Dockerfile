# Use OpenJDK base image
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the built JAR file
COPY target/com.bottlesnake-1.0-SNAPSHOT.jar app.jar

# Expose the port your Battlesnake listens on (e.g., 8080)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
