package com.example.demo.api.sellers;

import com.example.demo.api.sellers.param.SellerLoginParam;
import com.example.demo.api.sellers.param.SellerUpdateParam;
import com.example.demo.common.Constants;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Sellerinfo;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.SellerService;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

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
    @PostMapping("/uploadSellerHeadPic")
    public Result<String> uploadSellerHeadPic(@RequestParam("file") MultipartFile file, @RequestParam("sellerName") String sellerName, @RequestParam("sellerPassword") String password) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = sellerName + suffixName; // suffixName是后缀名，如 .png .jpg

        File fileDirectory = new File(Constants.SELLER_HEAD_PIC_DIC);
        //创建文件
        File destFile = new File(Constants.SELLER_HEAD_PIC_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);

            Sellerinfo sellerinfo = new Sellerinfo();
            sellerinfo.setSellerName(sellerName);
            sellerinfo.setSellerPassword(password);
            sellerinfo.setSellerHeadPic(Constants.FRONT_SELLER_HEAD_PIC_URL + newFileName);

            Boolean flag = sellerService.uploadSellerHeadPic(sellerinfo);
            if (flag) {
                Result resultSuccess = ResultGenerator.genSuccessResult("上传头像成功");
                resultSuccess.setData(Constants.FRONT_SELLER_HEAD_PIC_URL + newFileName);
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
