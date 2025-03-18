#!/bin/bash

docker compose down

rm -rf ./target/*

./mvnw package -Dnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true

docker build -f src/main/docker/Dockerfile.native-micro -t quarkus/denis-back .

docker compose up -d