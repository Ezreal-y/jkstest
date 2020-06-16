package com.jenkins.jkstest.security.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author l
 * @date 2020/6/15 18:21
 * @description Spring应用上下文环境
 */
public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据beanId返回Spring中的实例
     *
     * @param
     * @param beanName
     * @return
     * @Date 2019-08-07 17:36
     **/
    public static Object getBean(Class<?> beanName) throws BeansException {

        return applicationContext.getBean(beanName);
    }

}
