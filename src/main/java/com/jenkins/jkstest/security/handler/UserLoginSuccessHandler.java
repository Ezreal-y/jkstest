package com.jenkins.jkstest.security.handler;


import com.jenkins.jkstest.security.config.JWTConfig;
import com.jenkins.jkstest.security.utils.ResultUtil;
import com.jenkins.jkstest.security.utils.JWTTokenUtil;
import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**登录成功处理类
 * @author l
 * @date 2020/3/11 20:03
 * @description
 */
@Component
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @ResponseBody
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)  {
// 组装JWT
        SelfUserEntityVO selfUserEntity = (SelfUserEntityVO) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(selfUserEntity);
        token = JWTConfig.tokenPrefix + token;
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        ResultUtil.responseJson(httpServletResponse,resultData);
       //return  ResultUtil.ok(token);
    }
}
