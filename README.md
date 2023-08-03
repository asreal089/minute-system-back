# Project to track and record votes of associates in a given minute

## Tech used:
* Java 17 using spring boot 3.1.2
* Flyway for creating a managing tables
* Postgresql for database.
* Swagger for API documentation
* JUnity and Mockito for unity testing.

## How to get started:

* Create your `application.properties` file following the `application.properties-sample`, mapping your own DB. 
* Build and run using following maven commands:
````
mvn clean install
mvn spring-boot:run
````

## How it works

There a few endpoints implemented in this API:

![API - Swagger](https://github.com/asreal089/minute-system-back/blob/main/swagger.png?raw=true)

* Use the endpoint `api/v1/minute` end to create a minute, also that would a time frame for voting, the default value is 1 minute, but it can be set by adding the field `duration`.

* Vote using the endpoint `api/v1/minute/{idMinute}/vote` while the "session" is still available.

* Get result in the endpoint `api/v1/minute/{idMinute}/result`