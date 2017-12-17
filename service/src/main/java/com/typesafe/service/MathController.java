package com.typesafe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    private final static Logger LOG = LoggerFactory.getLogger(MathController.class);

    @GetMapping("/sum")
    public @ResponseBody
    int sum(@RequestParam int a, @RequestParam int b) {
        LOG.info("sum  {} + {}", a, b);
        return a + b;
    }


    @GetMapping("/minus")
    public @ResponseBody
    int minus(@RequestParam int a, @RequestParam int b) {
        LOG.info("minus  {} - {}", a, b);
        return a - b;
    }
}
