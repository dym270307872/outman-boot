FROM anapsix/alpine-java:8_server-jre_unlimited
ADD target/boot-docker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]