package com.example.demo.service.impl;

import com.example.demo.api.cart.param.CartUpdateParam;
import com.example.demo.api.order.param.OrderCreateParam;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.common.WteException;
import com.example.demo.dao.CartinfoMapper;
import com.example.demo.dao.OrderinfoMapper;
import com.example.demo.entity.Cartinfo;
import com.example.demo.entity.Orderinfo;

import com.example.demo.entity.Productinfo;
import com.example.demo.service.OrderService;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderinfoMapper orderinfoMapper;

    @Override
    public String createOrder(Orderinfo orderinfo) {
        if (orderinfoMapper.insert(orderinfo) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public PageResult getOrderProductsList(PageQueryUtil pageUtil) {
        List<Orderinfo> orderList = orderinfoMapper.getOrderProductsList(pageUtil);
        int total = orderinfoMapper.getProductsCount(pageUtil);
        PageResult pageResult = new PageResult(orderList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
