package com.gxk.shiro.POJO.DO;

import lombok.Data;

/**
 * 权限
 */
@Data
public class Permission {

    /** 权限id */
    private String permissionId;
    /** 权限来源 */
    private String resource;
    /** 权限代码 */
    private String permissionCode;
}
