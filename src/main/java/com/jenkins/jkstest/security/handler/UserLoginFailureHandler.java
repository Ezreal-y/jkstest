package com.jenkins.jkstest.security.handler;

import com.jenkins.jkstest.security.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author l
 * @date 2020/3/11 15:41
 * @description 登录失败返回结果
 */
@Component
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名不存在"));
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户被冻结"));
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名密码不正确"));
        }
        ResultUtil.responseJson(response, ResultUtil.resultCode(500, "登录失败"));
    }
}
