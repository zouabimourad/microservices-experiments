package com.typesafe.service.product;

import com.typesafe.common.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{code}")
    public @ResponseBody
    ResponseEntity<Product> getByCode(@PathVariable String code) {
        return productService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
