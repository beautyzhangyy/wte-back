package com.example.demo.service.impl;

import com.example.demo.api.sellers.param.SellerUpdateParam;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.common.WteException;
import com.example.demo.dao.SellerinfoMapper;
import com.example.demo.entity.Sellerinfo;
import com.example.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerinfoMapper sellerinfoMapper;

    @Override
    public String sellerRegister(String sellerName, String sellerPassword,String storeName,
                                 String sellerAddress,String sellerPhoneNum,String sellTime) {
        if(sellerinfoMapper.selectBySellerName(sellerName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        Sellerinfo sellerinfo = sellerinfoMapper.sellerRegister(sellerName,sellerPassword,storeName,
                sellerAddress,sellerPhoneNum,sellTime);
        if (sellerinfoMapper.insert(sellerinfo) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }


    @Override
    public String sellerLogin(String sellerName, String sellerPassword) {
        Sellerinfo sellerinfo = sellerinfoMapper.sellerLogin(sellerName, sellerPassword);
        if (sellerinfo != null){
            return ServiceResultEnum.SUCCESS.getResult() + sellerinfo.getSellerId();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public Boolean updateSellerInfo(SellerUpdateParam updateParam) {
        int sellerId = updateParam.getSellerId();
        Sellerinfo sellerinfo = sellerinfoMapper.selectByPrimarySellerKey(sellerId);
        if (sellerinfo == null) {
            WteException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
            return false;
        }
        sellerinfo.setSellerPassword(updateParam.getSellerPassword());
        sellerinfo.setStoreName(updateParam.getStoreName());
        sellerinfo.setSellerAddress(updateParam.getSellerAddress());
        sellerinfo.setSellerPhoneNum(updateParam.getSellerPhoneNum());
        sellerinfo.setSellTime(updateParam.getSellTime());
        if (sellerinfoMapper.updateSellerInfo(sellerinfo) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 数据表中包含sellerId对应记录时，才可使用该函数
     * @return
     */
    @Override
    public Sellerinfo getBySellerId(int sellerId) {
        return sellerinfoMapper.selectByPrimarySellerKey(sellerId);
    }
}