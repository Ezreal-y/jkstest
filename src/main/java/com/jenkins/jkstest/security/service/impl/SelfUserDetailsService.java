package com.jenkins.jkstest.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenkins.jkstest.security.entity.SysUser;
import com.jenkins.jkstest.security.service.ISysUserService;
import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author l
 * @date 2020/3/11 16:13
 * @description
 */
@Service
public class SelfUserDetailsService implements UserDetailsService {
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询用户信息
     *
     * @param username
     * @return UserDetails SpringSecurity用户信息
     * @throws UsernameNotFoundException
     */
    @Override
    public SelfUserEntityVO loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper();
        userQueryWrapper.eq("username", username);
        SysUser sysUserEntity = sysUserService.getOne(userQueryWrapper);
        if (null != sysUserEntity) {
            // 组装参数
            SelfUserEntityVO selfUserEntity = new SelfUserEntityVO();
            BeanUtils.copyProperties(sysUserEntity, selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}
