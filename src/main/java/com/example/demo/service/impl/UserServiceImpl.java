package com.example.demo.service.impl;

import com.example.demo.api.users.param.UserUpdateParam;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.common.WteException;
import com.example.demo.dao.UserinfoMapper;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public String register(String userName, String password) {
        if(userinfoMapper.selectByUserName(userName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        Userinfo userinfo = new Userinfo();
        userinfo.setUserName(userName);
        userinfo.setPassword(password);
        userinfo.setNickname(userName);    // 初始昵称与用户名一致
        if (userinfoMapper.insert(userinfo) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String userName, String password) {
        Userinfo userinfo = userinfoMapper.login(userName, password);
        if (userinfo != null){
            return ServiceResultEnum.SUCCESS.getResult() + userinfo.getUserId();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public Boolean updateUserInfo(UserUpdateParam updateParam) {
        int userId = updateParam.getUserId();
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(userId);
        if (userinfo == null) {
            WteException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
            return false;
        }
        userinfo.setPassword(updateParam.getPassword());
        userinfo.setNickname(updateParam.getNickname());
        userinfo.setUserAddress(updateParam.getUserAddress());
        userinfo.setPhoneNum(updateParam.getPhoneNum());
        if (userinfoMapper.updateInfo(userinfo) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 数据表中包含userId对应记录时，才可使用该函数
     * @param userId
     * @return
     */
    @Override
    public Userinfo getByUserId(int userId) {
        return userinfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Boolean uploadHeadPic(Userinfo userinfo) {
        if (userinfoMapper.uploadHeadPic(userinfo) > 0) {
            return true;
        }
        return false;
    }
}
