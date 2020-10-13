package com.jenkins.jkstest.security.beans;

import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * @author l
 * @date 2020/6/11 16:06
 * @description 返回泛型类
 */
@Data
@ApiIgnore
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 业务错误码
     */
    private long code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 结果集
     */

    private T data;

}
