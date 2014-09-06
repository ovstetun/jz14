#!/bin/bash

set -eu

sed "s#<PWD>#${PWD}#" nginx-localdocker.tpl > nginx-localdocker.conf
exec nginx -c $PWD/nginx-localdocker.conf
