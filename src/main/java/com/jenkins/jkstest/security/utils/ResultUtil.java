package com.jenkins.jkstest.security.utils;

import com.alibaba.fastjson.JSON;
import com.jenkins.jkstest.security.common.enums.ApiStatus;
import com.jenkins.jkstest.security.beans.ResultBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author l
 * @date 2020/3/11 15:28
 * @description
 */
@Slf4j
@Data
public class ResultUtil<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 私有化构造器
     */
    private ResultUtil() {
    }

    /**
     * 使用response输出JSON
     *
     * @Param resultMap 数据
     * @Return void
     */
    public static void responseJson(ServletResponse response, Map<String, Object> resultMap) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
        } catch (Exception e) {
            log.error("【JSON输出异常】" + e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }


    /**
     * 返回失败示例
     *
     * @Param resultMap  返回数据MAP
     * @Return Map<String   ,   Object> 返回数据MAP
     */
    public static Map<String, Object> resultError(Map<String, Object> resultMap) {
        resultMap.put("msg", "操作失败");
        resultMap.put("code", 500);
        return resultMap;
    }

    /**
     * 通用示例
     *
     * @Param code 信息码
     * @Param msg  信息
     * @Return Map<String   ,   Object> 返回数据MAP
     */
    public static Map<String, Object> resultCode(Integer code, String msg) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", msg);
        resultMap.put("code", code);
        return resultMap;
    }

    public static <T> ResultBean<T> ok() {
        return restResult(null, ApiStatus.SUCCESS);
    }

    public static <T> ResultBean<T> ok(T data) {
        return restResult(data, ApiStatus.SUCCESS);
    }

    public static <T> ResultBean<T> fail() {
        return restResult(null, ApiStatus.FAILURE);
    }

    public static <T> ResultBean<T> fail(T data) {
        return restResult(data, ApiStatus.FAILURE);
    }


    public static <T> ResultBean<T> restResult(ApiStatus status) {
        return restResult(null, status.getCode(), status.getMsg());
    }

    public static <T> ResultBean<T> restResult(T data, ApiStatus status) {
        return restResult(data, status.getCode(), status.getMsg());
    }

    private static <T> ResultBean restResult(T data, long code, String msg) {
        ResultBean apiResult = new ResultBean<>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        if (data != null) {
            apiResult.setData(data);
        }
        return apiResult;
    }
}
