FROM gradle:9.0.0-jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon


FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT exec java -jar app.jar