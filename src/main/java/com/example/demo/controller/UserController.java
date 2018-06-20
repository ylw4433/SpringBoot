package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @RequestMapping("/getUserInfo")
//    public List<User> getUserInfo(){
//        List<User> user = userService.findUserInfo();
//        System.out.println(user);
//        return user;
//    }

    @RequestMapping("getUserInfoById")
    public List<User> getUserInfoById(HttpServletRequest request){
        List<User> user = userService.findUserInfoById(request.getParameter("id"));
        System.out.println(user);
        return user;
    }
    
}
