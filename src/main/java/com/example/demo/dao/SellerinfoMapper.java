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

    Sellerinfo sellerRegister(@Param("sellerName") String sellerName, @Param("password") String sellerPassword,@Param("storeName") String storeName,
                              @Param("sellerAddress") String sellerAddress,@Param("sellerPhoneNum") String sellerPhoneNum,@Param("sellTime") String sellTime);

    Sellerinfo sellerLogin(@Param("sellerName") String sellerName, @Param("password") String sellerPassword);

    Sellerinfo selectByPrimarySellerKey(int sellerId);

    Sellerinfo selectBySellerName(String sellerName);

    List<Sellerinfo> getSellerList(PageQueryUtil pageUtil);

    int updateSellerInfo(Sellerinfo record);

    String uploadSellerHead(@Param("sellerId") int sellerId,@Param("sellerHeadPic") String sellerHeadPic);
}
