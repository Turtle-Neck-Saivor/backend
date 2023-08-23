# 빌드 단계
FROM gradle:7.6.1-jdk17-focal AS build

ENV BACKEND_LOCAL_HOME=./backend

ENV BACKEND_APP_HOME=/apps

WORKDIR $BACKEND_APP_HOME

COPY $BACKEND_LOCAL_HOME/build.gradle $BACKEND_LOCAL_HOME/settings.gradle $BACK_HOME/gradlew $BACKEND_APP_HOME

COPY $BACKEND_LOCAL_HOME/gradle $BACKEND_APP_HOME/gradle

RUN chmod +x gradlew

RUN ./gradlew build || return 0

COPY $BACKEND_LOCAL_HOME/src $BACKEND_APP_HOME/src

RUN ./gradlew clean build

FROM openjdk:17-ea-jdk-buster

ENV BACKEND_APP_HOME=/apps
ARG ARTIFACT_NAME=app.jar
ARG JAR_FILE_PATH=build/libs/backend-0.0.1-SNAPSHOT.jar

WORKDIR $BACKEND_APP_HOME

COPY --from=build $BACKEND_APP_HOME/$JAR_FILE_PATH $ARTIFACT_NAME

EXPOSE 8080

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait
CMD /wait && java -jar app.jar
