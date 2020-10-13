package com.jenkins.jkstest.controller;

import com.jenkins.jkstest.security.beans.ResultBean;
import com.jenkins.jkstest.security.utils.ResultUtil;
import com.jenkins.jkstest.system.aop.Syslog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author l
 * @date 2020/6/4 19:40
 * @description
 */
@RestController
@RequestMapping("/test1")
@Api(tags = "订单系统接口", description = "订单系统模块相关接口")
public class FirstContorller {

    @PostMapping("/parameter")
    public HashMap jso1n() {
        HashMap s = new HashMap();
        s.put("first", "11");
        HashMap s1 = s;
        return s1;
    }

    @PostMapping("/user")
    @ApiOperation(value = "下单")
    public ResultBean testUseTime() throws InterruptedException {
        return ResultUtil.ok();
    }
}
