FROM openjdk:17-ea-3-jdk-slim

WORKDIR /api

ARG JAR_FILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS =${ADDITIONAL_OPTS}

COPY target/${JAR_FILE} /api/erp-application-api.jar
COPY wait-for-it.sh /wait-for-it.sh

SHELL ["/bin/sh","-c"]

RUN chmod +x /wait-for-it.sh

EXPOSE 8080
EXPOSE 5005

CMD java ${ADDITIONAL_OPTS} - jar erp-application-api.jar --spring.profiles.acitve=${PROFILE}