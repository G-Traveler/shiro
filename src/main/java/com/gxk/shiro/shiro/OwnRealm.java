package com.gxk.shiro.shiro;

import com.gxk.shiro.POJO.DO.Permission;
import com.gxk.shiro.POJO.DO.Role;
import com.gxk.shiro.POJO.DTO.UserDTO;
import com.gxk.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OwnRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SimpleToken;
    }

    /**
     * 检测授权,如检查侧角色,检测permission等
     * @param
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取用户id
        String userId = principalCollection.toString();
        //获取DTO
        UserDTO userDTO = userService.findUserDTOByUserId(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //查询角色信息,暂时无用
//        for (Role role : userDTO.getRoleList()){
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//        }

        //查询UserDTO中List<Permission>中的Permission并交给AuthorizationInfo处理
        for (Permission permission : userDTO.getPermissionList()) {
            simpleAuthorizationInfo.addStringPermission(permission.getPermissionCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 使用此方法进行登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
                            AuthenticationToken authenticationToken) throws AuthenticationException {

        String token = (String) authenticationToken.getCredentials();
        //获取userId
        String userId = (String) authenticationToken.getPrincipal();
        if (userId == null)
            throw new AuthenticationException("Token无效");
        //根据userId查询redis中保存的token，对比是否相同
        if (!token.equals(redisTemplate.boundValueOps("dev_man:token:" + userId).get())) {
            throw new AuthenticationException("token验证失败");
        }
        return new SimpleAuthenticationInfo(userId, token, getName());
    }

}
