#!/usr/bin/env bash

set -eu

java -jar target/springboot-web-sample-0.0.1-SNAPSHOT.jar --server.port=${PORT}