package com.example.demo.service;

import com.example.demo.api.order.param.OrderCreateParam;
import com.example.demo.entity.Orderinfo;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;

/**
 * 订单管理的接口类
 */
public interface OrderService {
    String createOrder(Orderinfo orderinfo);

    PageResult getOrderProductsList(PageQueryUtil pageUtil);
}
