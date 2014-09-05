FROM nginx

ADD html/bin /usr/local/nginx/html

ADD nginx.conf /etc/nginx.conf
ADD start-nginx.sh /usr/local/bin/start-nginx.sh

CMD ["start-nginx.sh"]
