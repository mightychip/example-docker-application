FROM openjdk:8-alpine
MAINTAINER Mighty Chip <mighty.chip@gmail.com>
VOLUME /tmp
EXPOSE 8080 8081
ADD target/data-service-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar"]