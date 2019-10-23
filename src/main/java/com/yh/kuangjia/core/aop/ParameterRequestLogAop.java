package com.yh.kuangjia.core.aop;


import com.yh.kuangjia.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Aspect
@Component
@Order(1)
public class ParameterRequestLogAop {

    /**
     * 定义AOP扫描路径
     */
    @Pointcut("execution(public * com.yh.kuangjia.controller..*.*(..))")
    public void log() {
    }

    /**
     * 记录HTTP请求开始时的日志
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
 /*       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request.getMethod().toUpperCase().equals("OPTIONS")) {
            HttpServletResponse response = attributes.getResponse();
            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Headers","Content-Type,Access-Token");
            response.addHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS");
            response.addHeader("Access-Control-Allow-Origin","*");
            return;
        }*/
    }

    /**
     * 记录HTTP请求结束时的日志
     */
    @After("log()")
    public void doAfter() {
    }


    /**
     * 获取返回内容
     *
     * @param object
     */
    @AfterReturning(value = "execution(public * com.yh.kuangjia.controller..*.*(..))", returning = "object")
    // @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturn(JoinPoint point, Object object) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            //region 请求参数插入日志
            String parames = "";
            String contentType = request.getContentType();    //获取Content-Type


            if ((contentType != null) && (contentType.toLowerCase().startsWith("multipart/"))) {
                parames = "file";
            } else {
                parames = JsonUtil.object2Json(point.getArgs());
            }
            //记录请求日志 用于统计用户所有的请求接口

            //endregion

        } catch (Exception e) {
        }
    }
}