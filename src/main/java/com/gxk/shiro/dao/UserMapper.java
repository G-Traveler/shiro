package com.gxk.shiro.dao;

import com.gxk.shiro.POJO.DO.User;
import com.gxk.shiro.POJO.DTO.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;

public interface UserMapper {

    //登录
    User loginByUser(@Param("userId") String userId, @Param("password") String password);

    //根据用户id查询UserDTO信息,此次用来做测试
    UserDTO findUserDTOByUserId(@Param("userId") String userId);

    //查询信息

    //增加数据

    //删除数据

    //更新数据

}
