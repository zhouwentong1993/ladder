package com.wentong.ladder.vo;

import lombok.Data;

@Data
public class TemporaryModel {

    private Integer errorCode;
    private String errMsg;
    private String thirdOrderID;
    private String cityName;
    private String cityCode;
    private String thirdShopID;
    private StoreChannelBean storeChannelBean;

    //蜂鸟配送费
    private Double fee;
    //美团
    /**
     * 收件人经度（高德坐标），高德坐标 * 10^6
     */
    private Integer receiverLng;

    /**
     * 收件人纬度（高德坐标），高德坐标 * 10^6
     */
    private Integer receiverLat;

    //蜂鸟和哒哒
    /**
     * 收件人经度（高德坐标），高德坐标 * 10^6
     */
    private Double fNReceiverLng;

    /**
     * 收件人纬度（高德坐标），高德坐标 * 10^6
     */
    private Double fNReceiverLat;

    //快跑者 客户地址
    private String customer_tag;

    //1是新 其他来源是老服务
    private int isNew;

}
