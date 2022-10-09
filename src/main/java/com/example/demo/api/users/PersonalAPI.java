package com.example.demo.api.users;

import com.example.demo.api.users.param.UserLoginParam;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户-个人服务
 * 注册、登录、修改头像、修改昵称、修改地址
 */
@RestController
@RequestMapping("/user")
public class PersonalAPI {

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid UserLoginParam userLoginParam) {

        Result result = ResultGenerator.genSuccessResult();
        return  result;
    }
}
