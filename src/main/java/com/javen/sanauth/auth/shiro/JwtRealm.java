package com.javen.sanauth.auth.shiro;

import com.javen.sanauth.auth.entity.User;
import com.javen.sanauth.auth.service.UserService;
import com.javen.sanauth.commons.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.PasswordAuthentication;
import java.util.Objects;

public class JwtRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Autowired
    private UserService userService;

    /**
     * 授权   --具体可以访问那些路径
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermission("sys:test");
        System.out.println("赋予权限信息");
        return authorizationInfo;
    }

    /**
     * 身份认证   --登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTToken token = (JWTToken) authenticationToken;
        String username = JwtUtil.parse(token.getToken());
        User user = userService.findUserByName(username);
        if(Objects.isNull(user)){
            throw new AuthenticationException("User didn't existed!");
        }
        return new SimpleAuthenticationInfo(user,user,"jwtRealm");
    }
}
