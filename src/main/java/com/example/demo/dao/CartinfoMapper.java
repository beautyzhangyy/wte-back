package com.example.demo.dao;

import com.example.demo.entity.Cartinfo;
import com.example.demo.entity.Productinfo;
import com.example.demo.util.PageQueryUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 给数据表提供原子操作的实现接口
 */
@Repository
public interface CartinfoMapper {
    int insert(Cartinfo record);

    void deleteById(int cartId);

    Cartinfo selectByPrimaryCartKey(int cartId);

    Cartinfo selectCartInfo(int cartId);

    Cartinfo selectByUserIdAndProductId(Cartinfo record);

    int updateCartNum(Cartinfo record);

    List<Cartinfo> getCartProductsUserList(PageQueryUtil pageUtil);

    int getProductsCount(PageQueryUtil pageUtil);
}
