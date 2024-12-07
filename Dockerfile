# Use a imagem oficial do Java como base
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado para o container
COPY target/springboot-mvc-project-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8081

# Comando para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
