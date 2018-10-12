package com.gxk.shiro.constant;

import lombok.Getter;

/**
 * 结果枚举类
 */
@Getter
public enum ResultEnum {

    LOGIN_ERROR(-1, "账号或密码错误"),
    SUCCESS(0,"操作成功")
    ;

    /** 信息代码 */
    private Integer code;
    /** 包含的信息 */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
