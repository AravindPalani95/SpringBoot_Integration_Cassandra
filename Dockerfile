FROM openjdk:8-jdk-alpine
COPY target/HackerStory.jar hackerstory.jar
ENTRYPOINT ["java","-jar","hackerstory.jar"]