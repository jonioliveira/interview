#!/bin/bash

cd users-api
chmod +x mvnw

./mvnw clean package

cd ..

docker-compose -f docker-compose-dev.yml up -d

cd users-api

docker build -f src/main/docker/Dockerfile.jvm -t interview-users .

docker run -d -p 8080:8080 interview-users