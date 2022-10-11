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
}