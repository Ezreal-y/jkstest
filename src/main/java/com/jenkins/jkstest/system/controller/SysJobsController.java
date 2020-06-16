package com.jenkins.jkstest.system.controller;


import com.jenkins.jkstest.security.config.QuartzManager;
import com.jenkins.jkstest.security.utils.ResultUtil;
import com.jenkins.jkstest.security.utils.SecurityUtil;
import com.jenkins.jkstest.security.beans.ResultBean;
import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import com.jenkins.jkstest.system.entity.SysJobs;
import com.jenkins.jkstest.system.service.ISysJobsService;
import com.jenkins.jkstest.system.taskjob.TestJob;
import com.jenkins.jkstest.system.vo.JobsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
public class SysJobsController{

    @Autowired
    ISysJobsService sysJobsService;

    @GetMapping("/{jobid}")
    public ResultBean getJobs(@PathVariable("jobid") String jobid) {
        SelfUserEntityVO userDetails = SecurityUtil.getUserInfo();
        List<SysJobs> jobsListByJobid = sysJobsService.getJobsListByJobid(jobid);
        return ResultUtil.ok(jobsListByJobid);
    }

    @PostMapping("/")
    public ResultBean setJobs(HttpServletRequest request, @RequestParam String jobid) {
        Integer aBoolean = sysJobsService.setJob(request);
        return ResultUtil.ok(aBoolean);
    }

    @PostMapping("/vo")
    public ResultBean setJobs(@RequestBody  JobsVO jobid) {
        sysJobsService.setJob(jobid);
        return ResultUtil.ok();
    }
}
