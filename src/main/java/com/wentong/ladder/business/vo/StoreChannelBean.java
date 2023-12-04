package com.wentong.ladder.business.vo;

import lombok.Data;

@Data
public class StoreChannelBean {

    private Integer id;

    private String thirdGroupid;


    private String thirdShopid;


    private String appKey;


    private String secret;


    private String transportName;


    private String city;


    private String cityCode;


    private String province;


    private String transportAddress;

    private String transportLongitude;


    private String transportLatitude;


    private String transportTel;


    private Integer positionSource;


    private String deliveryServiceCode;


    private Integer platformType;


    private String subPlatformType;

    private Integer auditStatus;

    private String equityAccount;
    //1间连2直连
    private Integer type;

    //达达的集团id,美团的服务code,闪送的shopId,点我达的accesstoken,barogo的devUserId
    private String special_key;

    /**
     * 三方商品类型
     */
    private String thirdProductType;

    private String isplayArea;

    private Integer isgroupByding;
    private String areaName;

    private Integer isYuRechage;

    private Integer isYRAcount;
}
