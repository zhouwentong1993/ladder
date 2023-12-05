package com.wentong.ladder.handler.impl;

import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.enums.MappedType;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.registry.MappingFieldWrapper;
import com.wentong.ladder.vo.HttpMappingVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class AntiHttpMappingHandlerTest {

    @BeforeEach
    void before() {

    }


    @Test
    void mapping() {
    }

    @Test
    void testMapping() throws Exception {
        List<MappingFieldWrapper> mappingFieldWrappers = List.of(
                new MappingFieldWrapper("errorcode == 0", "success判断", null, MappedType.EXPRESSION, HttpMappingVo.class.getDeclaredField("success")),
                new MappingFieldWrapper("let map = seq.map(\"deliveryFee\",decimal(data.totalCost)-decimal(data.defCouCost),\"distance\",data.totalKm); if(decimal(data.defCouCost)!=0) {map[\"discountFee\"]=decimal(data.defCouCost);} return map;", "预估价", null, MappedType.EXPRESSION, HttpMappingVo.class.getDeclaredField("data")),
                new MappingFieldWrapper("message", "错误响应", null, MappedType.EXPRESSION, HttpMappingVo.class.getDeclaredField("message"))

        );
        MappingHandler<JSONObject, HttpMappingVo> mappingHandler = new AntiHttpMappingHandler<>(mappingFieldWrappers);

        String sourceStr = "{\n" +
                "\t\"data\": {\n" +
                "\t\t\"defCouCost\": \"6\",\n" +
                "\t\t\"carSubsidy\": \"0\",\n" +
                "\t\t\"nightSubsidy\": \"0\",\n" +
                "\t\t\"kmCost\": \"28\",\n" +
                "\t\t\"weatherSubsidy\": \"0\",\n" +
                "\t\t\"kgCost\": \"0\",\n" +
                "\t\t\"totalKm\": \"11.19\",\n" +
                "\t\t\"totalKg\": 2,\n" +
                "\t\t\"totalCost\": \"28\",\n" +
                "\t\t\"holidaySubsidy\": \"0\"\n" +
                "\t},\n" +
                "\t\"message\": \"运费计算成功\",\n" +
                "\t\"errorcode\": 0\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(sourceStr);

        HttpMappingVo mapping = mappingHandler.mapping(jsonObject, HttpMappingVo.class);
        Assertions.assertTrue(mapping.success);
        Assertions.assertEquals("11.19", ((Map) mapping.data.get("data")).get("distance"));

    }
}