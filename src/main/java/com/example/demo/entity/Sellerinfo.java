package com.example.demo.entity;

import lombok.Data;

@Data
public class Sellerinfo {
    private int sellerId;

    private String sellerName;

    private String sellerPassword;

    private String storeName;

    private String sellerHeadPic;

    private String sellerAddress;

    private String sellerPhoneNum;

    private String storePic;

    private String sellTime;    // 营业时间

    private String storeLicence;
}
