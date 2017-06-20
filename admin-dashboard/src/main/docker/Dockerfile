FROM java:8-jre-alpine
VOLUME /tmp
ADD admin-dashboard.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080