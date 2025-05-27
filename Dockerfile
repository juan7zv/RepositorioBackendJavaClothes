FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Configurar encoding en Maven
ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

# Compilar con configuración explícita
RUN mvn clean package -DskipTests \
    -Dfile.encoding=UTF-8 \
    -Dproject.build.sourceEncoding=UTF-8 \
    -Dproject.reporting.outputEncoding=UTF-8

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]