FROM openjdk:17-ea-3-jdk-slim

WORKDIR /api

COPY target/ERP.Legacy.API-0.0.1-SNAPSHOT.jar /api/erp-application-api.jar

EXPOSE 8080

CMD ["java","-jar","erp-application-api.jar"]