package com.yh.kuangjia.core.Interceptor;

import com.yh.kuangjia.base.Token;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.core.exception.MyTokenException;
import com.yh.kuangjia.util.JsonUtil;
import com.yh.kuangjia.util.security.AESUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
/*            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Headers","Content-Type,Access-Token");
            response.addHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS");
            response.addHeader("Access-Control-Allow-Origin","*");*/
            return true;
        }
        if (((HandlerMethod) handler).getMethodAnnotation(IgnoreLogin.class) != null) {
            return true;
        }
        try {
            String token = request.getHeader("Access-Token");
            if (token == null) {
                token = request.getParameter("token").replace(" ", "+");
                if (token == null) {
                    throw new MyTokenException("HEAD里面不存在Access-Token，请重新登录");
                }
            }

            String access_token = AESUtil.decrypt(token);
            if (access_token == null) {
                throw new MyTokenException("无效token，转换实体异常，请重新登录");
            }

            Date now = new Date();
            Token admin_user_token = JsonUtil.json2Object(access_token, Token.class);
            //token时间有效期验证
            if (now.compareTo(admin_user_token.getExpiredTime()) > 0) {
                throw new MyTokenException("无token，有效期已过，请重新登录");
            }
        } catch (Exception e) {
            throw new MyTokenException("无token，请重新登录" + e.toString());
        }
        return true;
    }
}