FROM openjdk:21

COPY target/*.jar inventory-service.jar
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]