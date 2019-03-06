package com.javen.sanauth.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author javen73
 * @date 2019/3/6 11:03
 */
@TableName("t_user")
@Data
public class User {
    //ID
    private String id;
    // 用户名
    private String username;
    // 密码
    private String password;
    //昵称
    private String nickName;
    // 头像
    private String avatar;
    //商户ID
    private String merchantId;
    // 创建时间
    private Date createTime;
}
