# Usa uma imagem Java leve
FROM eclipse-temurin:17-jdk-alpine

# Diretório temporário
VOLUME /tmp

# Copia o .jar gerado pelo Gradle
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Comando de execução
ENTRYPOINT ["java","-jar","/app.jar"]
