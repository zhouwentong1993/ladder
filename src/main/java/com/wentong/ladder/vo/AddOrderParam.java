package com.wentong.ladder.vo;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderParam {
    private String originId;
    private Double cargoPrice;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private Double receiverLng;
    private Double receiverLat;
    private String info;
    private Integer cargoType;
    private Integer cargoWeight;
    private Integer cargoNum;
    private Integer isInvoiced;
    private String invoiceTitle;
    private Integer deliveryServiceCode;
    private Integer coordinateType;
    // 订单类型 0: 及时单(尽快送达，限当日订单)、1: 预约单
    private Integer orderType;
    private Long expectedFinishTime;
    // 支付类型
    private Integer payType;
    private Integer productTypeNum;
    private String transportName;
    private String transportAddress;
    private Double transportLongitude;
    private Double transportLatitude;
    private String transportTel;
    private Double orderActualAmount;
    private Integer orderPaymentStatus;
    private Integer isAgentPayment;
    private List<FoodInfo> product;
    private Double tips;
    private String teamKey;

    // 外部订单号（火聚为例）
    private String thirdOrderId;

    //来源
    private String orderSource;
    //取货序号
    private String orderSequence;
    private String sassOrderNo;
    private String takeOutOrderKey;
    //通道报备表id
    private String reportId;
    private String isTianCai;
    private String pickUpType;
    private String personDirect;
    private String vehicleType;
    private String dropJibunAddress;
    private String pickupWishAt;

    private String takeoutShopId;

    private Integer mtWmOper;
    private String dropRoadAddress; // 法定道路地址（韩国平台专用）
    private Double totalDeliveryPrice; // 配送费（韩国平台专用）
    private Integer  transporterId; //配送员Id
    private String upstreamOrderKey;
    private Long orderPickupTime;
    private String insuranceFlag;
}
