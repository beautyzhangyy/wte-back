package com.example.demo.api.sellers.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class SellerLoginParam implements Serializable {
    @NotEmpty(message = "用户名不能为空")
    private String sellerName;

    @NotEmpty(message = "密码不能为空")
    private String sellerPassword;

    @NotEmpty(message = "店铺名不能为空")
    private String storeName;

    @NotEmpty(message = "地址不能为空")
    private String sellerAddress;

    @NotEmpty(message = "电话不能为空")
    private String sellerPhoneNum;

    private String sellTime;
}