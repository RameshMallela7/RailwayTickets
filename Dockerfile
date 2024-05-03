FROM openjdk:17
EXPOSE 9099
ADD target/railway_ticket.jar railway_ticket.jar
ENTRYPOINT ["java","-jar","/railway_ticket.jar"]