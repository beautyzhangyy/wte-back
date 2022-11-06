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

    Productinfo selectByProductNameAndSellerId(Productinfo record);

    List<Productinfo> getProductsList(PageQueryUtil pageUtil);   // 获得指定页数、数量的商品数据

    int updateProductInfo(Productinfo record);

    int uploadProductSPic(Productinfo record);

    int getProductsCount(PageQueryUtil pageUtil);   // 获得当前查询结果的数量

    List<Productinfo> getProductsBySearch(PageQueryUtil pageUtil);  // 通过关键字查询的商品结果

}
