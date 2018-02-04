package com.typesafe.service.order.controller;

import lombok.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailRequest {

    String productCode;
    int count;
}
