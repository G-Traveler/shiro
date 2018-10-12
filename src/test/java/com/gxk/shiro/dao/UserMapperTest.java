package com.gxk.shiro.dao;

import com.gxk.shiro.POJO.DO.User;
import com.gxk.shiro.POJO.DTO.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUserDTOByUserId() {
        UserDTO userDTO = userMapper.findUserDTOByUserId("201604006");
        log.info("[UserDTO] 该类内容为: userDTO = {}",userDTO);
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void login() {
        User user = userMapper.loginByUser("201604006", "123456");
        log.info("[登录信息:] user={}", user);
        Assert.assertNotNull(user);
    }
}