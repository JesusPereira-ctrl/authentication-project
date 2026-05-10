FROM dhi.io/maven:3-jdk25-debian13-dev AS builder

WORKDIR /app

COPY ./src ./src

COPY pom.xml .

RUN mvn clean package -DskipTests

FROM amazoncorretto:25-alpine3.20

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

CMD [ "java", "-jar", "app.jar" ]