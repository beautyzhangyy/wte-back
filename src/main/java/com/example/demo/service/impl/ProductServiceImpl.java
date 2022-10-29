package com.example.demo.service.impl;

import com.example.demo.api.products.param.ProductUpdateParam;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.common.WteException;
import com.example.demo.dao.ProductinfoMapper;
import com.example.demo.entity.Productinfo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductinfoMapper productinfoMapper;

    @Override
    public String productUpload(String productName, float productPrice, String productIntro,
                                int productInventory, int productStatus) {
        if(productinfoMapper.selectByProductName(productName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        Productinfo productinfo = new Productinfo();
        productinfo.setProductName(productName);
        productinfo.setProductPrice(productPrice);
        productinfo.setProductIntro(productIntro);
        productinfo.setProductInventory(productInventory);
        productinfo.setProductStatus(productStatus);
        if (productinfoMapper.insert(productinfo) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean updateProductInfo(ProductUpdateParam updateParam) {
        int productId = updateParam.getProductId();
        Productinfo productInfo = productinfoMapper.selectByPrimaryProductKey(productId);
        if (productInfo == null) {
            WteException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
            return false;
        }
        productInfo.setProductName(updateParam.getProductName());
        productInfo.setProductPrice(updateParam.getProductPrice());
        productInfo.setProductIntro(updateParam.getProductIntro());
        productInfo.setProductInventory(updateParam.getProductInventory());
        productInfo.setProductStatus(updateParam.getProductStatus());
        if (productinfoMapper.updateProductInfo(productInfo) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Productinfo getByProductId(int productId) {
        return productinfoMapper.selectByPrimaryProductKey(productId);
    }


    @Override
    public Boolean uploadProductSPic(Productinfo productInfo) {
        if (productinfoMapper.uploadProductSPic(productInfo) > 0) {
            return true;
        }
        return false;
    }
}
