# ============================
# Etapa 1: Build con Maven
# ============================
FROM maven:3.9-eclipse-temurin-21 AS build

# Configurar encoding explícitamente
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

WORKDIR /app

# Copiamos solo el POM primero para cachear dependencias
COPY pom.xml .

# Descargamos dependencias
RUN mvn dependency:go-offline -B

# Copiamos el código fuente
COPY src ./src

# Compilar con encoding explícito
RUN mvn clean package -DskipTests -Dmaven.resources.encoding=UTF-8 -Dfile.encoding=UTF-8

# ============================
# Etapa 2: Imagen para producción
# ============================
FROM eclipse-temurin:21-jre-jammy AS runtime

# Configurar encoding también en runtime
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

WORKDIR /app

# Instalar dependencias del sistema y curl para healthcheck
RUN apt-get update && apt-get install -y \
    fontconfig \
    libfreetype6 \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Crear usuario no-root para seguridad
RUN groupadd -r spring && useradd -r -g spring spring
USER spring

# Copiamos el JAR
COPY --from=build --chown=spring:spring /app/target/*.jar app.jar

EXPOSE 8080

# Health check para Docker Compose
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
