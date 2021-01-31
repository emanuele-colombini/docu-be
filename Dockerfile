FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11-jdk-alpine
COPY --from=build /app/target/app.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
