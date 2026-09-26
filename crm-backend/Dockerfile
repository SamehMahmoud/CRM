FROM eclipse-temurin:17-jdk
LABEL authors="sameh"
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/crm-backend-0.0.1-SNAPSHOT.jar"]