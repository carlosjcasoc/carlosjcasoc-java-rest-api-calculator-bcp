FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/app-1.0.0.jar"]