package com.javen.sanauth.auth.filter;

import com.alibaba.fastjson.JSON;
import com.javen.sanauth.auth.shiro.JWTToken;
import com.javen.sanauth.commons.returnUtils.Result;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含token字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(AUTHORIZATION_HEADER);
        return authorization != null;
    }
    /**
     * 执行登录
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        /*判断是否带有 token*/
        if (isLoginAttempt(request, response)) {
            //执行登录
            if(executeLogin(request, response)){
                if(Objects.isNull(mappedValue)){
                    return false;
                }
                String[] arra = (String[])mappedValue;
                Subject subject = getSubject(request, response);
                for (String s : arra) {
                    //判断权限
                    if (subject.isPermitted(s)){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    /**
     *  onAccessDenied：表示 （isAccessAllowed 返回false时） 访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Subject subject = getSubject(request,response);
        //获取请求token，如果token不存在，直接返回401
        String token = req.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.isEmpty(token)){
            String json = JSON.toJSONString(new Result(HttpStatus.UNAUTHORIZED.value(), "invalid token",null));
            httpResponse.getWriter().print(json);
            return false;
        }
        // 未认证的情况
        if (null == subject || !subject.isAuthenticated()) {
            // 告知JWT认证失败
            String json = JSON.toJSONString(new Result(HttpStatus.UNAUTHORIZED.value(), "error jwt",null));
            httpResponse.getWriter().print(json);
        }else {
            //  已经认证但未授权的情况
            // 告知客户端JWT没有权限访问此资源
            String json = JSON.toJSONString(new Result(HttpStatus.FORBIDDEN.value(), "no permission",null));
            httpResponse.getWriter().print(json);
        }
        return false;
    }


}
