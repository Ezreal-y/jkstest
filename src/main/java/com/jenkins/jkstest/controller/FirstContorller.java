package com.jenkins.jkstest.controller;

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
}
