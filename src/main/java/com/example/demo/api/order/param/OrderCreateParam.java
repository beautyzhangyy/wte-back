package com.example.demo.api.order.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderCreateParam implements Serializable{
    private int userId;

    private int productId;
}
