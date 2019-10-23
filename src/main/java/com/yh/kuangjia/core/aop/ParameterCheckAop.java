package com.yh.kuangjia.core.aop;

import com.yh.kuangjia.core.exception.MyParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Aspect
@Order(10)
public class ParameterCheckAop {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ParameterCheckAopUtil parameterCheckOption;

    /**
     * 定义有一个切入点，范围为web包下的类
     */
    @Pointcut("execution(public * com.yh.kuangjia.controller..*.*(..))")
    public void checkParam() {
    }

    @Before("checkParam()")
    public void doBefore(JoinPoint joinPoint) {
    }

    /**
     * 检查参数是否为空
     */
    @Around("checkParam()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        // 参数校验，未抛出异常表示验证OK
        parameterCheckOption.checkValid(pjp);

        return pjp.proceed();
    }

    /**
     * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     *
     * @param joinPoint
     */
    @AfterReturning("checkParam()")
    public void doAfterReturning(JoinPoint joinPoint) {
    }

    /**
     * 参数非空校验，如果参数为空，则抛出ParamIsNullException异常
     *
     * @param paramName
     * @param value
     * @param parameterType
     */
    private void paramIsNull(String paramName, Object value, String parameterType) {
        if (value == null || "".equals(value.toString().trim())) {
            throw new MyParamException(paramName);
        }
    }

}
