package com.jenkins.jkstest;

import com.jenkins.jkstest.security.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.jenkins.**.mapper")
public class JkstestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(JkstestApplication.class, args);
        SpringContextUtil.setApplicationContext(run);
    }

}
