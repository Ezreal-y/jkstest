package com.jenkins.jkstest.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jenkins.jkstest.security.entity.SysMenu;
import com.jenkins.jkstest.security.entity.SysUser;
import com.jenkins.jkstest.security.mapper.SysUserMapper;
import com.jenkins.jkstest.security.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser selectUserByName(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,username);
        return this.baseMapper.selectOne(queryWrapper);
    }



    @Override
    public List<SysMenu> selectSysMenuByUserId(Long userId) {
        return this.baseMapper.selectSysMenuByUserId(userId);
    }

    @Override
    public boolean saveBatch(Collection<SysUser> entityList) {
        return false;
    }
}
