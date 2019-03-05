package com.javen.sanauth.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test-a";
    }

    @RequestMapping("/xxx")
    public String xxx(){
        return "xxx 你可能没有权限";
    }
}
