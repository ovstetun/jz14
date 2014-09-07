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
            add_header 'Access-Control-Allow-Origin' "$http_origin";
            add_header 'Access-Control-Allow-Credentials' 'true';
        }
        location /api/ingredients/ {
            proxy_pass http://localdocker:8080/;
            add_header 'Access-Control-Allow-Origin' "$http_origin";
            add_header 'Access-Control-Allow-Credentials' 'true';
        }

        location /search {
            proxy_pass http://localdocker:9200/;
            add_header 'Access-Control-Allow-Origin' "$http_origin";
            add_header 'Access-Control-Allow-Credentials' 'true';
        }
        location /search/ {
            proxy_pass http://localdocker:9200/;
            add_header 'Access-Control-Allow-Origin' "$http_origin";
            add_header 'Access-Control-Allow-Credentials' 'true';
        }

    }
}
