FROM eclipse-temurin:17-jdk-alpine
LABEL authors="FatttSnake"

VOLUME /data

ARG EXTRACTED=target/extracted
COPY ${EXTRACTED}/dependencies/ /
COPY ${EXTRACTED}/spring-boot-loader/ /
COPY ${EXTRACTED}/snapshot-dependencies/ /
COPY ${EXTRACTED}/application/ /

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher", "${JAVA_OPTS}"]