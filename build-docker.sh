#!/bin/bash

JAR_NAME=`ls target | grep oxygen-api- | grep -vE "original|asc|pom|javadoc"`
JAR_VERSION=${JAR_NAME%.*}
JAR_VERSION=${JAR_VERSION#*-}
JAR_VERSION=${JAR_VERSION#*-}
BUILD_TIME=$(date "+%Y%m%d%H%M%S")

echo ${BUILD_TIME} > .build_time

mkdir target/extracted
java -Djarmode=layertools -jar target/${JAR_NAME} extract --destination target/extracted

if [[ "${JAR_VERSION}" =~ ^.*SNAPSHOT$ ]]
then
  docker build -t ${DOCKER_HUB_URL}/oxygen-api:snapshot-latest -t ${DOCKER_HUB_URL}/oxygen-api:${JAR_VERSION} -t ${DOCKER_HUB_URL}/oxygen-api:${JAR_VERSION}-${BUILD_TIME} .
else
  docker build -t ${DOCKER_HUB_URL}/oxygen-api:latest -t ${DOCKER_HUB_URL}/oxygen-api:${JAR_VERSION} -t ${DOCKER_HUB_URL}/oxygen-api:${JAR_VERSION}-${BUILD_TIME} .
fi