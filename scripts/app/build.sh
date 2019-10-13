#!/bin/sh

# Build App Container
cp ../../ai-web/target/ai-web-1.0.jar app
# TODO create an application.yml specific for the container
docker build -t ai-app:1.0 --build-arg JAR_FILE=ai-web-1.0.jar .
