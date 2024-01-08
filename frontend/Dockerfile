# Stage 1
FROM node:21-alpine3.19 AS builder

WORKDIR /frontend
COPY . /frontend
RUN npm install && npm run build

EXPOSE 5173

# # Stage 2
FROM nginx:1.25-alpine

# RUN rm /etc/nginx/conf.d/default.conf
# COPY nginx.conf /etc/nginx/conf.d

WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /frontend/dist .

ENTRYPOINT ["nginx", "-g", "daemon off;"]