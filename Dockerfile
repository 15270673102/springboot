FROM java:8
MAINTAINER 15270673102@163.com

VOLUME /tmp
COPY target/*.jar /app.jar
RUN bash -c "touch /app.jar"

ENTRYPOINT ["java","-jar","app.jar"]
