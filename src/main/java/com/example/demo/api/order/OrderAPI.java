package com.example.demo.api.order;

import com.example.demo.api.cart.param.CartAddParam;
import com.example.demo.api.order.param.OrderCreateParam;
import com.example.demo.common.Constants;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Cartinfo;
import com.example.demo.entity.Orderinfo;
import com.example.demo.entity.Productinfo;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.SellerService;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单
 * 添加、查询
 */
@RestController
@RequestMapping("/order")
public class OrderAPI {

    @Resource
    private OrderService orderService;

    @PostMapping("/addOrder")
    public Result<String> createOrder(@RequestBody @Valid OrderCreateParam orderCreateParam) {
        Orderinfo orderinfo = new Orderinfo();
        orderinfo.setUserId(orderCreateParam.getUserId());
        orderinfo.setProductId(orderCreateParam.getProductId());

        String createResult = orderService.createOrder(orderinfo);
        if (ServiceResultEnum.SUCCESS.getResult().equals(createResult)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(createResult);
    }

    @GetMapping("/OrderProductsList")
    public Result<PageResult<List<Productinfo>>> orderProductsList(@RequestParam(required = false) Integer pageNumber, @RequestParam("userId") int userId) {
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("page", pageNumber);
        params.put("limit", Constants.PRODUCT_ALL_UP_NUMBER);
        //搜索上架状态下的商品
        params.put("userId", userId);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(orderService.getOrderProductsList(pageUtil));
    }
}
