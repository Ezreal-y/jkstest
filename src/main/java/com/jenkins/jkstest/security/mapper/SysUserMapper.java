package com.jenkins.jkstest.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenkins.jkstest.security.entity.SysMenu;
import com.jenkins.jkstest.security.entity.SysRole;
import com.jenkins.jkstest.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查询实体
     *
     * @param username
     * @return
     */
    SysUser selectUserByName(String username);

    /**
     * 通过用户ID查询角色集合
     *
     * @Param userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);

    /**
     * 通过用户ID查询权限集合
     *
     * @Param userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<SysMenu> selectSysMenuByUserId(Long userId);
}


