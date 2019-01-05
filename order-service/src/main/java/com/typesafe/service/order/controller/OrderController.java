package com.typesafe.service.order.controller;

import com.typesafe.service.order.common.AccountNotFoundException;
import com.typesafe.service.order.common.ProductNotFoundException;
import com.typesafe.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity<Void> product(@RequestBody OrderRequest orderRequest) throws AccountNotFoundException, ProductNotFoundException {
        orderService.process(orderRequest);
        return ResponseEntity.ok().build();
    }
}
