package com.example.demo.api.users;

import com.example.demo.api.users.param.UserLoginParam;
import com.example.demo.api.users.param.UserUpdateParam;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户-个人服务
 * 注册、登录、修改头像、修改昵称、修改地址
 */
@RestController
@RequestMapping("/user")
public class PersonalAPI {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid UserLoginParam userLoginParam) {
        String loginResult = userService.login(userLoginParam.getUserName(), userLoginParam.getPassword());
        if (loginResult.contains("success")) {
            int userId = Integer.valueOf(loginResult.substring(7));
            Userinfo userinfo = userService.getByUserId(userId);
            return ResultGenerator.genSuccessResult(userinfo);
        }
        return  ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody @Valid UserLoginParam userLoginParam) {
        String registerResult = userService.register(userLoginParam.getUserName(), userLoginParam.getPassword());
        //注册成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //注册失败
        return ResultGenerator.genFailResult(registerResult);
    }

    @PostMapping("/updateInfo")
    public Result<String> updateInfo(@RequestBody @Valid UserUpdateParam userUpdateParam) {
        Boolean flag = userService.updateUserInfo(userUpdateParam);
        if (flag) {
            return ResultGenerator.genSuccessResult("修改成功");
        }else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }
}
