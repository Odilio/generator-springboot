# Spring Hexagonal Project
A Yeoman generator for generating Microservices with SpringBoot in Hexagonal Architecture

## How to use?

```
> npm install -g yo
> npm install -g generator-springboot
> yo springboot
```

## Features

* SpringBoot REST API with jar type packaging
* CORS configuration
* Swagger UI Integration
* Spring Data JPA integration with option to select databases like MySQL, Postgresql, MariaDB etc
* RabbitMQ
* Flyway or Liquibase data migration support
* SpringBoot Actuator configuration
* TestContainers integration
* JUnit 5 
* Docker configuration for Application, ELK, Prometheus, Grafana
* Localstack configuration
* GitHub Actions Configuration

### Generate SpringBoot Microservice

![Microservice Generation](docs/server-generation-1.png)
![Microservice Generation](docs/server-generation-2.png)

### Generate REST API with CRUD operations
You can generate REST API with CRUD operation using the following command:

:high_brightness: You should run the following command from within the generated project folder. 

`myservice> yo springboot-hexagonal:controller Customer --base-path /customers`

This will generate:
* JPA entity
* Spring Data JPA Repository
* Service
* Spring MVC REST Controller with CRUD operations
* Unit and Integration Tests for REST Controller
* Flyway or Liquibase migration to create table

![CRUD Generation](docs/crud-generation.png)

# Other commands

`myservice> yo springboot-hexagonal:rabbitmq Customer --queue-name customers`

`myservice> yo springboot-hexagonal:kubernetes --image-name customers`

## Local Development Setup

```
> git clone https://github.com/odilio/generator-springboot.git
> cd generator-springboot
> npm install 
> npm link
> yo springboot-hexagonal
```

