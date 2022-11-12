package com.example.demo.service.impl;

import com.example.demo.api.cart.param.CartUpdateParam;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.common.WteException;
import com.example.demo.dao.CartinfoMapper;
import com.example.demo.entity.Cartinfo;

import com.example.demo.entity.Productinfo;
import com.example.demo.service.CartService;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartinfoMapper cartinfoMapper;

    @Override
    public String cartCreate(Cartinfo cartinfo) {
        if (cartinfoMapper.selectByUserIdAndProductId(cartinfo) != null) {
//            int num = cartinfo.getNum();
//            int newNum = num+1;
//            cartinfo.setNum(newNum);
            Cartinfo cartinfo1=cartinfoMapper.selectByUserIdAndProductId(cartinfo);
            int id=cartinfo1.getCartId();
            int num=cartinfo1.getNum();
            String str= id +","+ num;
            return ServiceResultEnum.SUCCESS.getResult() +str;
        }
        if (cartinfoMapper.insert(cartinfo) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean updateCartNum(CartUpdateParam updateParam) {
        int cartId = updateParam.getCartId();
//        int num = updateParam.getNum();
//        int change = updateParam.getChange();
        Cartinfo cartInfo = cartinfoMapper.selectByPrimaryCartKey(cartId);
        if (cartInfo == null) {
            WteException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
            return false;
        }
//        num = num+change;
        cartInfo.setNum(updateParam.getNum());
        if (cartinfoMapper.updateCartNum(cartInfo) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(int cartId) {
        cartinfoMapper.deleteById(cartId);
    }

    @Override
    public Cartinfo getCart(int cartId) {
        return cartinfoMapper.selectByPrimaryCartKey(cartId);
    }

    @Override
    public Cartinfo getCartInfo(int cartId) {
        return cartinfoMapper.selectCartInfo(cartId);
    }

    @Override
    public PageResult getCartProductsUserList(PageQueryUtil pageUtil) {
        List<Cartinfo> cartList = cartinfoMapper.getCartProductsUserList(pageUtil);
        int total = cartinfoMapper.getProductsCount(pageUtil);
        PageResult pageResult = new PageResult(cartList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
