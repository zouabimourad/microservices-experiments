package com.typesafe.service.order.service;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    String reference;
    String accountName;
    LocalDateTime timestamp;

    List<OrderDetail> details = new ArrayList<>();

    double total = 0.0;
}
