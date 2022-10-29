package com.example.demo.entity;

import lombok.Data ;

@Data
public class Productinfo {
    private int productId;

    private String productName;

    private float productPrice;

    private String productSPic;

    private String productBPic;

    private String productIntro;

    private int productStatus;

    private int productInventory;

    private int sellerId;
}
