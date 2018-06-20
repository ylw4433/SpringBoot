package com.example.demo.service;


import com.example.demo.entity.User;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface UserService {

//	public SysUser findByUserName(String username);
    public List<User> findUserInfo();
    
    public List<User> findUserInfoById(String id);
    
    public int del(String id);
    public int del();
    
    public int updateUserInfo(User user);
    
    public int addUser(User user);
}
