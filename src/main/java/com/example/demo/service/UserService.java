package com.example.demo.service;

import com.example.demo.api.users.param.UserUpdateParam;
import com.example.demo.entity.Userinfo;

import java.util.List;

/**
 * 用户服务的接口类
 */
public interface UserService {

    /**
     * 用户注册
     * @Param userName
     * @Param password
     */
    String register(String userName, String password);

    /**
     * 用户登录
     * @Param userName
     * @Param password
     */
    String login(String userName, String password);

    /**
     * 用户修改信息
     * @Param userName
     * @Param password
     */
    Boolean updateUserInfo(UserUpdateParam updateParam);

    /**
     * 用户修改信息
     * @Param userName
     * @Param password
     */
    Userinfo getByUserId(int userId);

    /**
     * 用户上传头像
     * @Param userName
     * @Param password
     */

}
