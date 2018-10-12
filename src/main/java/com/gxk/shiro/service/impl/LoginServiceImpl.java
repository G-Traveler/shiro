package com.gxk.shiro.service.impl;

import com.gxk.shiro.POJO.DO.User;
import com.gxk.shiro.POJO.DTO.UserDTO;
import com.gxk.shiro.constant.ResultEnum;
import com.gxk.shiro.dao.UserMapper;
import com.gxk.shiro.exception.GeneralException;
import com.gxk.shiro.service.LoginService;
import com.gxk.shiro.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Map<String, Object> login(String userId, String password) {

        //用户登录
        User user = userMapper.loginByUser(userId, password);

        if (user == null) {
            log.error("【登陆失败！请重新核对账号密码】");
            throw new GeneralException(ResultEnum.LOGIN_ERROR.getCode()
                    , ResultEnum.LOGIN_ERROR.getMessage());
        }
        //利用登录成功的User转换成UserDTO对象用于传输数据
        UserDTO userDTO = userMapper.findUserDTOByUserId(user.getUserId());
        //根据userId，密码，token过期时间生成token
        String token = JWTUtil.sign(userDTO.getUserId(), userDTO.getPassword(), 30);
        //存入redis
        redisTemplate.boundValueOps("dev_man:token:" + userDTO.getUserId())
                .set(token, 30, TimeUnit.DAYS);
        //返回给客户端
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userDTO);
        res.put("token", token);

        return res;
    }

}

