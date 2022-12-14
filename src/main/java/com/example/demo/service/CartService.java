package com.example.demo.service;

import com.example.demo.api.cart.param.CartUpdateParam;
import com.example.demo.entity.Cartinfo;
import com.example.demo.entity.Productinfo;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;

/**
 * 购物车的接口类
 */
public interface CartService {
    String cartCreate(Cartinfo cartinfo);

    Boolean updateCartNum(CartUpdateParam updateParam);

    void deleteById(int cartId);

    Cartinfo getCart(int cartId);

    Cartinfo getCartInfo(int cartId);

    PageResult getCartProductsUserList(PageQueryUtil pageUtil);
}
