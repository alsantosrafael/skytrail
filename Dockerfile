FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar app.jar
ENTRYPOINT exec java -jar app.jar