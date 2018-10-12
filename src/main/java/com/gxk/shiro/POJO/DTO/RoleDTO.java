package com.gxk.shiro.POJO.DTO;

import com.gxk.shiro.POJO.DO.Permission;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {

    /** 角色id */
    private String roleId;
    /** 角色名称 */
    private String roleName;
    /** 角色权限 */
    private List<Permission> permissionList;
}
