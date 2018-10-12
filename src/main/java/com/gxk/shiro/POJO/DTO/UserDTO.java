package com.gxk.shiro.POJO.DTO;

import com.gxk.shiro.POJO.DO.Permission;
import com.gxk.shiro.POJO.DO.Role;
import com.gxk.shiro.POJO.DO.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    /** 用户id */
    private String userId;

    /** 用户账号 */
    private String username;

    /** 用户密码 */
    private String password;

    /** 角色列表 */
    private List<Role> roleList;

    /** 权限列表 */
    private List<Permission> permissionList;
}
