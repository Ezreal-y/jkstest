package com.jenkins.jkstest.security.exception;


import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


//其抽象级别应该是用于对Controller进行“切面”环绕的，而具体的业务织入方式则是通过结合其他的注解来实现的。

/**
 * @author l
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
    /**
     * 结合@ExceptionHandler用于全局异常的处理。
     *
     * @return
     * @ExceptionHandler的作用主要在于声明一个或多个类型的异常,当符合条件的Controller抛出这些异常后将会对这些异常进行捕获，然后按照其标注的方法的逻辑进行处理，从而改变返回的视图信息。
     */
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody
    Map<String, Object> customException(Exception e) {
        log.error("err->", e);
        log.error("err->", e.getMessage());
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "505");
        map.put("msg1",e.getMessage());
        return map;
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public Map<String, Object> jrsfExceptionHandler(ExpiredJwtException ex) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "50ss5");
        map.put("msg1", ex.getMessage());
        return map;
    }

    @ExceptionHandler(value = ClassCastException.class)
    public Map<String, Object> jrsfExceptionHandler(ClassCastException ex) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "50ss5");
        map.put("msg1s", ex.getMessage());
        return map;
    }

}
