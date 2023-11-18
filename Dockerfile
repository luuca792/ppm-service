FROM openjdk:17

COPY ./ppm-main/target/ppm-main-0.2.0.jar /app/ppm-main-0.2.0.jar

WORKDIR /app/

EXPOSE 8081
EXPOSE 5005

CMD [ "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/ppm-main-0.2.0.jar" ]