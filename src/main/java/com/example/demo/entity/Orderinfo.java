package com.example.demo.entity;

import lombok.Data;

@Data
public class Orderinfo {
    private int orderId;

    private int cartId;

    private int userId;

    private String productName;

    private float productPrice;

    private String productSPic;

    private int num;
}
