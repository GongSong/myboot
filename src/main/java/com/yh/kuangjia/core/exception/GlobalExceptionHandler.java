package com.yh.kuangjia.core.exception;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice(basePackages = "com.yh.kuangjia.controller")
public class GlobalExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    EmailUtil emailUtil;
//    private static final String send = "243748670@qq.com";

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result errorResult(NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        printfExceptionInfo(e);
        return Result.failure(ResultCode.FAILURE_CODE500, "字段值出现了null值");
    }

    /**
     * 处理数组的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value =ArrayIndexOutOfBoundsException.class)
    @ResponseBody
    public Result errorResult(ArrayIndexOutOfBoundsException e) {
        printfExceptionInfo(e);
        return Result.failure(ResultCode.FAILURE_CODE500, "数组越界异常");
    }

    /**
     * 处理sql的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = UncategorizedSQLException.class)
    @ResponseBody
    public Result errorResult(UncategorizedSQLException e) {
        printfExceptionInfo(e);
        return Result.failure(ResultCode.FAILURE_CODE500, "SQL查询语句异常");
    }



    /**
     * 处理sql的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public Result errorResult(DataIntegrityViolationException e) {
        printfExceptionInfo(e);
        return Result.failure(ResultCode.FAILURE_CODE500, "sql插入/删除/修改数据出现异常");
    }

    /**
     * 处理sql的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public Result errorResult(IllegalArgumentException e) {
        printfExceptionInfo(e);
        return Result.failure(ResultCode.FAILURE_CODE500, "数据库别名出现异常");
    }

    /**
     * 处理sql的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public Result errorResult(SQLException e) {
        printfExceptionInfo(e);
        return Result.failure(ResultCode.FAILURE_CODE500, "数据库异常");
    }

    /**
     * 全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result errorResult(Exception e) {
        printfExceptionInfo(e);
        return Result.failure(ResultCode.FAILURE_CODE500, e.toString());
    }

    /**
     * Token异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MyTokenException.class)
    @ResponseBody
    public Result exception(MyTokenException e) {
        return new Result().failure(ResultCode.FAILURE_TOKEN);
    }

    /**
     * 参数验证异常
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MyParamException.class)
    @ResponseBody
    public Result paramErrorHandler(MyParamException e) {
        printfExceptionInfo(e);
        return new Result(1, e.getMessage());
    }

    /**
     * 异常信息打印
     *
     * @param e
     */
    private void printfExceptionInfo(Exception e) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\r\n**********异常信息开始**********\r\n");
        strBuf.append(" 标题:");
        strBuf.append("\r\n");
        if (e != null) {
            strBuf.append(e.getMessage());
            strBuf.append("\r\n");
            strBuf.append(e.toString());
            strBuf.append("\r\n");
            StackTraceElement[] stackTrace = e.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement t : stackTrace) {
                    strBuf.append(t.toString());
                    strBuf.append("\r\n");
                }
            }
        }
        strBuf.append("**********异常信息结束**********");
        strBuf.append("\r\n");
        //emailUtil.sendMail(e.toString(),strBuf.toString(),send);
        logger.error(strBuf.toString());
    }
}