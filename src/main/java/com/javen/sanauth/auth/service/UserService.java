package com.javen.sanauth.auth.service;

import com.baomidou.mybatisplus.service.IService;
import com.javen.sanauth.auth.entity.User;

/**
 * @author javen73
 * @date 2019/3/6 14:21
 */
public interface UserService extends IService<User> {

    User findUserByName(String username);
}
