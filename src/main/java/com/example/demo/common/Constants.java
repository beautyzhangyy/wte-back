package com.example.demo.common;

/**
 * 常量配置
 */
public class Constants {
    public final static String FILE_UPLOAD_DIC = "D:\\upload\\";//上传文件的默认url前缀，根据部署设置自行修改

    public final static String USER_HEAD_PIC_DIC = "D:\\upload\\userHeadPic\\";
    public final static String FRONT_USER_HEAD_PIC_URL = "/upload/userHeadPic/";    // 回传至前端的用户头像URL前缀

    public final static String SELLER_HEAD_PIC_DIC = "D:\\upload\\sellerHeadPic\\";
    public final static String FRONT_SELLER_HEAD_PIC_URL = "/upload/sellerHeadPic/";

    public final static String STORE_PIC_DIC = "D:\\upload\\storePic\\";
    public final static String FRONT_STORE_PIC_URL = "/upload/storePic/";

    public final static String STORE_LICENCE_PIC_DIC = "D:\\upload\\storeLicence\\";
    public final static String FRONT_STORE_LICENCE_PIC_URL = "/upload/storeLicence/";

    public final static String PRODUCT_PIC_DIC = "D:\\upload\\productSPic\\";
    public final static String FRONT_PRODUCT_PIC_URL = "/upload/productSPic/";


    public final static int PRODUCT_AT_LEAST_NUMBER = 10; // 首页新品展示数量
    public final static int INTELLIGENT_RECOMMEND = 10; // 智能推荐展示数量
    public final static int RANDOM_RECOMMEND = 10; // 随机推荐展示数量
    public final static int GOODS_SEARCH_PAGE_LIMIT = 15; // 搜索分页默认条数

    public final static int SELL_STATUS_UP = 1; // 上架状态
    public final static int SELL_STATUS_OFF = 0; // 下架状态
}
