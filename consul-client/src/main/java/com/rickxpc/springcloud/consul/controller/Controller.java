package com.rickxpc.springcloud.consul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hi")
    public String hi(){
        return "Hi, this is from consul client";
    }
}
