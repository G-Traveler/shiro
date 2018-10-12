package com.gxk.shiro.service;

import com.gxk.shiro.POJO.DTO.UserDTO;

import java.util.Map;

public interface LoginService {

    /**
     * 登录
     * @param
     * @return 用户通行token
     */
    Map<String, Object> login(String userId, String password);
}
