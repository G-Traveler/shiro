package com.gxk.shiro.controller;

import com.gxk.shiro.constant.PermisionContants;
import com.gxk.shiro.constant.ResourceConstants;
import com.gxk.shiro.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户id删除用户
     * @param userId
     */
    @GetMapping("/delete/{userId}")
    @RequiresPermissions(ResourceConstants.USER + PermisionContants.DELETE)
    public void deleteUserByUserId(@PathVariable("userId") String userId) {
        System.out.println("可以删除");
//        userService.findUserDTOByUserId(userId);
    }

}
