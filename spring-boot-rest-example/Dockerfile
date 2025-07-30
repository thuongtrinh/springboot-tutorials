
FROM openjdk:8-jre

MAINTAINER TXT

USER root

ENV ROOT_DIR /app

ENV FUll_APP_NAME spring-boot-rest-example*.jar
ENV APP_NAME spring-boot-rest-example.jar
ENV APP_HOME ${ROOT_DIR}/${APP_NAME}

ENV APP_CONFIG_DIR ${ROOT_DIR}/config
ENV APP_LOG_DIR ${ROOT_DIR}/config

COPY ./target/${FUll_APP_NAME} ${APP_HOME}

COPY entrypoint.bash ./entrypoint.sh
RUN chmod +x ./entrypoint.sh

ENTRYPOINT ["/bin/sh", "-c", "./entrypoint.sh"]
