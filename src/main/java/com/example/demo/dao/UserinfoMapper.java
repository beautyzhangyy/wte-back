package com.example.demo.dao;

import com.example.demo.entity.Userinfo;
import org.apache.ibatis.annotations.Param;

/**
 * 给数据表提供原子操作的实现接口
 */
public interface UserinfoMapper {
    int insert(Userinfo record);

    Userinfo login(@Param("userName") String userName, @Param("password") String password);

    Userinfo selectByPrimaryKey(int userId);

    int updateByPrimaryKey(Userinfo record);

}
