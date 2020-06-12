package com.jenkins.jkstest.security.utils;

import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author l
 * @date 2020/3/11 15:33
 * @description
 */
public class SecurityUtil {
    /**
     * 私有化构造器
     */
    private SecurityUtil() {
    }

    /**
     * 获取当前用户信息
     */
    public static SelfUserEntityVO getUserInfo() {
        SelfUserEntityVO userDetails = (SelfUserEntityVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        //userDetails.setPassword(details.);
        return userDetails;
    }

    /**
     * 获取当前用户ID
     */
    public static Long getUserId() {
        return getUserInfo().getUserId();
    }

    /**
     * 获取当前用户账号
     */
    public static String getUserName() {
        return getUserInfo().getUsername();
    }


    /**
     * 获取当前用户角色
     */
    public static Collection<GrantedAuthority> getUserAuthorities() {
        return getUserInfo().getAuthorities();
    }
}

