package com.typesafe.common;

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
