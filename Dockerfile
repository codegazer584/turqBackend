FROM gradle:jdk14 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM adoptopenjdk/openjdk14:jre-14.0.2_12-debian
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar
WORKDIR /app

ENTRYPOINT ["java","-jar","/app/app.jar"]