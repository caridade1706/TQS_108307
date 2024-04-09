# Use OpenJDK 11 as the base image
FROM openjdk:17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build file and download dependencies
COPY . .

# Copy the entire application code
COPY src ./src

# Package the application into a JAR file
RUN ./mvnw package -DskipTests

# Create a new stage for the final image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the final image
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar backend.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Define the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "backend.jar"]
