# microservices-experiments

## Description
Example project with Spring Boot, Zuul, Eureka, Ribbon, Hystrix, Sleuth and Zipkin 

## Build
`./gradlew clean build buildDocker`

## Run
`docker-compose up`


**Eureka Console** : http://localhost:8761

**Zipkin Console** : http://localhost:9411

**Hystrix Dashboard Console (monitoring order-service hystrix stream)** : http://localhost:9500/hystrix/monitor?stream=http%3A%2F%2Forder-service%3A8083%2Factuator%2Fhystrix.stream

## Test

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"accountIdentifier" : "mouradz", "details" : [{"productCode" : "iphone6","count" : 2},{"productCode" : "iphonex","count" : 2}]}' \
  http://localhost:8080/rest/order
```


