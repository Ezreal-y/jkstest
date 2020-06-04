package com.jenkins.jkstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class JkstestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JkstestApplication.class, args);
    }

}
