package com.jenkins.jkstest.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenkins.jkstest.security.config.QuartzManager;
import com.jenkins.jkstest.security.utils.SecurityUtil;
import com.jenkins.jkstest.system.entity.SysJobs;
import com.jenkins.jkstest.system.mapper.SysJobsMapper;
import com.jenkins.jkstest.system.service.ISysJobsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jenkins.jkstest.system.taskjob.TestJob;
import com.jenkins.jkstest.system.vo.JobsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author l
 * @since 2020-06-10
 */
@Slf4j
@Service
public class SysJobsServiceImpl extends ServiceImpl<SysJobsMapper, SysJobs> implements ISysJobsService {

    @Override
    public List<SysJobs> getJobsListByJobid(String jobId) {
        List<SysJobs> sysJobs;
        QueryWrapper<SysJobs> jobsQW = new QueryWrapper<>();
        if (!"0".equals(jobId)) {
            jobsQW.eq("jobId", jobId);
        }
        sysJobs = this.baseMapper.selectList(jobsQW);
        return sysJobs;
    }

    @Override
    public List<SysJobs> getAliveJobList() {
        List<SysJobs> sysJobs;
        QueryWrapper<SysJobs> jobsQW = new QueryWrapper<>();
        jobsQW.eq("job_status", "1");
        sysJobs = this.baseMapper.selectList(jobsQW);
        return sysJobs;
    }

    @Override
    public void setJob(JobsVO jobsVO) {
        //jobsVO.getJobClass();
        SysJobs sysJobs =new SysJobs();
        BeanUtils.copyProperties(jobsVO,sysJobs);
        sysJobs.setCreateTime(System.currentTimeMillis());
        String jobCron = jobsVO.getJobCron();
        QuartzManager.removeJob(sysJobs.getJobName());
        // 添加定时任务 默认任务组 默认触发器组
        //QuartzManager1.addJob(jobName, QuartzJobFactory.class, cron, job);
        QuartzManager.addJob(sysJobs.getJobName(), "1jobgroup", "1trigger", "1Group", TestJob.class, jobCron);
        int insert = baseMapper.insert(sysJobs);
        log.info("添加任务");
    }


    @Override
    public Integer setJob(HttpServletRequest request) {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("date");
        String hours = request.getParameter("hours");
        String minutes = request.getParameter("minutes");
        //格式:CronTrigger配置格式: [秒] [分] [小时] [日] [月] [周] [年] 0 0 0 10 5 ? 2019
        String cron = "0 " + minutes + " " + hours + " " + day + " " + month + " ? " + year;
        log.info(cron);
        //任务
        SysJobs job = new SysJobs();
        job.setStaffId(SecurityUtil.getUserName());
        String jobName = 1 + "_job";
        job.setJobId("1");
        job.setJobName(jobName);
        job.setJobCron(cron);
        job.setJobType("1");
        job.setJobStatus("1");
        job.setJobGroup("MY_JOBGROUP_NAME");
        job.setJobDesc("定时导出日志");
        job.setParam("TestJob");
        job.setIp(request.getRemoteAddr());
        job.setCreateTime(System.currentTimeMillis());
        // 删除已有的定时任务
        QuartzManager.removeJob(jobName);
        // 添加定时任务 默认任务组 默认触发器组
        //QuartzManager1.addJob(jobName, QuartzJobFactory.class, cron, job);
        QuartzManager.addJob(jobName, "1jobgroup", "1trigger", "1Group", TestJob.class, cron);

        int insert = baseMapper.insert(job);
        log.info("添加任务");

        return insert ;
    }
}
