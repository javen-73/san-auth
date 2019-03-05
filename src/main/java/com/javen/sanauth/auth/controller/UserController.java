package com.javen.sanauth.auth.controller;

import com.javen.sanauth.commons.returnUtils.Result;
import com.javen.sanauth.commons.utils.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @RequestMapping("/login")
    public Result login(String username,String password){
        return Result.buildResultSuccess("success", JwtUtil.sign());
    }
}
