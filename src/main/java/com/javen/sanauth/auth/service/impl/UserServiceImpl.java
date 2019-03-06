package com.javen.sanauth.auth.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.javen.sanauth.auth.entity.User;
import com.javen.sanauth.auth.mapper.UserMapper;
import com.javen.sanauth.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author javen73
 * @date 2019/3/6 14:21
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Override
    public User findUserByName(String username) {
        return baseMapper.findUserByName(username);
    }
}
