package com.jenkins.jkstest.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author l
 * @since 2020-06-10
 * https://blog.csdn.net/zhanlanmg/article/details/50392266
 */
@Data
@Accessors(chain = true)
public class SysJobs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableField("jobId")
    private String jobId;

    /**
     * 操作员
     */
    @TableField("staffId")
    private String staffId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 触发器名
     */
    private String triggerName;

    /**
     * 触发器组名
     */
    private String triggerGroup;

    /**
     * 任务状态 0禁用 1启用 2删除
     */
    private String jobStart;

    /**
     * 指定时间或者第一次延时
     */
    private String jobCron;

    /**
     * 任务参数
     */
    private String param;

    /**
     * 任务类型 1一次性 2重复性
     */
    private String jobType;

    /**
     * 任务状态 0禁用 1启用 2删除
     */
    private String jobStatus;

    /**
     * 任务类
     */
    private String jobClass;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 创建时间
     */
   /* @TableField("create_Time")*/
    private Long createTime;


}
