#!/bin/bash

docker compose down

rm -rf ./target/*

./mvnw clean package

docker build --no-cache -f src/main/docker/Dockerfile.jvm -t quarkus/denis-back .
docker compose up -d