package com.example.demo.api.users.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateParam implements Serializable {
    private int userId;

    private String password;

    private String nickname;

    private String userAddress;

    private String phoneNum;

}
