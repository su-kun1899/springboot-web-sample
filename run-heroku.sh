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

export SERVER_PORT=${ENV_PORT}
export SPRING_PROFILES_ACTIVE=heroku
export SPRING_DATASOURCE_URL=${JDBC_URL}
export SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
export SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}

exit 0
