package com.wentong.ladder.business.vo;

import lombok.Data;

@Data
public class FoodInfo {
    private String productName;    //菜品名称
    private Integer productNum;     //菜品数量
    private Double productPrice;    //商品原价
    private Double itemActualPrice; //商品实际价格
    private String remark;          //备注
}
