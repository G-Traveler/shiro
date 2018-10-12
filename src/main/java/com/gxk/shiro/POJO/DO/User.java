package com.gxk.shiro.POJO.DO;

import lombok.Data;

/**
 * 用户类
 */
@Data
public class User {

    /** 用户id */
    private String userId;

    /** 用户账号 */
    private String password;

    /** 用户密码 */
    private String username;
}
