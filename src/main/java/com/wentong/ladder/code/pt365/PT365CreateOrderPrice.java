package com.wentong.ladder.code.pt365;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;

/**
 * Auto generated class.
 * DO NOT MODIFY!!!
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedClass
public class PT365CreateOrderPrice {

    @MappedField(expression = "temporaryModel.storeChannelBean.special_key", desc = "扣款的365账号", type = MappedType.EXPRESSION)
    private String user365Id;
    @MappedField(expression = "str(temporaryModel.storeChannelBean.transportLongitude)", desc = "发货地坐标经度", type = MappedType.EXPRESSION)
    private String senderAddressLon;
    @MappedField(expression = "temporaryModel.storeChannelBean.transportLatitude", desc = "发货地坐标纬度", type = MappedType.EXPRESSION)
    private String senderAddressLat;
    @MappedField(expression = "str(addOrderParam.receiverLng)", desc = "收货地坐标经度", type = MappedType.EXPRESSION)
    private String receiverAddressLon;
    @MappedField(expression = "str(addOrderParam.receiverLat)", desc = "收货地坐标纬度", type = MappedType.EXPRESSION)
    private String receiverAddressLat;
    @MappedField(expression = "str(addOrderParam.cargoWeight/1000))", desc = "重量单位公斤", type = MappedType.EXPRESSION)
    private String weight;
    @MappedField(expression = "str(1600)", desc = "配送工具 有效值：1600（其他） 1601（汽车）", type = MappedType.EXPRESSION)
    private String transportation;
    @MappedField(expression = "getTokenLadderFunction()", desc = "访问凭证", type = MappedType.REF_JAVA_CODE)
    private String token;

}
