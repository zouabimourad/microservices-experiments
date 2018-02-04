package com.typesafe.service.order.service;

import com.google.common.collect.Lists;
import com.typesafe.common.Account;
import com.typesafe.common.Product;
import com.typesafe.service.order.common.AccountNotFoundException;
import com.typesafe.service.order.common.ProductNotFoundException;
import com.typesafe.service.order.controller.OrderDetailRequest;
import com.typesafe.service.order.controller.OrderRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {


    @MockBean
    RestTemplate restTemplate;

    @SpyBean
    OrderServiceDelegate orderServiceDelegate;

    @Autowired
    OrderService orderService;

    @Test
    public void test() throws AccountNotFoundException, ProductNotFoundException {

        Account account = Account.builder().firstName("mourad").lastName("zouabi").identifier("mouradz").build();
        ResponseEntity<Account> response = ResponseEntity.ok(account);
        when(restTemplate.getForEntity(eq("http://account-service/{identifier}")  ,eq(Account.class) , anyString())).thenReturn(response);

        Product product = Product.builder().code("iphone6").price(600.0).build();
        ResponseEntity<Product> responseP = ResponseEntity.ok(product);
        when(restTemplate.getForEntity(eq("http://product-service/{identifier}")  ,eq(Product.class) , eq("iphone6"))).thenReturn(responseP);

        product = Product.builder().code("iphonex").price(1000.0).build();
        responseP = ResponseEntity.ok(product);
        when(restTemplate.getForEntity(eq("http://product-service/{identifier}")  ,eq(Product.class) , eq("iphonex"))).thenReturn(responseP);

        OrderRequest orderRequest = OrderRequest.builder()
                .accountIdentifier("mouradz")
                .details(Lists.newArrayList(
                        OrderDetailRequest.builder().count(3).productCode("iphone6").build(),
                        OrderDetailRequest.builder().count(2).productCode("iphonex").build()))
                .build();


        orderService.process(orderRequest);

        Assertions.assertThat(orderService.orders).hasSize(1)
                .flatExtracting(Order::getDetails)
                .hasSize(2).extracting(OrderDetail::getProductCode).containsExactly("iphone6","iphonex");

        Assertions.assertThat(orderService.orders.get(0).total).isEqualTo(3800);

    }

}