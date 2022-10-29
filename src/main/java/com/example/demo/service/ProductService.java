package com.example.demo.service;

import com.example.demo.api.products.param.ProductUpdateParam;
import com.example.demo.entity.Productinfo;

/**
 * 产品服务的接口类
 */
public interface ProductService {
    String productUpload(String productName, float productPrice, String productIntro, int productInventory, int productStatus);

    Boolean updateProductInfo(ProductUpdateParam updateParam);

    Productinfo getByProductId(int productId);

    Boolean uploadProductSPic(Productinfo productInfo);
}
