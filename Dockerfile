FROM openjdk:17

EXPOSE 8080

COPY target/dual-0.0.1-SNAPSHOT.jar dual.jar

ENTRYPOINT ["java", "-jar", "/dual.jar"]