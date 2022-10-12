package com.example.demo.api.sellers;

import com.example.demo.api.sellers.param.SellerLoginParam;
import com.example.demo.api.sellers.param.SellerUpdateParam;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Sellerinfo;
import com.example.demo.service.SellerService;
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
@RequestMapping("/seller")
public class SellerAPI {

    @Resource
    private SellerService sellerService;

    @PostMapping("/login")
    public Result<String> sellerLogin(@RequestBody @Valid SellerLoginParam sellerLoginParam) {
        String loginResult = sellerService.sellerLogin(sellerLoginParam.getSellerName(), sellerLoginParam.getSellerPassword());
        if (loginResult.contains("success")) {
            int sellerId = Integer.valueOf(loginResult.substring(7));
            Sellerinfo sellerinfo = sellerService.getBySellerId(sellerId);
            return ResultGenerator.genSuccessResult(sellerinfo);
        }
        return  ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/register")
    public Result<String> sellerRegister(@RequestBody @Valid SellerLoginParam sellerLoginParam) {
        String registerResult = sellerService.sellerRegister(sellerLoginParam.getSellerName(), sellerLoginParam.getSellerPassword(),
                sellerLoginParam.getStoreName(),sellerLoginParam.getSellerAddress(),sellerLoginParam.getSellerPhoneNum(),sellerLoginParam.getSellTime());
        //注册成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //注册失败
        return ResultGenerator.genFailResult(registerResult);
    }

    @PostMapping("/updateInfo")
    public Result<String> updateSellerInfo(@RequestBody @Valid SellerUpdateParam sellerUpdateParam) {
        Boolean flag = sellerService.updateSellerInfo(sellerUpdateParam);
        if (flag) {
            return ResultGenerator.genSuccessResult("修改成功");
        }else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }
}
