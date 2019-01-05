package com.typesafe.service.product;

import com.typesafe.common.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AccountService {

    private List<Account> accounts = new ArrayList<>();

    @PostConstruct
    public void init() {
        accounts.add(Account.builder().identifier("mouradz").firstName("Mourad").lastName("ZOUABI").build());
    }

    public Optional<Account> findByIdentifier(String identifier) {
        log.info("findByIdentifer({})", identifier);
        return accounts.stream().filter(p -> p.getIdentifier().equals(identifier)).findFirst();
    }

    public List<Account> findAll() {
        log.info("findAll()");
        return accounts;
    }
}
