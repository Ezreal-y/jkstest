package com.jenkins.jkstest.security.config;

import com.jenkins.jkstest.security.utils.SpringContextUtil;
import com.jenkins.jkstest.security.utils.SysDateUtil;
import com.jenkins.jkstest.system.entity.SysJobs;
import com.jenkins.jkstest.system.service.ISysJobsService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.*;

import static org.quartz.JobBuilder.newJob;

//目前正在使用的任务类

/**
 * @author KR
 */
@Slf4j
public class QuartzManager  {

	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

	/**
	 * 默认任务组名
	 */
	private static String JOB_GROUP_NAME = "MY_JOBGROUP_NAME";

	/**
	 * 默认触发器组名
	 */
	private static String TRIGGER_GROUP_NAME = "MY_TRIGGERGROUP_NAME";

	public static int FLAG = 1;

	/**
	 * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * @param jobName
	 *            任务名
	 * @param cls
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档 qgw 2016年1月21日 下午3:30:10 ^_^
	 */
	public static void addJob(String jobName, Class cls, String time, Object scheduleJob) {
		try {
			// 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
			// JobDetail 是具体Job实例
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail job = newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
			// 添加具体任务方法
			job.getJobDataMap().put("scheduleJob", scheduleJob);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
			// 按新的cronExpression表达式构建一个新的trigger
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).withSchedule(scheduleBuilder).build();

			// 交给scheduler去调度
			sched.scheduleJob(job, trigger);

			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 添加一个定时任务
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档 qgw
	 */
	@SuppressWarnings("unchecked")
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass,
							  String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail job = newJob(jobClass).withIdentity(jobName, jobGroupName).build();
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
			// 按新的cronExpression表达式构建一个新的trigger
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(scheduleBuilder).build();
			sched.scheduleJob(job, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName
	 * @param time
	 *            qgw 2016年1月21日 下午3:28:34 ^_^
	 */
	public static void modifyJobTime(String jobName, String time) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);

		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				// 按新的trigger重新设置job执行
				sched.rescheduleJob(triggerKey, trigger);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 * @date 2016年1月27日 下午4:45:15 ^_^
	 */
	public static int modifyJobTime(String triggerName, String triggerGroupName, String time) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				return FLAG;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				// trigger已存在，则更新相应的定时设置
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				// 按新的trigger重新设置job执行
				sched.resumeTrigger(triggerKey);
			}
			FLAG = 1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return FLAG;
	}

	/**
	 * @Description 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName
	 * @author qgw
	 * @date 2016年1月29日 下午2:21:16 ^_^
	 */
	public static void removeJob(String jobName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			Trigger trigger = sched.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			sched.pauseTrigger(triggerKey);
			// 停止触发器
			sched.unscheduleJob(triggerKey);// 移除触发器
			sched.deleteJob(jobKey);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 移除一个任务
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @date 2016年1月29日 下午2:21:16 ^_^
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, triggerGroupName);
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(triggerKey);// 停止触发器
			sched.unscheduleJob(triggerKey);// 移除触发器
			sched.deleteJob(jobKey);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:暂停一个任务
	 * @param jobName
	 * @param jobGroupName
	 *            qgw 2016年1月22日 下午4:24:55 ^_^
	 */
	public static void pauseJob(String jobName, String jobGroupName) {
		JobKey jobKey = JobKey.jobKey(jobName, jobName);
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:暂停一个任务(使用默认组名)
	 * @param jobName

	 *            qgw 2016年1月22日 下午4:24:55 ^_^
	 */
	public static void pauseJob(String jobName) {
		pauseJob(jobName, JOB_GROUP_NAME);
	}

	/**
	 * @Description:启动所有定时任务
	 * @author qgw
	 * @date 2016年1月29日 下午2:21:16 ^_^
	 */
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description 关闭所有定时任务
	 * @author qgw
	 * @date 2016年1月25日 下午2:26:54 ^_^
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				Collection<Scheduler> allSchedulers = gSchedulerFactory.getAllSchedulers();
				log.info("共计[{}]个任务关闭",allSchedulers.size());
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 初始已经持久化的定时任务
	 */

	public static void init() {

		ISysJobsService jobsBean = (ISysJobsService) SpringContextUtil.getBean(ISysJobsService.class);
		List<SysJobs> allTaskList = jobsBean.list();
		int count = 0;
		for (SysJobs job1 : allTaskList) {
			String taskCron = SysDateUtil.cronToStr(job1.getJobCron());
			/*Map<String, String> jobMap = new HashMap<>();

			jobMap.put("jobCron", job1.getJobCron());
			jobMap.put("jobStatus", "0");
			jobMap.put("jobDesc", job1.getJobDesc());
			jobMap.put("jobName", job1.getJobName());
			jobMap.put("jobGroup", job1.getJobGroup());*/

			// 判断任务是否过期：执行时间大于当前时间 或者定时任务状态为1
			Date now = new Date();
			// 返回正值是代表左侧日期大于参数日期，反之亦然，日期格式必须一致
			int i = taskCron.compareTo(SysDateUtil.fmtDateToStr(now)); // 1是前大于后
																		// -1是前小于后
			if (i == 1 && "1".equals(job1.getJobStatus())) {
				Class clazz = null;
				try {
					clazz = Class.forName("com.jenkins.jkstest.system.taskjob." + job1.getJobClass());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				addJob(job1.getJobName(), job1.getJobGroup(), job1.getTriggerName(), job1.getTriggerGroup(), clazz, job1.getJobCron());
				log.info("已启动定时任务{}：" + job1.getJobDesc() + SysDateUtil.cronToStr(job1.getJobCron()), (++count));
			}
			// int modifyTask = timeTaskDao.modifyTask(jobMap);
		}
		log.info("已启动定时任务个数：{}", count);
	}

}
