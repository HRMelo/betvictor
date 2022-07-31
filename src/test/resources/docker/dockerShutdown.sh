#!/bin/bash

docker ps --filter "name=websocket" --format "{{.ID}}" | xargs -I {} docker rm -f {}