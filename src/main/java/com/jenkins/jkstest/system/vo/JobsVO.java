package com.jenkins.jkstest.system.vo;

import com.jenkins.jkstest.system.entity.SysJobs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author l
 * @date 2020/6/10 15:13
 * @description
 */
@Data
@ApiModel(description = "定时任务的属性", value = "任务vo")
public class JobsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */

    private String jobId;

    /**
     * 操作员
     */

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
    @ApiModelProperty(value="时间",name="jobCron",example="0 16 17 15 6 ? 2020")
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
    @ApiModelProperty(value="任务状态 0禁用 1启用 2删除",name="jobStatus")
    private String jobStatus;

    /**
     * 任务类
     */
    @ApiModelProperty(value="任务类",name="jobClass")
    private String jobClass;

    /**
     * 任务描述
     */
    @ApiModelProperty(value="任务描述",name="jobDesc")
    private String jobDesc;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 创建时间
     */
    private String createTime;
}
