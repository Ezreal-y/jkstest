package com.jenkins.jkstest.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jenkins.jkstest.security.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
public interface ISysRoleService extends IService<SysRole> {
     List<SysRole> selectSysRoleByUserId(Long roleId);
}
