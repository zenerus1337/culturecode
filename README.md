Project description

## OpenAPI:
### URL: 
https://springdoc.org/

### Usage
The Swagger UI page will then be available at http://server:port/context-path/swagger-ui.html and the OpenAPI description will be available at the following url for json format: http://server:port/context-path/v3/api-docs
 
eg.:
http://localhost:8080//swagger-ui.html

## Preparing application

> mvn clean package

> docker build -t mc-cc-app .

> docker run -p 8080:8080 --name mc-cc-app mc-cc-app
 
## Running application

### You can also use -d to run them in detached mode:

> docker-compose up -d

### Start the application: Start containers that were previously stopped.

> docker-compose start

### Stop the application: Stop running containers without removing them.

> docker-compose stop

### Down the application: Stop and remove containers, networks, volumes, and images created by up.

> docker-compose down
   
### To view the logs of your services, use:

> docker-compose logs

### You can follow the logs by using:

> docker-compose logs -f

### To execute a command inside a running container, use:

> docker-compose exec service_name command

### For example, to open a bash shell in your db service, you could use:

> docker-compose exec db bash

### If your docker-compose.yml uses custom images that need to be built from a Dockerfile, you can use:

> docker-compose up --build

### or you can separately build images without starting containers:

> docker-compose build


