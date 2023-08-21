# 빌드 단계
FROM gradle:7.6.1-jdk17-focal AS build

ENV BACK_HOME=./backend

ENV APP_HOME=/apps

WORKDIR $APP_HOME

COPY $BACK_HOME/build.gradle settings.gradle gradlew $APP_HOME

COPY $BACK_HOME/gradle $APP_HOME/gradle

RUN chmod +x gradlew

RUN ./gradlew build || return 0

COPY $BACK_HOME/src $APP_HOME/src

RUN ./gradlew clean build

FROM openjdk:17-ea-jdk-buster

ENV APP_HOME=/apps
ARG ARTIFACT_NAME=app.jar
ARG JAR_FILE_PATH=build/libs/backend-0.0.1-SNAPSHOT.jar

WORKDIR $APP_HOME

COPY --from=build $APP_HOME/$JAR_FILE_PATH $ARTIFACT_NAME

EXPOSE 8080

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait
CMD /wait && java -jar app.jar
