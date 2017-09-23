FROM openjdk:8-alpine
MAINTAINER Mighty Chip <mighty.chip@gmail.com>
VOLUME /tmp
EXPOSE 8080 8081
#TODO There must be a programmatic way to do this... we have to change this every time we change Versions...
ADD target/data-service-0.0.2-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar"]