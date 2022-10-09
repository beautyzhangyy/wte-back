package com.example.demo.entity;

import lombok.Data;

@Data
public class Userinfo {
    private int userId;

    private String userName;

    private String password;

    private String nickname;

    private String headPic;

    private String userAddress;
}
