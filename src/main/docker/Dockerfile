FROM java:8
VOLUME /tmp
COPY ./springboot-0.0.1-SNAPSHOT.jar /springboot.jar
RUN bash -c "touch /springboot.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","springboot.jar"]

# docker run -d -p 18080:8080 --name docker-resource leesia/resource:1.0
