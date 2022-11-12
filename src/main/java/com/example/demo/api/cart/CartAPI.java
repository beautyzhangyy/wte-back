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
import com.example.demo.service.ProductService;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 购物车
 * 添加、修改
 */
@RestController
@RequestMapping("/cart")
public class CartAPI {

    @Resource
    private CartService cartService;

    @Resource
    private ProductService productService;

    @PostMapping("/addCart")
    public Result<String> cartCreate(@RequestBody @Valid CartAddParam cartAddParam) {
        Cartinfo cartinfoTest = new Cartinfo();
        cartinfoTest.setUserId(cartAddParam.getUserId());
        cartinfoTest.setProductId(cartAddParam.getProductId());
        cartinfoTest.setNum(cartAddParam.getNum());
        Productinfo productinfo = productService.getProduct(cartAddParam.getProductId());
        cartinfoTest.setProductName(productinfo.getProductName());
        cartinfoTest.setProductPrice(productinfo.getProductPrice());
        cartinfoTest.setProductSPic(productinfo.getProductSPic());

        String createResult = cartService.cartCreate(cartinfoTest);
        if (ServiceResultEnum.SUCCESS.getResult().equals(createResult)) {
            Cartinfo cartinfo = new Cartinfo();
            cartinfo.setUserId(cartAddParam.getUserId());
            cartinfo.setProductId(cartAddParam.getProductId());
            cartinfo.setNum(cartAddParam.getNum());
            cartinfo.setProductName(productinfo.getProductName());
            cartinfo.setProductPrice(productinfo.getProductPrice());
            cartinfo.setProductSPic(productinfo.getProductSPic());
            return ResultGenerator.genSuccessResult();
        }
        else if (createResult.length()>7) {
            String idAndNum=createResult.substring(7);
            List<Integer> list= Arrays.stream(idAndNum.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            int cartId=list.get(0);
            int cartNum=list.get(1);
            CartUpdateParam cartUpdateParam=new CartUpdateParam();
            cartUpdateParam.setCartId(cartId);
            cartUpdateParam.setUserId(cartAddParam.getUserId());
            cartUpdateParam.setProductId(cartAddParam.getProductId());
            cartUpdateParam.setNum(cartAddParam.getNum()+cartNum);
            Boolean flag = cartService.updateCartNum(cartUpdateParam);
            if (flag) {
                return ResultGenerator.genSuccessResult("修改成功");
            } else {
                return ResultGenerator.genFailResult("修改失败");
            }
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
    public Result<PageResult<List<Cartinfo>>> cartProductsUserList(@RequestParam(required = false) Integer pageNumber, @RequestParam("userId") int userId) {
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
        return ResultGenerator.genSuccessResult(cartService.getCartProductsUserList(pageUtil));
    }

    @GetMapping("/delete")
    public Result<String> deleteById(@RequestParam("cartId") int cartId) {
        cartService.deleteById(cartId);
        return ResultGenerator.genSuccessResult("删除成功");
    }

    @GetMapping("/getCartInfo")
    public Result<String> getCartInfo(@RequestParam("cartId") int cartId) {
        Cartinfo cartinfo = cartService.getCartInfo(cartId);
        return ResultGenerator.genSuccessResult(cartinfo);
    }
}
