FROM maven:3.9.11-eclipse-temurin-21-alpine AS builder

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /build/target/*.jar product-service.jar

EXPOSE 8181

ENV SPRING_PROFILES_ACTIVE=docker

CMD [ "java", "-jar", "product-service.jar" ]