#!/usr/bin/env bash

set -eu

# postgres://<username>:<password>@<hostname>:<port>/<path>
readonly POSTGRES_DATABASE_URL=${DATABASE_URL}

# -> <username>:<password>@<hostname>:<port>/<path>
POSTGRES_USER_INFO=${POSTGRES_DATABASE_URL##postgres://}
# -> <username>:<password>
POSTGRES_USER_INFO=${POSTGRES_USER_INFO%%@*}

echo ${POSTGRES_USER_INFO}

readonly POSTGRES_USER=`echo ${POSTGRES_USER_INFO} | cut -d ':' -f 1`
readonly POSTGRES_PASSWORD=`echo ${POSTGRES_USER_INFO} | cut -d ':' -f 1`

echo ${POSTGRES_USER}
echo ${POSTGRES_PASSWORD}

java -jar target/springboot-web-sample-0.0.1-SNAPSHOT.jar --server.port=${PORT}