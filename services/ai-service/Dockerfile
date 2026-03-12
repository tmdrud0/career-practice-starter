FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY services services

RUN chmod +x mvnw
RUN ./mvnw -pl services/ai-service -am package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/services/ai-service/target/ai-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
