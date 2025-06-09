# Imagen base con JDK 21
FROM openjdk:21-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todo el contenido del proyecto al contenedor
COPY src ./src
COPY lib ./lib
COPY rmi.policy .

# Compilar los archivos Java con Lombok y SQLite en el classpath
RUN find src -name "*.java" > sources.txt && \
    javac -cp "lib/lombok.jar:lib/sqlite-jdbc-3.42.0.0.jar" -d . @sources.txt

# Exponer el puerto RMI
EXPOSE 2099

# Ejecutar el servidor con ambos .jar en el classpath
CMD ["java", "-Djava.security.policy=rmi.policy", "-cp", ".:lib/lombok.jar:lib/sqlite-jdbc-3.42.0.0.jar", "RMI.Servidor.Test.Test"]