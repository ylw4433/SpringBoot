package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> findUserInfo();

    public List<User> findUserInfoById(String id);
    
    public int del(String id);
    
    public int updateUserInfo(User user);
    
    public int addUser(User user);
}
