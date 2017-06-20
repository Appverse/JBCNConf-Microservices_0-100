FROM java:8-jre-alpine
VOLUME /tmp
ADD customers.jar /app.jar 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]