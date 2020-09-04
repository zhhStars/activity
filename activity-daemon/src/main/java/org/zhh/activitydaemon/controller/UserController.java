package org.zhh.activitydaemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhh.activitydaemon.entity.User;
import org.zhh.activitydaemon.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{id}")
    public Object get(@PathVariable(value = "id") String id){
        User user = userService.getById(id);
        System.out.println(user);
        return user;
    }
}
