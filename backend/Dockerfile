# Stage 1
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /backend

COPY mvnw pom.xml .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Stage 2
FROM eclipse-temurin:21-jdk-alpine

ARG DEPENDENCY=/backend/target/dependency

COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /backend/lib
COPY --from=builder ${DEPENDENCY}/META-INF /backend/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /backend

ENTRYPOINT ["java", "-cp", "backend:backend/lib/*", "com.codecool.Application"]