package com.jenkins.jkstest.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jenkins.jkstest.security.entity.SysUserRole;
import com.jenkins.jkstest.security.mapper.SysUserRoleMapper;
import com.jenkins.jkstest.security.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 * 用户与角色关系表 服务实现类
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {


}
