package com.example.demo.api.products.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ProductUploadParam implements Serializable {
    @NotEmpty(message = "产品名不能为空")
    private String productName;

    @NotEmpty(message = "产品价格不能为空")
    private float productPrice;

    @NotEmpty(message = "产品介绍不能为空")
    private String productIntro;

    @NotEmpty(message = "产品上架状态不能为空")
    private int productStatus;

    @NotEmpty(message = "产品库存不能为空")
    private int productInventory;
}






