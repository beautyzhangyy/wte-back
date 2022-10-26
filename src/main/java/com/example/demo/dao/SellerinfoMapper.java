package com.example.demo.dao;

import com.example.demo.entity.Sellerinfo;
import com.example.demo.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 给数据表提供原子操作的实现接口
 */
@Repository
public interface SellerinfoMapper {
    int insert(Sellerinfo record);

    Sellerinfo sellerLogin(@Param("sellerName") String sellerName, @Param("sellerPassword") String sellerPassword);

    Sellerinfo selectByPrimarySellerKey(int sellerId);

    Sellerinfo selectBySellerName(String sellerName);

    List<Sellerinfo> getSellerList(PageQueryUtil pageUtil);

    int updateSellerInfo(Sellerinfo record);

    int uploadSellerHeadPic(Sellerinfo record);
}
