package com.jenkins.jkstest.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jenkins.jkstest.security.entity.SysMenu;
import com.jenkins.jkstest.security.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询实体
     *
     * @param username
     * @return
     */
    SysUser selectUserByName(String username);



    /**
     * 根据用户ID查询权限集合
     *
     * @param userId
     * @return
     */
    List<SysMenu> selectSysMenuByUserId(Long userId);
}
