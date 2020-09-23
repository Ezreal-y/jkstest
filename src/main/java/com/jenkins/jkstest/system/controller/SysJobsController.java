package com.jenkins.jkstest.system.controller;


import com.jenkins.jkstest.security.config.QuartzManager;
import com.jenkins.jkstest.security.utils.ResultUtil;
import com.jenkins.jkstest.security.utils.SecurityUtil;
import com.jenkins.jkstest.security.beans.ResultBean;
import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import com.jenkins.jkstest.system.entity.SysJobs;
import com.jenkins.jkstest.system.service.ISysJobsService;
import com.jenkins.jkstest.system.vo.JobsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  定时任务
 * </p>
 *
 * @author l
 * @since 2020-06-10
 */
@RestController
@RequestMapping("/system/jobs")
@Slf4j
@Api(description = "用户操作接口")
public class SysJobsController{

    @Autowired
    ISysJobsService sysJobsService;

    @GetMapping("/{jobid}")
    @ApiOperation(value = "权限test", notes="通过jobid获取")
    @ApiImplicitParam(name = "jobid", value = "jobid号码", paramType = "query", required = true, dataType = "String")
    public ResultBean getJobs(@PathVariable("jobid") String jobid) {
        SelfUserEntityVO userDetails = SecurityUtil.getUserInfo();
        List<SysJobs> jobsListByJobid = sysJobsService.getJobsListByJobid(jobid);
        return ResultUtil.ok(jobsListByJobid);
    }

    @PostMapping("/")
    @ApiOperation("添加任务")
    public ResultBean setJobs(HttpServletRequest request, @RequestParam String jobid) {
        Integer aBoolean = sysJobsService.setJob(request);
        return ResultUtil.ok(aBoolean);
    }

    @PostMapping("/vo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
    })
    public ResultBean setJobs(@RequestBody JobsVO jobsVO) {
        sysJobsService.setJob(jobsVO);
        return ResultUtil.ok();
    }
    @PostMapping("/setJob")
    public ResultBean setJob(@RequestBody  JobsVO jobsVO) {
        sysJobsService.setJob(jobsVO);
        return ResultUtil.ok();
    }

    @PostMapping("/getJobs")
    public ResultBean getJobs() throws SchedulerException {
        Collection allJobs = QuartzManager.getAllJobs();
        return ResultUtil.ok(allJobs);
    }

    @PostMapping("/shutdownAllJobs")
    public ResultBean shutdownAllJobs() throws SchedulerException {
        QuartzManager.shutdownJobs();
        return ResultUtil.ok();
    }

    @PostMapping("/arthasTest")
    public ResultBean arthasTest() throws InterruptedException {
        log.info("->>star");
        te();
        log.info("->>star1");
        Thread.sleep(5000);
        log.info("->>end");
        return ResultUtil.ok();
    }

    private void te() throws InterruptedException {
        Thread.sleep(2000);
    }
}
