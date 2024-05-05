#FROM openjdk:17-alpine
#WORKDIR /opt
#ENV PORT 8080
#EXPOSE 8080
#COPY target/railway_ticket.jar /opt/railway_ticket.jar
#ENTRYPOINT exec java $JAVA_OPTS -jar railway_ticket.jar


# Stage 1: Build the Java application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean istall -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-alpine

# Copy the built JAR file from the build stage
COPY --from=build /app/target/railway_ticket.jar /opt/railway_ticket.jar

# Expose the port
ENV PORT 8080
EXPOSE 8080

# Add docker-compose binary to the image
RUN apk update && \
    apk add --no-cache docker-compose

# Set the working directory
WORKDIR /opt

# Define the command to run your application
CMD ["docker-compose", "up"] 