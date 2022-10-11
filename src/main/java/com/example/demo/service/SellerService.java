package com.example.demo.service;

import com.example.demo.api.sellers.param.SellerUpdateParam;
import com.example.demo.entity.Sellerinfo;

import java.util.List;

/**
 * 用户服务的接口类
 */
public interface SellerService {

    /**
     * 商家注册
     * ...
     */
    String sellerRegister(String sellerName, String sellerPassword,String storeName,
                    String sellerAddress,String sellerPhoneNum,String sellTime);

    /**
     * 商家登录
     */
    String sellerLogin(String sellerName, String sellerPassword);

    /**
     * 商家修改信息
     */
    Boolean updateSellerInfo(SellerUpdateParam updateParam);

    /**
     * 商家修改信息
     */
    Sellerinfo getBySellerId(int sellerId);

    /**
     * 商家上传头像
     */

}
