package com.jenkins.jkstest.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jenkins.jkstest.security.entity.SysRoleMenu;
import com.jenkins.jkstest.security.mapper.SysRoleMenuMapper;
import com.jenkins.jkstest.security.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与权限关系表 服务实现类
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

}
