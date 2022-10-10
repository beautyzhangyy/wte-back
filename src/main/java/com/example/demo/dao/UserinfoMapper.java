package com.example.demo.dao;

import com.example.demo.entity.Userinfo;
import com.example.demo.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 给数据表提供原子操作的实现接口
 */
@Repository
public interface UserinfoMapper {
    int insert(Userinfo record);

    Userinfo login(@Param("userName") String userName, @Param("password") String password);

    Userinfo selectByPrimaryKey(int userId);

    Userinfo selectByUserName(String userName);

    List<Userinfo> getUserList(PageQueryUtil pageUtil);

    int updateInfo(Userinfo record);

    String uploadHead(@Param("userId") int userId,@Param("headPic") String headPic);
}
