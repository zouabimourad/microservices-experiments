package com.typesafe.service.order.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderDetail {

    String productCode;
    int count;
    double price;
    double totalPrice;

}
