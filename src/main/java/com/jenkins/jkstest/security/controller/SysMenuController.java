package com.jenkins.jkstest.security.controller;


import com.jenkins.jkstest.security.beans.ResultBean;
import com.jenkins.jkstest.security.utils.ResultUtil;
import com.jenkins.jkstest.security.utils.SecurityUtil;
import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import com.jenkins.jkstest.system.aop.Syslog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/security/sys-menu")
public class SysMenuController {


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @Syslog()
    public ResultBean userLogin(){
        Map<String,Object> result = new HashMap<>();
        SelfUserEntityVO userDetails = SecurityUtil.getUserInfo();
        result.put("title","管理端信息");
        result.put("data",userDetails);
        return ResultUtil.ok(result);
    }
}
