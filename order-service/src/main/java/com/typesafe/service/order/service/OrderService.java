package com.typesafe.service.order.service;

import com.typesafe.common.Account;
import com.typesafe.common.Product;
import com.typesafe.service.order.common.AccountNotFoundException;
import com.typesafe.service.order.common.ProductNotFoundException;
import com.typesafe.service.order.controller.OrderDetailRequest;
import com.typesafe.service.order.controller.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    final OrderServiceDelegate orderServiceDelegate;

    List<Order> orders = new ArrayList<>();

    @Autowired
    public OrderService(OrderServiceDelegate orderServiceDelegate) {
        this.orderServiceDelegate = orderServiceDelegate;
    }

    public void process(OrderRequest orderRequest) throws AccountNotFoundException, ProductNotFoundException {

        Account account = orderServiceDelegate.getAccountDetails(orderRequest.getAccountIdentifier()).orElseThrow(AccountNotFoundException::new);

        Order order = new Order();

        order.setAccountName(account.getFirstName() + " " + account.getLastName());

        for (OrderDetailRequest orderDetailRequest : orderRequest.getDetails()) {
            Product  product = orderServiceDelegate.getProductDetails(orderDetailRequest.getProductCode()).orElseThrow(ProductNotFoundException::new);


            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductCode(orderDetailRequest.getProductCode());
            double price = product.getPrice();
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
        log.info("Processed order " + order.toString());
    }


}
