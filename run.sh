#!/bin/bash

SERVICE="query-clients"

if [ "$1" == "" ]; then
    docker run --rm -p 8082:8082 -dit --name $SERVICE sbonacho/$SERVICE
else
    docker stop $SERVICE
fi