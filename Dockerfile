FROM java:8
MAINTAINER 15270673102@163.com

VOLUME /tmp
COPY target/springboot-0.0.1-SNAPSHOT.jar /springboot.jar
RUN bash -c "touch /springboot.jar"
ENTRYPOINT ["java","-jar","springboot.jar"]
