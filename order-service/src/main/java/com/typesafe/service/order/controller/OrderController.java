package com.typesafe.service.order.controller;

import com.typesafe.service.order.common.AccountNotFoundException;
import com.typesafe.service.order.common.ProductNotFoundException;
import com.typesafe.service.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;


    @PostMapping()
    public @ResponseBody
    ResponseEntity<Void> product(@RequestBody OrderRequest orderRequest) throws AccountNotFoundException, ProductNotFoundException {
        orderService.process(orderRequest);
        return ResponseEntity.ok().build();
    }
}
