package com.gxk.shiro.service;

import com.gxk.shiro.POJO.DO.User;
import com.gxk.shiro.POJO.DTO.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void login() {
        Map<String, Object> map = loginService.login("201604006", "123456");
    }
}