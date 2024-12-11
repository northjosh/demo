FROM openjdk:17-alpine

LABEL  maintainer=northjosh


COPY target/demo-0.0.1-SNAPSHOT.jar ./app/

WORKDIR ./app

ENTRYPOINT ["java", "-jar", "demo-0.01-SNAPSHOT.jar"]