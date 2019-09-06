FROM gradle:5.4.1-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/flight-planner
WORKDIR /home/gradle/flight-planner
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/flight-planner/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]