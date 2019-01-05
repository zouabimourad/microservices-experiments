package com.typesafe.service.product;

import com.typesafe.common.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(Product.builder().code("iphone6").name("iPhone 6").price(600.0).build());
        products.add(Product.builder().code("iphone7").name("iPhone 7").price(700.0).build());
        products.add(Product.builder().code("iphone8").name("iPhone 8").price(800.0).build());
        products.add(Product.builder().code("iphonex").name("iPhone x").price(1000.0).build());
    }

    public Optional<Product> findByCode(String code) {
        log.info("findByCode({})", code);
        return products.stream().filter(p -> p.getCode().equals(code)).findFirst();
    }

    public List<Product> findAll() {
        log.info("findAll()");
        return products;
    }
}
