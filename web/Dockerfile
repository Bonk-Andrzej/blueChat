FROM node:8.11.2-alpine as node
EXPOSE 4200
ADD . /home/chat/web
WORKDIR /home/chat/web

#COPY package*.json ./

RUN npm install

#COPY . .

RUN npm run build