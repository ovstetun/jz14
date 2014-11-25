#!/bin/bash

set -eu

/bin/sed -i "s/INGREDIENTS_PORT_8080_TCP_ADDR/${INGREDIENTS_PORT_8080_TCP_ADDR}/" /etc/nginx.conf
/bin/sed -i "s/INGREDIENTS_PORT_8080_TCP_PORT/${INGREDIENTS_PORT_8080_TCP_PORT}/" /etc/nginx.conf
/bin/sed -i "s/ELASTICHTTP_PORT_9200_TCP_ADDR/${ELASTICHTTP_PORT_9200_TCP_ADDR}/" /etc/nginx.conf
/bin/sed -i "s/ELASTICHTTP_PORT_9200_TCP_PORT/${ELASTICHTTP_PORT_9200_TCP_PORT}/" /etc/nginx.conf

exec nginx
