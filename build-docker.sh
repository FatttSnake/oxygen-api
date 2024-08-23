#!/bin/bash

JAR_NAME=`ls target | grep api-|grep -v original`
JAR_VERSION=${JAR_NAME%.*}
JAR_VERSION=${JAR_VERSION#*-}
BUILD_TIME=$(date "+%Y%m%d%H%M%S")

mkdir target/extracted
java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

if [[ "${JAR_VERSION}" =~ ^.*SNAPSHOT$ ]]
then
  docker build -t ${DOCKER_HUB_URL}/oxygen-api:snapshot-latest -t ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION -t ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION-${BUILD_TIME} .
  cat "${KEYS_PATH}/docker.password" | docker login ${DOCKER_HUB_URL} -u jenkins --password-stdin
  docker push ${DOCKER_HUB_URL}/oxygen-api:snapshot-latest
  docker push ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION
  docker push ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION-${BUILD_TIME}
else
  docker build -t ${DOCKER_HUB_URL}/oxygen-api:latest -t ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION -t ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION-${BUILD_TIME} .
  cat "${KEYS_PATH}/docker.password" | docker login ${DOCKER_HUB_URL} -u jenkins --password-stdin
  docker push ${DOCKER_HUB_URL}/oxygen-api:latest
  docker push ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION
  docker push ${DOCKER_HUB_URL}/oxygen-api:$JAR_VERSION-${BUILD_TIME}
fi