# microservices-experiments

## Description
Exemple project with Spring Boot, Zuul, Eureka and Ribbon 

## Build
`gradle clean build buildDocker`

## Run
`docker-compose up`

## Test
`http://localhost:8080/rest/sum?a=1&b=3`

`http://localhost:8080/rest/minus?a=6&b=3`


If you call one url multiple times you can see the execution log in the differents service instances