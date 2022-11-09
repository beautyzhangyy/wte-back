package com.example.demo.api.cart.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartAddParam {
    private int userId;

    private int productId;

    private int num;
}
