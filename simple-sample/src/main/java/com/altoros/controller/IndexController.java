package com.altoros.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zeremit on 10/20/16.
 */
@RestController
public class IndexController {

    @Value("${name}")
    private String name;

    @RequestMapping("/name")
    public String name() {
        return name;
    }
}
