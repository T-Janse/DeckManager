# Stage 1: Build
FROM gradle:8.4-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle* gradle.properties* /app/
COPY src /app/src

RUN gradle build --no-daemon -x test
RUN gradle generateJavaDoc

# Stage 2: Run
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
