package com.jenkins.jkstest.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jenkins.jkstest.security.entity.SysRole;
import com.jenkins.jkstest.security.mapper.SysRoleMapper;
import com.jenkins.jkstest.security.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


    @Override
    public List<SysRole> selectSysRoleByUserId(Long roleId) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRole::getRoleId,roleId);
        return this.baseMapper.selectList(queryWrapper);
    }
}
