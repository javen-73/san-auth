package com.javen.sanauth.auth.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.javen.sanauth.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author javen73
 * @date 2019/3/6 14:23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findUserByName(String username);
}
