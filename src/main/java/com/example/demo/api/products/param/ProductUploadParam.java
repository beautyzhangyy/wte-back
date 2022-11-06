package com.example.demo.api.products.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ProductUploadParam implements Serializable {
    @NotEmpty(message = "商品名不能为空")
    private String productName;

    @NotEmpty(message = "商品价格不能为空")
    private float productPrice;

    @NotEmpty(message = "商品介绍不能为空")
    private String productIntro;

    @NotEmpty(message = "商品上架状态不能为空")
    private int productStatus;

    @NotEmpty(message = "商品库存不能为空")
    private int productInventory;

    @NotEmpty(message = "卖家ID缺失")
    private int sellerId;

}






