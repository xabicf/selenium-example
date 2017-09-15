FROM nginx:latest

RUN sed -i -e 's/Welcome\ to\ nginx\!/Hello\ Selenium\!/g' /usr/share/nginx/html/index.html
