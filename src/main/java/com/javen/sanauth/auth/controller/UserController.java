package com.javen.sanauth.auth.controller;

import com.javen.sanauth.auth.constants.ShiroConstant;
import com.javen.sanauth.auth.entity.User;
import com.javen.sanauth.auth.service.UserService;
import com.javen.sanauth.commons.returnDTO.ResultDTO;
import com.javen.sanauth.commons.utils.EncryptUtil;
import com.javen.sanauth.commons.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResultDTO login(String username, String password){
        User user = userService.findUserByName(username);
        if(Objects.isNull(user)){
            return ResultDTO.buildResultFail(0,"用户不存在");
        }
        String userPassword = user.getPassword();
        String md5 = EncryptUtil.md5(password, user.getId());
        if(!Objects.equals(userPassword,md5)){
            return ResultDTO.buildResultFail(0,"密码不正确");
        }
        return ResultDTO.buildResultSuccess("success", JwtUtil.sign(user));
    }
}
