package com.example.demo.api.cart.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartAddParam implements Serializable{
    private int userId;

    private int productId;

    private int num;

    private String productName;

    private float productPrice;

    private String productSPic;
}
