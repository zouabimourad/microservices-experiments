# microservices-experiments

## Description
Example project with Spring Boot, Zuul, Eureka, Ribbon, Hystrix, Sleuth and Zipkin 

## Build
`gradle clean build buildDocker`

## Run
`docker-compose up`


**Eureka GUI** : http://localhost:8761

**Zipkin GUI** : http://localhost:9411

## Test

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"accountIdentifier" : "mouradz", "details" : [{"productCode" : "iphone6","count" : 2},{"productCode" : "iphonex","count" : 2}]}' \
  http://localhost:8080/rest/order
```


