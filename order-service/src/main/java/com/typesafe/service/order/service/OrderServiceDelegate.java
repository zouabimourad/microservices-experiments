package com.typesafe.service.order.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.typesafe.common.Account;
import com.typesafe.common.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OrderServiceDelegate {

    final RestTemplate restTemplate;

    Cache<String, Product> productCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build();

    @Autowired
    public OrderServiceDelegate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @HystrixCommand(fallbackMethod = "getProductDetailsFromLocalCache")
    public Optional<Product> getProductDetails(String code) {

        ResponseEntity<Product> productEntity = restTemplate.getForEntity("http://product-service/{identifier}", Product.class, code);
        if (productEntity.getStatusCode().is2xxSuccessful()) {
            if (productCache.getIfPresent(code) == null) {
                productCache.put(code, productEntity.getBody());
            }
            return Optional.ofNullable(productEntity.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Product> getProductDetailsFromLocalCache(String code) {
        log.info("getProductDetailsFromLocalCache()");
        return Optional.ofNullable(productCache.getIfPresent(code));

    }

    public Optional<Account> getAccountDetails(String identifier) {
        ResponseEntity<Account> accountEntity = restTemplate.getForEntity("http://account-service/{identifier}", Account.class, identifier);
        if (accountEntity.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(accountEntity.getBody());
        } else {
            return Optional.empty();
        }
    }

}
