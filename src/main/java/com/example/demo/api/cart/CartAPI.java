package com.example.demo.api.cart;

import com.example.demo.api.cart.param.CartUpdateParam;
import com.example.demo.api.cart.param.CartAddParam;
import com.example.demo.api.products.param.ProductUpdateParam;
import com.example.demo.api.sellers.param.SellerLoginParam;
import com.example.demo.common.Constants;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Cartinfo;
import com.example.demo.entity.Productinfo;
import com.example.demo.service.CartService;
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
 * 购物车
 * 添加、修改
 */
@RestController
@RequestMapping("/cart")
public class CartAPI {

    @Resource
    private CartService cartService;

    @PostMapping("/addCart")
    public Result<String> cartCreate(@RequestBody @Valid CartAddParam cartAddParam) {
        Cartinfo cartinfoTest = new Cartinfo();
        cartinfoTest.setUserId(cartAddParam.getUserId());
        cartinfoTest.setProductId(cartAddParam.getProductId());
        cartinfoTest.setNum(cartAddParam.getNum());

        String createResult = cartService.cartCreate(cartinfoTest);
        if (ServiceResultEnum.SUCCESS.getResult().equals(createResult)) {
            Cartinfo cartinfo = new Cartinfo();
            cartinfo.setUserId(cartAddParam.getUserId());
            cartinfo.setProductId(cartAddParam.getProductId());
            cartinfo.setNum(cartAddParam.getNum());
            return ResultGenerator.genSuccessResult();
        }
        else if (createResult.matches("[0-9]+")) {
            int cartId=Integer.parseInt(createResult);
            CartUpdateParam cartUpdateParam=new CartUpdateParam();
            cartUpdateParam.setCartId(cartId);
            cartUpdateParam.setUserId(cartAddParam.getUserId());
            cartUpdateParam.setProductId(cartAddParam.getProductId());
            cartUpdateParam.setNum(cartAddParam.getNum());
            return updateCartNum(cartUpdateParam);
        }
        return ResultGenerator.genFailResult(createResult);
    }

    @PostMapping("/updateCart")
    public Result<String> updateCartNum(@RequestBody @Valid CartUpdateParam cartUpdateParam) {
        Boolean flag = cartService.updateCartNum(cartUpdateParam);
        if (flag) {
            return ResultGenerator.genSuccessResult("修改成功");
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @GetMapping("/CartProductsUserList")
    public Result<PageResult<List<Productinfo>>> cartProductsUserList(@RequestParam(required = false) Integer pageNumber, @RequestParam("userId") int userId) {
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("page", pageNumber);
        params.put("limit", Constants.PRODUCT_ALL_UP_NUMBER);
        //搜索上架状态下的商品
        params.put("num", Constants.CART_NUM_NULL);
        params.put("userId", userId);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(cartService.getCartProductsUserList(pageUtil));
    }
}
