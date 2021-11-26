FROM openjdk:8-jdk-alpine

COPY ../../../target/library-app-1.0-SNAPSHOT.jar library-app-1.0-SNAPSHOT.jar
EXPOSE 7777

ENTRYPOINT ["java", "-jar", "/library-app-1.0-SNAPSHOT.jar"]