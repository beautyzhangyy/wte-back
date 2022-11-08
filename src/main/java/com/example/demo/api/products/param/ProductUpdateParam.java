package com.example.demo.api.products.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ProductUpdateParam implements Serializable {
    private int productId;

    private String productName;

    private float productPrice;

    private String productIntro;

    private int productStatus;

    private int productInventory;
}
