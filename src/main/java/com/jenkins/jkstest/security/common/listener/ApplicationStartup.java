package com.jenkins.jkstest.security.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @author l
 * @date 2019/11/6 18:16
 * @description ApplicationListener 事件监听器
 */
@Component
public class ApplicationStartup implements ApplicationListener {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            // 初始化环境变量
        } else if (event instanceof ApplicationPreparedEvent) {
            // 初始化完成
        } else if (event instanceof ContextRefreshedEvent) {
            logger.info("项目启动中");

            // 应用刷新
        } else if (event instanceof ApplicationReadyEvent) {
            // 应用已启动完成

        } else if (event instanceof ContextStartedEvent) {
            //应用启动，需要在代码动态添加监听器才可捕获
        } else if (event instanceof ContextStoppedEvent) {
            logger.info("项目停止...");
            // 应用停止
        } else if (event instanceof ContextClosedEvent) {
            // 应用关闭 hook method kill -9无效
            // mailService.sendSysInfo("程序关闭");
            logger.info("应用关闭...");
        } else {
            //logger.info("");
        }
    }
}
