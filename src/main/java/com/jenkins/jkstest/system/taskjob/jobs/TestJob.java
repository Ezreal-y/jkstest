package com.jenkins.jkstest.system.taskjob.jobs;

import com.jenkins.jkstest.security.utils.SpringContextUtil;
import com.jenkins.jkstest.system.entity.SysJobs;
import com.jenkins.jkstest.system.service.ISysJobsService;
import com.jenkins.jkstest.system.taskjob.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author l
 * @date 2020/6/12 18:47
 * @description
 */
@DisallowConcurrentExecution
@Slf4j
public class TestJob implements BaseJob {


    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
        log.info("定时任务[{}]--start",jobExecutionContext.getJobDetail().getJobDataMap());
        ISysJobsService sysJobsServiceBean = (ISysJobsService) SpringContextUtil.getBean(ISysJobsService.class);
        List<SysJobs> selectAll = sysJobsServiceBean.getAliveJobList();
        log.info("定时任务--end--{}",selectAll.size());
    }


}
