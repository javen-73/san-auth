package com.javen.sanauth.auth.controller;

import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.shiro.SecurityUtils.getSubject;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test-a";
    }

    @RequestMapping("/xxx")
    public String xxx(){
        Subject subject = getSubject();
        System.out.println(subject);
        return "xxx 你可能没有权限";
    }
}
