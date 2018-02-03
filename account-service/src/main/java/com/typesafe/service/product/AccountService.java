package com.typesafe.service.product;

import com.typesafe.common.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {

    private final static Logger LOG = LoggerFactory.getLogger(AccountService.class);

    private List<Account> accounts = new ArrayList<>();

    @PostConstruct
    public void init() {
        accounts.add(Account.builder().identifier("mouradz").firstName("Mourad").lastName("ZOUABI").build());
    }

    public Optional<Account> findByIdentifier(String identifier) {
        LOG.info("findByIdentifer({})", identifier);
        return accounts.stream().filter(p -> p.getIdentifier().equals(identifier)).findFirst();
    }

    public List<Account> findAll() {
        LOG.info("findAll()");
        return accounts;
    }
}
