FROM java:8
MAINTAINER 15270673102@163.com

ADD target/*.jar /app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
