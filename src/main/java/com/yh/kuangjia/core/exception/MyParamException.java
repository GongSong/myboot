package com.yh.kuangjia.core.exception;

/**
 * 之所以自定义该异常，而不用{@link org.springframework.web.bind.MissingServletRequestParameterException},
 * 原因在于，MissingServletRequestParameterException为Checked异常,在动态代理过程中，
 * 很容易引发{@link java.lang.reflect.UndeclaredThrowableException}异常
 *
 */
public class MyParamException extends RuntimeException {
    private final String message;

    public MyParamException(String message) {
        super("");
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
