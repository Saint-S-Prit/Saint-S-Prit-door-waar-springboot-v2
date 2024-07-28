FROM openjdk:17-jdk-alpine
#APP
ARG SQL_PLATFORM=${SQL_PLATFORM}
ARG DB_DATASOURCE=${DB_DATASOURCE}
ARG DB_USER=${DB_USER}
ARG DB_PASSWORD=${DB_PASSWORD}
ARG DB_DDL_AUTO=${DB_DDL_AUTO}
ARG DB_DDL_GENERATE=${DB_DDL_GENERATE}
ARG DB_DRIVER_NAME=${DB_DRIVER_NAME}
ARG SERVER_PORT=${SERVER_PORT}
ARG CONTEXT_PATH=${CONTEXT_PATH}
ARG CONTEXT_PATH=${CONTEXT_PATH}
ARG FILE_UPLOAD=${FILE_UPLOAD}




RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir -p /app
RUN mkdir -p /app/stockage
RUN chown -R spring:spring  /app/
USER root
COPY ./ /app/
WORKDIR /app
RUN chmod +x mvnw
RUN ./mvnw -q clean package -DskipTests
USER spring:spring
ENTRYPOINT ["java","-jar","target/worker-express.jar"]
