package com.jenkins.jkstest.system.aspect;


import com.jenkins.jkstest.security.exception.MyExceptionHandler;
import com.jenkins.jkstest.security.utils.SecurityUtil;
import com.jenkins.jkstest.system.aop.Syslog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 自定义注解aop
 *
 * @author l
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {
    @Autowired
    private MyExceptionHandler myExceptionHandler;

    // @Pointcut("execution(public * com.example.demo.controller.*.*(..))")这个controller包下的有任意参数的任方法
     //@Pointcut("execution(public * com.jenkins.jkstest.*.controller.*(..))")


    //匹配jkstest包下的所有带ller后缀类中的所有方法
     //@Pointcut("execution(* com.jenkins.jkstest..*.*ller.*(..))")

    //匹配注解有Syslog注解的方法
    @Pointcut("@annotation(com.jenkins.jkstest.system.aop.Syslog)")
    public void Syslog() {
    }

    Long startTime;
    @Before("Syslog()")
    public void doBefore(JoinPoint joinPoint)  {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        startTime = System.currentTimeMillis();
        //HttpSession session = request.getSession();
        //String userName =  (String) session.getAttribute("user");

        System.out.println("===========================以下是Aspect的Before方法的执行结果==========================");
        //url
        //System.out.println("url=" + request.getRequestURL());
        // 请求类，两句一样
        String strClassName = joinPoint.getTarget().getClass().getName();
        //String strClassName = joinPoint.getSignature().getDeclaringTypeName();
        String operation = null;
        try {
            operation = getControllerMethodDescription(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("得到operation=" + operation);
        //ip
        System.err.println("IP=" + request.getRemoteAddr());
        //获取类名和方法名
        System.err.println("class_method=" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //args[]
        System.err.println("args=" + joinPoint.getArgs());

        Class<?> aClass = joinPoint.getTarget().getClass();
        for (Method method : aClass.getDeclaredMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                Syslog logWrite = method.getAnnotation(Syslog.class);
                if (logWrite != null) {
                    System.out.println(SecurityUtil.getUserName() + "Found LogWrite:" + logWrite.user() + " " + logWrite.action() + "处理中");
                }
            }
        }
    }

    //环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的.
    @Around("Syslog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        try {
            //目标方法执行。此处执行后 切面也执行一次
            result = proceedingJoinPoint.proceed();
            return result;
        } catch (Exception e) {
            System.out.println("===========================以下是Aspect的doAround方法捕捉到的异常==========================");
            Class<?> aClass = proceedingJoinPoint.getTarget().getClass();
            for (Method method : aClass.getDeclaredMethods()) {
            //for (Method method : BaseController.class.getDeclaredMethods()) {
                if (method.getName().equals(proceedingJoinPoint.getSignature().getName())) {
                    Syslog logWrite = method.getAnnotation(Syslog.class);
                    if (logWrite != null) {
                        System.out.println(SecurityUtil.getUserName() +"Found LogWrite:" + logWrite.user() + " " + logWrite.action() + "处理失败");
                    }
                }
            }
            return myExceptionHandler.customException(e);
        }
    }

    /**
     * 打印输出结果<br>
     *
     * @param joinPoint
     * @param object    注意这里的object 应该和returning="object"保持一致
     */
    @AfterReturning(pointcut = "Syslog()", returning = "object")
    public void doAfterReturning(JoinPoint joinPoint, Object object) {
        System.out.println("===========================以下是Aspect的doAfterReturning方法的执行结果==========================");
        Class<?> aClass = joinPoint.getTarget().getClass();
        System.err.println("用时"+(System.currentTimeMillis()-startTime));
        for (Method method : aClass.getDeclaredMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                Syslog logWrite = method.getAnnotation(Syslog.class);
                if (logWrite != null) {
                    System.out.println("Found LogWrite:" + logWrite.user() + " " + logWrite.action() + "处理完成");
                }
            }
        }
        System.err.println("response=" +object );
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取连接点的方法签名对象；
        String methodName = joinPoint.getSignature().getName();
        // 获取连接点方法运行时的入参列表
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(Syslog.class).action();
                    break;
                }
            }
        }
        return description;
    }
}
