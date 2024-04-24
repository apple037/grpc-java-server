FROM openjdk:22-jdk
ADD target/GrpcServer-0.0.1-SNAPSHOT.jar java-server.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "java-server.jar"]