package com.yh.kuangjia.core;

import com.yh.kuangjia.base.TokenAdmin;
import com.yh.kuangjia.base.TokenHelper;
import com.yh.kuangjia.base.TokenUser;
import com.yh.kuangjia.core.exception.MyTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseController {

    /**
     * ThreadLocal确保高并发下每个请求的request，response都是独立的
     */
    private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
    private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();

    /**
     * 线程安全初始化reque，respose对象
     * <p>
     * 每个Action执行前都会执行该方法
     * ModelAttribute的作用
     * 1)放置在方法的形参上：表示引用Model中的数据
     * 2)放置在方法上面：表示请求该类的每个Action前都会首先执行它，也可以将一些准备数据的操作放置在该方法里面。
     *
     * @param request
     * @param response
     */
    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) throws MyTokenException {
        currentRequest.set(request);
        currentResponse.set(response);
    }

    /**
     * 线程安全
     *
     * @return
     */
    public HttpServletRequest request() {
        return (HttpServletRequest) currentRequest.get();
    }

    /**
     * 线程安全
     *
     * @return
     */
    public HttpServletResponse response() {
        return (HttpServletResponse) currentResponse.get();
    }

    /**
     * 小程序端用户Token
     *
     * @return
     */
    public TokenUser GetTokenUser() {
        return TokenHelper.GetTokenUser(request());
    }

    /**
     * PC端管理用户Token
     *
     * @return
     */
    public TokenAdmin GetTokenAdmin() {
        return TokenHelper.GetTokenAdmin(request());
    }
}
