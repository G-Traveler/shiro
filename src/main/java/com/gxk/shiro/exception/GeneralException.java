package com.gxk.shiro.exception;

/**
 * 自定义异常类
 */
public class GeneralException extends RuntimeException{

    private Integer code;

    public GeneralException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

