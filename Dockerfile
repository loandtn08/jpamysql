FROM openjdk:17
MAINTAINER loandang
ARG APP_NAME="jpamysql"
ARG APP_VERSION="0.0.1-SNAPSHOT"
ARG JAR_FILE="/build/libs/${APP_NAME}-${APP_VERSION}.jar"

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

