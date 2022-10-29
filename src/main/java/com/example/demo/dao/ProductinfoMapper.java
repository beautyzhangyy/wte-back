package com.example.demo.dao;

import com.example.demo.entity.Productinfo;
import com.example.demo.util.PageQueryUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 给数据表提供原子操作的实现接口
 */
@Repository
public interface ProductinfoMapper {
    int insert(Productinfo record);

    Productinfo selectByPrimaryProductKey(int productId);

    Productinfo selectByProductName(String productName);

    List<Productinfo> getProductList(PageQueryUtil pageUtil);

    int updateProductInfo(Productinfo record);

    int uploadProductSPic(Productinfo record);
}
