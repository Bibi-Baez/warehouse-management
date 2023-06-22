FROM openjdk:17
COPY ./target/warehouse-management-0.0.1-SNAPSHOT.jar warehouse-management.jar
ENTRYPOINT ["java", "-jar", "/warehouse-management.jar"]