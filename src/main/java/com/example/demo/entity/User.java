package com.example.demo.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private String id;

    private String username;

    private String password;

    private String userdesc;

    private String telephone;

    private String email;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String reverse1;

    private String reverse2;

    private String reverse3;

    private String reverse4;

    private String reverse5;
}