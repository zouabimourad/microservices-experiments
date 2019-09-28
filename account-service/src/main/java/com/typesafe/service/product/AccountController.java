package com.typesafe.service.product;

import com.typesafe.common.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{identifier}")
    public @ResponseBody
    ResponseEntity<Account> product(@PathVariable String identifier) {
        return accountService.findByIdentifier(identifier)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List<Account>> products() {
        return ResponseEntity.ok(accountService.findAll());
    }
}
