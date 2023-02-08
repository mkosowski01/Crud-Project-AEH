FROM openjdk:19-jdk-slim
ADD target/app-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar app-0.0.1-SNAPSHOT.jar