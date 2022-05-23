# Spring Hexagonal Project
A Yeoman generator for generating Microservices with SpringBoot in Hexagonal Architecture

## How to use?

```
> npm install -g yo
> npm i generator-springboot-hexagonal
> yo springboot-hexagonal
```
to known more look at the doc page 
https://spark-life.gitbook.io/spring-hexagonal/

## Features

* SpringBoot REST API
* CORS configuration
* Swagger UI Integration
* Spring Data JPA integration with option to select databases like MySQL, Postgresql, MariaDB etc
* RabbitMQ
* Kubernetes
* Flyway or Liquibase data migration support
* SpringBoot Actuator configuration
* ReactJS starter
* TestContainers integration
* JUnit 5 
* ArchUnit
* Localstack configuration

### Generate SpringBoot Microservice

![Microservice Generation](docs/server-generation-1.png)
![Microservice Generation](docs/server-generation-2.png)

### Generate REST API with CRUD operations
You can generate REST API with CRUD operation using the following command:

:high_brightness: You should run the following command from within the generated project folder. 

`myservice> yo springboot-hexagonal:hexagonal Customer --base-path /customers`

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

`myservice> yo springboot-hexagonal:kubernetes`
see more in https://spark-life.gitbook.io/spring-hexagonal/generators/kubernetes

`myservice> yo springboot-hexagonal:react` 
see more in https://spark-life.gitbook.io/spring-hexagonal/generators/react

`myservice> yo springboot-hexagonal:security` - in dev

## Local Development Setup

```
> git clone https://github.com/odilio/generator-springboot.git
> cd generator-springboot
> npm install 
> npm link
> yo springboot-hexagonal
```

## Roadmap

myservice> yo springboot-hexagonal:cassandra

myservice> yo springboot-hexagonal:mongodb

myservice> yo springboot-hexagonal:grpc

myservice> yo springboot-hexagonal:mail

myservice> yo springboot-hexagonal:reactadmin

## Contacts

https://www.linkedin.com/in/odilio-noronha-filho/

https://medium.com/rapaduratech

odilionoronha@gmail.com