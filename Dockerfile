# ---------- STAGE 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# copy pom and download dependencies first (better caching)
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# copy source code
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# ---------- STAGE 2: Run ----------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]