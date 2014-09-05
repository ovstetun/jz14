#!/bin/bash

set -eu

/bin/sed -i "s/SERVICE_HOST/${SERVICE_HOST}/" /etc/nginx.conf
exec nginx
