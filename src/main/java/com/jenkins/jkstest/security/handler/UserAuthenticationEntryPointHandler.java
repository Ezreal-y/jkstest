package com.jenkins.jkstest.security.handler;

import com.jenkins.jkstest.security.utils.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author l
 * @date 2020/3/11 15:35
 * @description 用户未登录处理类
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 9:01
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(401,"未登录"));
    }
}
