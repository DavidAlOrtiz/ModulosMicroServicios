FROM openjdk:17.0.2

WORKDIR /app/msvc-uusario

COPY ./pom.xml /app
COPY ./msvc-uusario/.mvn ./.mvn
COPY ./msvc-uusario/mvnw .
COPY ./msvc-uusario/pom.xml .

RUN ./mvnw package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && -r ./target/

COPY ./msvc-uusario/src ./src

RUN ./mvnw clean package -DskipTests 

EXPOSE 8001


ENTRYPOINT [ "java", "-jar", "./target/msvc-uusario-0.0.1-SNAPSHOT.jar" ]

