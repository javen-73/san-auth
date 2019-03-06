package com.javen.sanauth.auth.shiro;

import com.javen.sanauth.auth.entity.User;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author javen73
 * @date 2019/3/6 17:40
 */
public class RealmUser extends User implements AuthenticationToken {

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
