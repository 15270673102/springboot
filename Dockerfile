FROM java:8
MAINTAINER 15270673102@163.com

COPY target/*.jar /app.jar
RUN bash -c "touch /app.jar"

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
