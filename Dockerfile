FROM openjdk:17-alpine
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/railway_ticket.jar /opt/railway_ticket.jar
ENTRYPOINT exec java $JAVA_OPTS -jar railway_ticket.jar


