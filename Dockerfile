FROM openjdk:21-slim
MAINTAINER daniel.dieguez
COPY target/Atletas-0.0.1-SNAPSHOT.jar Atletas-1.0.0.jar
ENTRYPOINT ["java","-jar","/Atletas-1.0.0.jar"]