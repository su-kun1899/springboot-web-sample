#!/usr/bin/env bash

set -eu

readonly ENV_PORT=${PORT}

# postgres://<username>:<password>@<hostname>:<port>/<path>
readonly ENV_DATABASE_URL=${DATABASE_URL}

# -> <username>:<password>@<hostname>:<port>/<path>
readonly POSTGRES_INFO=${ENV_DATABASE_URL##postgres://}

# -> <hostname>:<port>/<path>
readonly POSTGRES_DB_HOST=`echo ${POSTGRES_INFO} | cut -d '@' -f 2`
readonly JDBC_URL="jdbc:postgresql://${POSTGRES_DB_HOST}"

# -> <username>:<password>
readonly POSTGRES_USER_INFO=${POSTGRES_INFO%%@*}

readonly POSTGRES_USER=`echo ${POSTGRES_USER_INFO} | cut -d ':' -f 1`
readonly POSTGRES_PASSWORD=`echo ${POSTGRES_USER_INFO} | cut -d ':' -f 2`

java -jar target/springboot-web-sample-0.0.1-SNAPSHOT.jar \
    --server.port=${ENV_PORT} \
    --spring.profiles.active=heroku \
    --spring.datasource.url=${JDBC_URL} \
    --spring.datasource.username=${POSTGRES_USER} \
    --spring.datasource.password=${POSTGRES_PASSWORD}
