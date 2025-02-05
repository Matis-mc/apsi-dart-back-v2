#!/bin/bash

docker compose down

rm ./target/apsidart-*

./mvnw clean package

docker build --no-cache -f src/main/docker/Dockerfile.jvm -t quarkus/denis-back-jvm .
docker compose up -d