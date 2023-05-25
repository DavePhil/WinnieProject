FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY backend/.mvn/ .mvn
EXPOSE 8080
COPY backend/mvnw backend/pom.xml ./
RUN ./mvnw dependency:resolve

COPY backend/src ./src

CMD ["./mvnw", "spring-boot:run"]