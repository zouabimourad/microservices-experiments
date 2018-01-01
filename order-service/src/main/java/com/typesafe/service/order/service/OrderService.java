package com.typesafe.service.order.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.typesafe.service.order.common.AccountNotFoundException;
import com.typesafe.service.order.controller.OrderDetailRequest;
import com.typesafe.service.order.controller.OrderRequest;
import com.typesafe.service.order.common.ProductNotFoundException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
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
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    private final static Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderServiceDelegate orderServiceDelegate;

    List<Order> orders = new ArrayList<>();


    public void process(OrderRequest orderRequest) throws AccountNotFoundException, ProductNotFoundException {

        Map<String, Object> account = orderServiceDelegate.getAccountDetails(orderRequest.getAccountIdentifier()).orElseThrow(() -> new AccountNotFoundException());

        Order order = new Order();

        order.setAccountName(account.get("firstName") + " " + account.get("lastName"));

        for (OrderDetailRequest orderDetailRequest : orderRequest.getDetails()) {
            Map<String, Object> product = orderServiceDelegate.getProductDetails(orderDetailRequest.getProductCode()).orElseThrow(() -> new ProductNotFoundException());


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


}
