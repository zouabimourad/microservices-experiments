# microservices-experiments

## Description
Exemple project with Spring Boot, Zuul, Eureka, Ribbon, Hystrix and Zipkin 

## Build
`gradle clean build buildDocker`

## Run
`docker-compose up`


**Eureka GUI** : http://localhost:8761

**Zipkin GUI** : http://localhost:9411

## Test

```
POST http://localhost:8080/rest/order
Content-Type: application/json

{
    "accountIdentifier" : "mouradz",
    "details" : [
        {
           "productCode" : "iphone6",
           "count" : 2
        },
        {
            "productCode" : "iphonex",
            "count" : 2
        }
    ]
}
```




