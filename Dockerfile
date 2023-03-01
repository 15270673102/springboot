FROM registry.cn-hangzhou.aliyuncs.com/wangjiayu/jdk:v1.0
MAINTAINER wangjiayu

COPY target/*.jar app.jar
RUN sh -c "touch /app.jar"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
