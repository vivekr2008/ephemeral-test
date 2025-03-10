# Use the official OpenJDK base image with a specific version (JDK 17, for example)
FROM openjdk:21-jdk-slim AS builder

# Set the working directory in the container
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the local pom.xml and the source code into the container
COPY pom.xml .

# Download the dependencies (for faster builds in case the source code hasn't changed)
RUN mvn dependency:go-offline -B

# Copy the rest of the application code
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use the openjdk image to create the runtime image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the application will run on (default 8080 for Spring Boot)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
