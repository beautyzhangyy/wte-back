package com.example.demo.api.products;

import com.example.demo.api.products.param.ProductUpdateParam;
import com.example.demo.api.products.param.ProductUploadParam;
import com.example.demo.common.Constants;
import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Productinfo;
import com.example.demo.service.ProductService;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

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
        String registerResult = productService.productUpload(productUploadParam.getProductName(), productUploadParam.getProductPrice(),
                productUploadParam.getProductIntro(),productUploadParam.getProductInventory(),productUploadParam.getProductStatus());
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
        }else {
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
                Result resultSuccess = ResultGenerator.genSuccessResult("上传头像成功");
                resultSuccess.setData(Constants.FRONT_PRODUCT_PIC_URL + newFileName);
                return resultSuccess;
            }else {
                return ResultGenerator.genFailResult("上传头像失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }
}