package com.typesafe.service.order.service;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    String productCode;
    int count;
    double price;
    double totalPrice;

}
