# Social-Blogging

A backend REST services for a social blogging platform. Project built with Spring Framework from scratch.

The application is deployed to [Heroku](https://dashboard.heroku.com/).

You can access the API documentation [here](https://backend-reddit-heroku.herokuapp.com/swagger-ui.html). 

## Overview

This project was built with [Spring Boot](https://spring.io/projects/spring-boot).

Environment used:

* Java 11
* Maven 3.7
* IntelliJ IDEA

## Demo API endpoints

* API Request Collection

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/01%20Testing%20Services%20Collection.png)

* UserService GET Request via localhost:9191 ([GatewayService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/gateway-service)): return a service instance ID ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/01%20GET%20User%20ServiceId.png)

* GET a user with id as parameter ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/02%20GET%20User%20Gateway.png)

*  GET a user with id as parameter exception ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))


## Modules, libraries and features

#### Spring Boot Starters
Bootstrap initial application

#### Spring Security
Implementation of register new user, authentication/ authorization/login/logout with JSON Web Token and Spring Security, security of API Endpoints.

#### Spring Data JPA with MySQL
Data access, communication between application and externe database

#### Spring MVC
Dispatch requests to responsible handlers

#### Hibernate
Data persistence and management of entities and tables for application and database

#### CRUD 
Implement CREATE, READ, UPDATE, DELETE operations for RESTful services.

#### Mailtrap 
Fake [SMTP server](https://mailtrap.io/) for managing sending/receiving mail for registration

#### Lombok
Manage boiler-plate code in Java

#### MapStruct
Mapping entities to data transfer objects (DTO)

#### Java Mail Sender
Send mail from our application

#### JSON Web Token 
Authenticate users

#### Swagger
Build API documentation

## Running the project

Frontend pending. Refer to API documentation for further insight.



## API Endpoints Overview

* Controller layer

![Controllers](https://github.com/namphuong2217/Backend-JavaSpring-Reddit/blob/main/src/main/resources/images/Screenshot%20from%202020-11-13%2009-51-04.png)

* Data transfer objects

![DTO](https://github.com/namphuong2217/Backend-JavaSpring-Reddit/blob/main/src/main/resources/images/Screenshot%20from%202020-11-13%2009-51-13.png)

* API Endpoints

![Endpoints](https://github.com/namphuong2217/Backend-JavaSpring-Reddit/blob/main/src/main/resources/images/Screenshot%20from%202020-11-13%2009-51-46.png)

* Signup Endpoint

![Signup](https://github.com/namphuong2217/Backend-JavaSpring-Reddit/blob/main/src/main/resources/images/Screenshot%20from%202020-11-13%2009-52-08.png)

* Retrieve a post Endpoint

![Post](https://github.com/namphuong2217/Backend-JavaSpring-Reddit/blob/main/src/main/resources/images/Screenshot%20from%202020-11-13%2009-52-28.png)
