package com.jenkins.jkstest.system.service;

import com.jenkins.jkstest.system.entity.SysJobs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jenkins.jkstest.system.vo.JobsVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author l
 * @since 2020-06-10
 */
public interface ISysJobsService extends IService<SysJobs> {

   /**
    *  根据任务Id取得任务列表
    * @param jobId
    * @return
    */
   List<SysJobs> getJobsListByJobid( String jobId);

   /**
    *
    * @return
    */
   List<SysJobs> getAliveJobList();

   /**
    *
    * @param jobsVO
    */
   void setJob(JobsVO jobsVO);

   /**
    *
    * @param request
    */
   Integer setJob(HttpServletRequest request);
}
