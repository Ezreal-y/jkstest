package com.jenkins.jkstest.controller;

import com.jenkins.jkstest.security.beans.ResultBean;
import com.jenkins.jkstest.security.utils.ResultUtil;
import com.jenkins.jkstest.system.aop.Syslog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author l
 * @date 2020/6/4 19:40
 * @description
 */
@RestController
@RequestMapping("/test1")
public class FirstContorller {

    @PostMapping("/parameter")
    public HashMap jso1n() {
        HashMap s = new HashMap();
        s.put("first", "11");
        HashMap s1 = s;
        return s1;
    }

    @PostMapping("/user")
    public ResultBean testUseTime() throws InterruptedException {
        Thread.sleep(2000);
        return ResultUtil.ok();
    }
}
