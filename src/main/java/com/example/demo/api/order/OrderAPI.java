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
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单
 * 添加、查询
 */
@RestController
@RequestMapping("/order")
public class OrderAPI {

    @Resource
    private OrderService orderService;

    @Resource
    private CartService cartService;

    @PostMapping("/addOrder")
    public Result<String> createOrder(@RequestBody @Valid OrderCreateParam orderCreateParam) {
        List<Integer> list= Arrays.stream(orderCreateParam.getCartIds().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            Orderinfo orderinfo = new Orderinfo();
            Cartinfo cartinfo = cartService.getCart(list.get(i));
            orderinfo.setCartId(list.get(i));
            orderinfo.setUserId(cartinfo.getUserId());
            orderinfo.setProductName(cartinfo.getProductName());
            orderinfo.setProductPrice(cartinfo.getProductPrice());
            orderinfo.setProductSPic(cartinfo.getProductSPic());
            orderinfo.setNum(cartinfo.getNum());
            String createResult = orderService.createOrder(orderinfo);
            if (!Objects.equals(ServiceResultEnum.SUCCESS.getResult(), createResult)){
                return ResultGenerator.genFailResult(createResult);
            }
        }
         return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/OrderProductsList")
    public Result<PageResult<List<Orderinfo>>> orderProductsList(@RequestParam(required = false) Integer pageNumber, @RequestParam("userId") int userId) {
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
