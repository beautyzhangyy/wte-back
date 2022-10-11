package com.example.demo.api.sellers.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class SellerUpdateParam implements Serializable {
    private int sellerId;

    private String sellerPassword;

    private String storeName;

    private String sellerAddress;

    private String sellerPhoneNum;

    private String sellTime;
}
