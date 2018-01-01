package com.typesafe.service.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private final static Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @GetMapping("/{identifier}")
    public @ResponseBody
    ResponseEntity<Account> product(@PathVariable String identifier) {
        return accountService.findByIdentifier(identifier)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public @ResponseBody
    ResponseEntity<List<Account>> procut() {
        return ResponseEntity.ok(accountService.findAll());
    }
}
