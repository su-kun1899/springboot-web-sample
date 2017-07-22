#!/usr/bin/env bash

set -eu

readonly ENV_PORT=${PORT}

# jdbc:postgres://<username>:<password>@<hostname>:<port>/<path>
readonly POSTGRES_DATABASE_URL="jdbc:${DATABASE_URL}"

# -> <username>:<password>@<hostname>:<port>/<path>
POSTGRES_USER_INFO=${POSTGRES_DATABASE_URL##postgres://}
# -> <username>:<password>
POSTGRES_USER_INFO=${POSTGRES_USER_INFO%%@*}

readonly POSTGRES_USER=`echo ${POSTGRES_USER_INFO} | cut -d ':' -f 1`
readonly POSTGRES_PASSWORD=`echo ${POSTGRES_USER_INFO} | cut -d ':' -f 2`

java -jar target/springboot-web-sample-0.0.1-SNAPSHOT.jar \
    --server.port=${ENV_PORT} \
    --spring.profiles.active=heroku \
    --spring.datasource.url=${POSTGRES_DATABASE_URL} \
    --spring.datasource.username=${POSTGRES_USER} \
    --spring.datasource.password=${POSTGRES_PASSWORD}
