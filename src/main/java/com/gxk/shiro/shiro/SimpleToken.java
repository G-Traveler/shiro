package com.gxk.shiro.shiro;

import com.gxk.shiro.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationToken;

public class SimpleToken implements AuthenticationToken {

    private String token;

    public SimpleToken(String token) {
        this.token = token;
    }

    /**
     * @return
     */
    @Override
    public Object getPrincipal() {
        return JWTUtil.getUserId(token);
    }

    /**
     * @return 返回token
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
