FROM openjdk:17

COPY target/dual-*.jar dual.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/dual.jar"]