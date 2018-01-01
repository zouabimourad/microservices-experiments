package com.typesafe.service.product;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private String code;
    private String name;
    private Double price;

}
