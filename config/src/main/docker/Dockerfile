FROM java:8-jre-alpine
VOLUME /tmp
ADD config-server.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8888