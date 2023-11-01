FROM openjdk:17-jdk-alpine as builder 
ARG MSCV_USUARIOS=msvc-uusario 

WORKDIR /app/$msvc-uusario

COPY ./pom.xml /app
COPY ./$msvc-uusario/.mvn ./.mvn
COPY ./$msvc-uusario/mvnw .
COPY ./$msvc-uusario/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip=true -Dmaven.main.skip -Dspring-boot.repackage.skip && -rf ./target/
#RUN ./mvnw dependecy:go-offline

COPY ./msvc-uusario/src ./src

RUN ./mvnw clean package -DskipTests 

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
ARG MSCV_USUARIOS=msvc-uusario
COPY --from=builder /app/$MSCV_USUARIOS/target/msvc-uusario-0.0.1-SNAPSHOT.jar .
ENV PORT 8001
EXPOSE ${PORT}


ENTRYPOINT [ "java", "-jar", "./target/msvc-uusario-0.0.1-SNAPSHOT.jar" ]




