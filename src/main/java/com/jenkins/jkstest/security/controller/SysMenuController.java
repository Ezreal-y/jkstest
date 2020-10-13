package com.jenkins.jkstest.security.controller;


import com.jenkins.jkstest.security.beans.ResultBean;
import com.jenkins.jkstest.security.common.enums.ApiStatus;
import com.jenkins.jkstest.security.utils.ResultUtil;
import com.jenkins.jkstest.security.utils.SecurityUtil;
import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import com.jenkins.jkstest.system.aop.Syslog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author l
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/security/sys-menu")
@Api(tags="测试权限",description = "接口描述")
public class SysMenuController {


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @Syslog()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", dataType = "String", paramType = "header", example = "xingguo"),
            @ApiImplicitParam()
    }
    )
    public ResultBean userLogin() {
        Map<String,Object> result = new HashMap<>();
        SelfUserEntityVO userDetails = SecurityUtil.getUserInfo();
        result.put("title","管理端信息");
        result.put("data",userDetails);
        //return ResultUtil.ok(result);
        return ResultUtil.restResult(ApiStatus.PARAM_TYPE_ERROR);
    }
}
