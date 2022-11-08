package com.example.demo.api.products;

import com.example.demo.api.products.param.ProductUpdateParam;
import com.example.demo.api.products.param.ProductUploadParam;
import com.example.demo.common.Constants;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Productinfo;
import com.example.demo.service.ProductService;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品信息
 * 名称、价格、介绍、状态
 */
@RestController
@RequestMapping("/product")
public class ProductAPI {

    @Resource
    private ProductService productService;

    @PostMapping("/upload")
    public Result<String> productUpload(@RequestBody @Valid ProductUploadParam productUploadParam) {
        Productinfo productinfo = new Productinfo();
        productinfo.setProductName(productUploadParam.getProductName());
        productinfo.setProductPrice(productUploadParam.getProductPrice());
        productinfo.setProductIntro(productUploadParam.getProductIntro());
        productinfo.setProductStatus(productUploadParam.getProductStatus());
        productinfo.setProductInventory(productUploadParam.getProductInventory());
        productinfo.setSellerId(productUploadParam.getSellerId());

        String registerResult = productService.productUpload(productinfo);
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(registerResult);
    }

    @PostMapping("/updateInfo")
    public Result<String> updateSellerInfo(@RequestBody @Valid ProductUpdateParam productUpdateParam) {
        Boolean flag = productService.updateProductInfo(productUpdateParam);
        if (flag) {
            return ResultGenerator.genSuccessResult("修改成功");
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping("/uploadProductPic")
    public Result<String> uploadProductSPic(@RequestParam("file") MultipartFile file, @RequestParam("productName") String productName, @RequestParam("sellerId") int sellerId) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = productName + suffixName; // suffixName是后缀名，如 .png .jpg

        File fileDirectory = new File(Constants.PRODUCT_PIC_DIC);
        //创建文件
        File destFile = new File(Constants.PRODUCT_PIC_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);

            Productinfo productinfo = new Productinfo();
            productinfo.setProductName(productName);
            productinfo.setSellerId(sellerId);
            productinfo.setProductSPic(Constants.FRONT_PRODUCT_PIC_URL + newFileName);

            Boolean flag = productService.uploadProductSPic(productinfo);
            if (flag) {
                Result resultSuccess = ResultGenerator.genSuccessResult("上传商品图片成功");
                resultSuccess.setData(Constants.FRONT_PRODUCT_PIC_URL + newFileName);
                return resultSuccess;
            } else {
                return ResultGenerator.genFailResult("上传商品图片失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }

    @GetMapping("/ProductsAtLeast")
    public Result<PageResult<List<Productinfo>>> productsAtLeast(@RequestParam(required = false) Integer pageNumber) {
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("page", pageNumber);
        params.put("limit", Constants.PRODUCT_AT_LEAST_NUMBER);
        //搜索上架状态下的商品
        params.put("productStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(productService.getProductsList(pageUtil));
    }

    @GetMapping("/ProductsSellerList")
    public Result<PageResult<List<Productinfo>>> productsSellerList(@RequestParam(required = false) Integer pageNumber,@RequestParam("sellerId") int sellerId) {
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("page", pageNumber);
        params.put("limit", Constants.PRODUCT_ALL_UP_NUMBER);
        //搜索上架状态下的商品
        params.put("productStatus", Constants.SELL_STATUS_UP);
        params.put("productStatus", Constants.SELL_STATUS_OFF);
        params.put("sellerId", sellerId);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(productService.getProductsSellerList(pageUtil));
    }
}