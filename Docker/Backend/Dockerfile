FROM openjdk:17-slim

LABEL author="XJTLU CPT202 GROUP 25"
LABEL url="CPT202.website"
LABEL version="A.0.2"
LABEL release-date="2024-03-9"

ADD Backend_APP.jar /CPT202/app.jar
ADD config.properties /CPT202/config.properties

EXPOSE 80
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/CPT202/app.jar"]
