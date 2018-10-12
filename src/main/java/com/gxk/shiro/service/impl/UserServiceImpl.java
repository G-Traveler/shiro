package com.gxk.shiro.service.impl;

import com.gxk.shiro.POJO.DO.User;
import com.gxk.shiro.POJO.DTO.UserDTO;
import com.gxk.shiro.dao.UserMapper;
import com.gxk.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO findUserDTOByUserId(String userId) {
        return userMapper.findUserDTOByUserId(userId);
    }

}
