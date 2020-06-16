package com.jenkins.jkstest.system.taskjob;

import com.jenkins.jkstest.security.utils.SpringContextUtil;
import com.jenkins.jkstest.system.entity.SysJobs;
import com.jenkins.jkstest.system.service.ISysJobsService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author l
 * @date 2020/6/12 18:47
 * @description
 */
@DisallowConcurrentExecution
@Slf4j
public class TestJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
        log.info("定时任务[{}]--start",jobExecutionContext.getJobDetail().getJobDataMap());
        ISysJobsService sysJobsServiceBean = (ISysJobsService) SpringContextUtil.getBean(ISysJobsService.class);
        List<SysJobs> selectAll = sysJobsServiceBean.getAliveJobList();
        log.info("定时任务--end--{}",selectAll.size());
    }


}
