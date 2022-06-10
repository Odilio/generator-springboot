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
* Spring Data JPA integration with option to select databases like MySQL, Postgresql, MariaDB etc
* RabbitMQ
* Kubernetes
* Flyway data migration support
* SpringBoot Actuator configuration
* Spring Native / GraalVM
* ReactJS starter
* TestContainers integration
* JUnit 5 
* ArchUnit
* Localstack configuration
* Swagger UI Integration


### Generate REST API with CRUD operations
You can generate REST API with CRUD operation using the following command:

:high_brightness: You should run the following command from within the generated project folder. 

`myservice> yo springboot-hexagonal:hexagonal Customer`

Customer will be the name used in DTOs, Mappers and Models

This will generate:
* JPA entity
* Spring Data JPA Repository
* Service
* Spring MVC REST Controller with CRUD operations
* Unit and Integration Tests for REST Controller
* Flyway migration to create table

# Other commands

`myservice> yo springboot-hexagonal:kubernetes`
see more in https://spark-life.gitbook.io/spring-hexagonal/generators/kubernetes

`myservice> yo springboot-hexagonal:react` 
see more in https://spark-life.gitbook.io/spring-hexagonal/generators/react

`myservice> yo springboot-hexagonal:rabbitmq Customer`
see more in https://spark-life.gitbook.io/spring-hexagonal/generators/rabbitmq

`myservice> yo springboot-hexagonal:security` - in dev

`myservice> yo springboot-hexagonal:kafka` - in dev

`myservice> yo springboot-hexagonal:webclient Person --uri https://testnets-api.opensea.io` - in dev

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

myservice> yo springboot-hexagonal:gateway

myservice> yo springboot-hexagonal:oauth2

myservice> yo springboot-hexagonal:mail

myservice> yo springboot-hexagonal:reactadmin

## Contacts

https://www.linkedin.com/in/odilio-noronha-filho/

https://medium.com/rapaduratech

odilionoronha@gmail.com