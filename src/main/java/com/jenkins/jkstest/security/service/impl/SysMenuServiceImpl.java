package com.jenkins.jkstest.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jenkins.jkstest.security.entity.SysMenu;
import com.jenkins.jkstest.security.mapper.SysMenuMapper;
import com.jenkins.jkstest.security.service.ISysMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

}
