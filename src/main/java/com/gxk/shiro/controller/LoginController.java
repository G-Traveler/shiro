package com.gxk.shiro.controller;

import com.gxk.shiro.POJO.VO.ResultVO;
import com.gxk.shiro.service.LoginService;
import com.gxk.shiro.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam("userId") String userId
                            , @RequestParam("password") String password) {
       Map<String,Object> objectMap =  loginService.login(userId, password);
        return ResultVOUtil.success(objectMap);
    }
}
