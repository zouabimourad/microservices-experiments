package com.typesafe.service.order.controller;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    String accountIdentifier;
    List<OrderDetailRequest> details;


}
