package com.example.demo.api.users;

import com.example.demo.api.users.param.UserLoginParam;
import com.example.demo.api.users.param.UserUpdateParam;
import com.example.demo.common.Constants;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;

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

    @PostMapping("/uploadHeadPic")
    public Result<String> uploadHeadPic(@RequestParam("file") MultipartFile file, @RequestParam("userName") String userName,@RequestParam("password") String password) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = userName + suffixName; // suffixName是后缀名，如 .png .jpg

        File fileDirectory = new File(Constants.USER_HEAD_PIC_DIC);
        //创建文件
        File destFile = new File(Constants.USER_HEAD_PIC_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);

            Userinfo userinfo = new Userinfo();
            userinfo.setUserName(userName);
            userinfo.setPassword(password);
            userinfo.setHeadPic(Constants.FRONT_USER_HEAD_PIC_URL + newFileName);

            Boolean flag = userService.uploadHeadPic(userinfo);
            if (flag) {
                Result resultSuccess = ResultGenerator.genSuccessResult("上传头像成功");
                resultSuccess.setData(Constants.FRONT_USER_HEAD_PIC_URL + newFileName);
                return resultSuccess;
            }else {
                return ResultGenerator.genFailResult("上传头像失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }
}
