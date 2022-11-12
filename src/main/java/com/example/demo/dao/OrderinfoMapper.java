package com.example.demo.dao;

import com.example.demo.entity.Cartinfo;
import com.example.demo.entity.Orderinfo;
import com.example.demo.entity.Productinfo;
import com.example.demo.entity.Userinfo;
import com.example.demo.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 给数据表提供原子操作的实现接口
 */
@Repository
public interface OrderinfoMapper {
    int insert(Orderinfo record);

    Cartinfo selectByPrimaryOrderKey(int orderId);

    List<Orderinfo> getCartProductsOrderList(PageQueryUtil pageUtil);

    int getProductsCount(PageQueryUtil pageUtil);
}
