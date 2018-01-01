package com.typesafe.service.order.controller;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OrderRequest {

    String accountIdentifier;
    List<OrderDetailRequest> details;


}
