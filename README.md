# Match-API
A Spring Boot example demonstrating how to implement simple CRUD operations with ``Match`` and ``Match Odds`` entities.

## Technologies
Project is created with:
* Java version: 1.8
* Spring Boot: 2.6.2
* PostgreSQL: 14.1
* Maven: 3.6.3


## Installation
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration
Create a PostegreSQL database and add the credentials to ``/resources/application.properties``. 

The default ones are :

```
spring.datasource.url= jdbc:postgresql://localhost:5432/
spring.datasource.username= postgres
spring.datasource.password= root
server.port = 8090
```
