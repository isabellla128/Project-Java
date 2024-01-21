FROM openjdk:17-oracle
ADD target/*.jar application.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "application.jar"]