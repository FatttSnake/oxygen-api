FROM eclipse-temurin:25-jre
LABEL authors="FatttSnake"

VOLUME /data

ENV SERVER_PORT=8080

ARG EXTRACTED=target/extracted

COPY ${EXTRACTED}/dependencies/ /
COPY ${EXTRACTED}/spring-boot-loader/ /
COPY ${EXTRACTED}/snapshot-dependencies/ /
RUN true
COPY ${EXTRACTED}/application/ /

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher", "--spring.config.additional-location=file:data/"]

HEALTHCHECK --interval=30s --timeout=3s --start-period=120s --retries=3 \
    CMD wget -qO- "http://localhost:${SERVER_PORT}/health" || exit 1
