# Backend Java Spring Social Blogging Platform 

A backend REST services for a social blogging platform. Project built with Spring Framework from scratch.

This codebase was created to demonstrate a fully fledged fullstack application built with Spring boot + JPA including CRUD operations, authentication, and more.

For more information on how this works see below.

## Overview

This project was built with [Spring Boot](https://spring.io/projects/spring-boot).

Environment used:

* Java 11
* Maven 3.6 +
* IntelliJ IDEA

## How it works

The application uses Spring boot (Web, JPA, Postgresql).

* Use the idea of Three-Layer Architecture Design to separate the business term and infrastruture term

The application is built in 3 layer architecture: [``Controller Layer``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/controller), [Service Layer](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/service), [Repository Layer](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/repository)

* Use JPA to implement the Data Mapper pattern for persistence to database.

* Use Data Transfer Object to separate the read model data and write model data.

And the code organize as this:

1. [``Controller Layer``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/controller) is the layer handles coming requests
2. [``Service Layer``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/service) is the business layers
3. [``Repository Layer``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/repository) is the Persistence/ Data Access Layer
4. [``model``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/model), [``dto``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/dto) is the business model including entities and DTO
5. [``security``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/security), [``config``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/config) handle security of API Endpoints
6. [``exception``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/exception) is global custom exception to handle input invalidation 


## Testing API - Demo Client with [Insomnia](https://insomnia.rest/)

* API Request Collection

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/000%20API%20Collection.png)

* Register a new user

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/001%20Register.png)

* Global custom exception to handle invalidation [ExceptionHandler ``@ControllerAdvice``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/exception)

* Mail has already been registered

![image](https://github.com/namphuong2217/Social-Blogging-Platform/blob/main/documentation/001%20Not%20unique%20mail.png)

* Username is empty

![image](https://github.com/namphuong2217/Social-Blogging-Platform/blob/main/documentation/001%20Register%20Request%20name%20empty.png)

* Login of existing user 

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/002%20Login.png)

* Create new community to group/categorize posts

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/003%20Create%20Community.png)

* Query a community

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/003%20Query%20A%20Community.png)

* Query all communities

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/004%20Query%20All%20Communities.png)

* Create new post by current user

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/004%20Create%20a%20post.png)

* Query all posts

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/006%20Query%20All%20Posts.png)

* Create new comment by current user on specified post

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/007%20Create%20A%20Comment.png)

* Query all comments by given username

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/007%20Query%20Comment%20by%20Username.png)

* Downvote or Upvote a post

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/008%20Vote%20A%20Post.png)

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/008%20Vote%20A%20Post%20Query%20Post.png)

* Refresh Token

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/009%20Refresh%20TOken.png)

* Logout and delete Refresh Token

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/009%20Logout%20RefreshToken%20deleted.png)

## Modules, libraries and features

#### Spring Boot Starters
Bootstrap initial application

#### Spring Security
* Security of API Endpoints. Implementation of authentication/ authorization/login/logout with JSON Web Token and Spring Security, 
* Adding new user

#### Spring Data JPA with Postgresql
Persistence to Postgresql database using JPA. More control with data.sql and schema.sql

#### Spring MVC
Dispatch requests to responsible handlers

#### Hibernate
Data persistence and management of entities and tables for application and database

#### CRUD 
Implement CREATE, READ, UPDATE, DELETE operations for RESTful services.

#### JSON Web Token 
Authenticate users

#### Lombok
Manage boiler-plate code in Java

#### MapStruct (unfinished)
Mapping entities to data transfer objects (DTO)

#### Mailtrap (unfinished)
Fake [SMTP server](https://mailtrap.io/) for managing sending/receiving mail for registration

#### Java Mail Sender (unfinished)
Send mail from the application

#### Swagger (unfinished)
Build API documentation

## Running the project

[API documentation](https://backend-reddit-heroku.herokuapp.com/swagger-ui.html)

## Some Demos

* Postgresql database

![SQL](https://github.com/namphuong2217/Social-Blogging-Platform/blob/main/documentation/011%20SQL%20Pgadmin%201.png)

![SQL](https://github.com/namphuong2217/Social-Blogging-Platform/blob/main/documentation/011%20SQL%20Pgadmin.png)

* Data transfer objects

![DTO](https://github.com/namphuong2217/Backend-JavaSpring-Reddit/blob/main/src/main/resources/images/Screenshot%20from%202020-11-13%2009-51-13.png)

* API Endpoints

![Endpoints](https://github.com/namphuong2217/Social-Blogging-Platform/blob/main/documentation/010%20API%20Endpoints.png)

