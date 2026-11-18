# ---- Build ----
# Java 25 wajib: pom.xml memakai <java.version>25</java.version> (sama dengan Jenkinsfile).
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
COPY src ./src
# -Pproduction = build frontend Vaadin untuk produksi
RUN ./mvnw -B -ntp clean package -Pproduction -DskipTests

# ---- Run ----
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN mkdir -p /app/uploads /app/data
ENV APP_PORT=8080 UPLOAD_DIR=/app/uploads
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
