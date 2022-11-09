package com.example.demo.api.cart.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartUpdateParam implements Serializable{
    private int cartId;

    private int userId;

    private int productId;

    private int num;
}
