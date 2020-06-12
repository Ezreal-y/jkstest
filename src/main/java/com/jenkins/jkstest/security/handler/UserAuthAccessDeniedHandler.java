
package com.jenkins.jkstest.security.handler;


import com.jenkins.jkstest.security.utils.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author l
 * @date 2020/1/19 11:55
 * @description 暂无权限处理类
 */

@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultUtil.responseJson(httpServletResponse, ResultUtil.resultCode(403,"未授权"));
    }
}
