FROM openjdk:22-jdk
VOLUME /tmp
ADD target/holidays-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]