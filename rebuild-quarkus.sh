#!/bin/bash

docker compose down

rm ./target/apsi-dart-*

./mvnw clean package

docker build --no-cache -f src/main/docker/Dockerfile.jvm -t quarkus/apsidart-backend-jvm .
docker compose up -d