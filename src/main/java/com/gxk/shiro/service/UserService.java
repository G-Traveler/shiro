package com.gxk.shiro.service;

import com.gxk.shiro.POJO.DO.User;
import com.gxk.shiro.POJO.DTO.UserDTO;

public interface UserService {

    UserDTO findUserDTOByUserId(String userId);
}
