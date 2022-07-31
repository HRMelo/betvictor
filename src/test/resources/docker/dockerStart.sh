#!/bin/bash

docker ps --filter "name=websocket-service" --format "{{.ID}}" | xargs -I {} docker rm -f {}
TMPDIR=$HOME/tmp docker-compose --project-name websocket-service -f src/test/resources/docker/docker-compose.yml up --build -d --force-recreate
export RABBIT_MQ=`docker port rabbitmq 5672 | cut -d ":" -f 2`
sleep 5
printf "MYSQL=$RABBIT_MQ\n