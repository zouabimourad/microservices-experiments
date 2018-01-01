package com.typesafe.service.order.service;

import com.typesafe.service.order.common.AccountNotFoundException;
import com.typesafe.service.order.controller.OrderDetailRequest;
import com.typesafe.service.order.controller.OrderRequest;
import com.typesafe.service.order.common.ProductNotFoundException;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final static Logger LOG = LoggerFactory.getLogger(OrderService.class);

    List<Order> orders = new ArrayList<>();

    @Autowired
    RestTemplate restTemplate;

    public void process(OrderRequest orderRequest) throws AccountNotFoundException, ProductNotFoundException {

        Map<String, Object> account = getAccountDetails(orderRequest.getAccountIdentifier()).orElseThrow(() -> new AccountNotFoundException());

        Order order = new Order();
        order.setTotal(0.0);
        order.setDetails(new ArrayList<>());
        order.setAccountName(account.get("firstName") + " " + account.get("lastName"));

        for (OrderDetailRequest orderDetailRequest : orderRequest.getDetails()) {
            Map<String, Object> product = getProductDetails(orderDetailRequest.getProductCode()).orElseThrow(() -> new ProductNotFoundException());

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductCode(orderDetailRequest.getProductCode());
            double price = (double) product.get("price");
            orderDetail.setPrice(price);
            orderDetail.setCount(orderDetailRequest.getCount());
            double orderDetailTotalPrice = price * orderDetailRequest.getCount();
            orderDetail.setTotalPrice(orderDetailTotalPrice);
            order.getDetails().add(orderDetail);
            order.setTotal(order.getTotal() + orderDetailTotalPrice);
        }

        order.setTimestamp(LocalDateTime.now());
        order.setReference(RandomStringUtils.randomAlphanumeric(20));
        orders.add(order);
        LOG.info("Processed order " + order.toString());
    }

    private Optional<Map<String, Object>> getProductDetails(String code) {
        ResponseEntity<Map> productEntity = restTemplate.getForEntity("http://product-service/{identifier}", Map.class, code);
        if (productEntity.getStatusCode().is2xxSuccessful()) {
            return Optional.of(productEntity.getBody());
        } else {
            return Optional.empty();
        }
    }

    private Optional<Map<String, Object>> getAccountDetails(String identifier) {
        ResponseEntity<Map> accountEntity = restTemplate.getForEntity("http://account-service/{identifier}", Map.class, identifier);
        if (accountEntity.getStatusCode().is2xxSuccessful()) {
            return Optional.of(accountEntity.getBody());
        } else {
            return Optional.empty();
        }
    }
}
