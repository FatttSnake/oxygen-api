#!/bin/bash

JAR_NAME=`ls target | grep api-|grep -v original`
JAR_VERSION=${JAR_NAME%.*}
JAR_VERSION=${JAR_VERSION#*-}

mkdir target/extracted
java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted
docker build -t hub.fatweb.top/fatweb-api:latest -t hub.fatweb.top/fatweb-api:$JAR_VERSION -t hub.fatweb.top/fatweb-api:$JAR_VERSION-$(date "+%Y%m%d%H%M%S") .