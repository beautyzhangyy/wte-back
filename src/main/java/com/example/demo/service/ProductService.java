package com.example.demo.service;

import com.example.demo.api.products.param.ProductUpdateParam;
import com.example.demo.entity.Productinfo;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;

/**
 * 产品服务的接口类
 */
public interface ProductService {
    String productUpload(Productinfo productInfo);

    Boolean updateProductInfo(ProductUpdateParam updateParam);

    Productinfo getByProductId(int productId);

    Boolean uploadProductSPic(Productinfo productInfo);

    PageResult getProductsList(PageQueryUtil pageUtil);

    PageResult getProductsBySearch(PageQueryUtil pageUtil);
}
