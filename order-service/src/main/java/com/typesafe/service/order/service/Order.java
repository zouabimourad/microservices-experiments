package com.typesafe.service.order.service;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Order {

    String reference;
    String accountName;
    LocalDateTime timestamp;

    @NonNull
    List<OrderDetail> details;

    double total;
}
