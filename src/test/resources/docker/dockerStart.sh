#!/bin/bash

docker ps --filter "name=websocket" --format "{{.ID}}" | xargs -I {} docker rm -f {}
TMPDIR=$HOME/tmp docker-compose --project-name websocket -f src/test/resources/docker/docker-compose.yml up --build -d --force-recreate
export MYSQL=`docker port mysql 3306 | cut -d ":" -f 2`
sleep 5
printf "MYSQL=$MYSQL\n"