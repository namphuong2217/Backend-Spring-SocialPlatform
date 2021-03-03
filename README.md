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

## Testing API - Demo Client with [Insomnia](https://insomnia.rest/)

* API Request Collection

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/000%20API%20Collection.png)

* Register a new user

![image](https://github.com/namphuong2217/Social-Blogging/blob/main/documentation/001%20Register.png)

* Global exception to handle invalidation [ExceptionHandler with ``@ControllerAdvice``](https://github.com/namphuong2217/Social-Blogging-Platform/tree/main/src/main/java/com/personalproject/socialbloggingplatform/exception)

Mail has already been registered

![image](https://github.com/namphuong2217/Social-Blogging-Platform/blob/main/documentation/001%20Not%20unique%20mail.png)

Username is empty

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
* Adding new user,

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
Send mail from our application

#### Swagger (unfinished)
Build API documentation

## Running the project

[Refer to API documentation for further insight. ](https://backend-reddit-heroku.herokuapp.com/swagger-ui.html).

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
