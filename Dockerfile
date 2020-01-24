FROM maven:alpine

COPY /target /app

WORKDIR /app

ENTRYPOINT ["java","-jar","./project-2-backend-0.0.1-SNAPSHOT.jar"]