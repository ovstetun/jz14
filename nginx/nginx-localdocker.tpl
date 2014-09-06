daemon off;

worker_processes 1;

events { worker_connections 1024; }

http {

    sendfile on;

    include mime.types;

    server {

        listen 3000;

        proxy_http_version 1.1;

        location / {
            root   <PWD>/html/build;
            index  index.html index.htm;
        }

        location /api/ingredients {
            proxy_pass http://localdocker:8080/;
        }
        location /api/ingredients/ {
            proxy_pass http://localdocker:8080/;
        }

        location /search {
            proxy_pass http://localdocker:9200/;
        }
        location /search/ {
            proxy_pass http://localdocker:9200/;
        }

    }
}