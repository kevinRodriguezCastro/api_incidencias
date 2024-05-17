FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/api_incidencias-0.0.1.jar
COPY ${JAR_FILE} app_incidencias.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app_incidencias.jar"]