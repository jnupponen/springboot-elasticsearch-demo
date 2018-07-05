# Builder image
FROM maven:3.5-jdk-8 as builder

WORKDIR /app
ADD pom.xml .
RUN ["mvn", "verify", "clean", "--fail-never"]

COPY . .
RUN mvn clean package

# Runner image
FROM openjdk:8-jdk-alpine

RUN apk --no-cache add --update ca-certificates

WORKDIR /app

COPY --from=builder /app/target/*.jar ./app.jar

EXPOSE 8080
CMD java -jar app.jar