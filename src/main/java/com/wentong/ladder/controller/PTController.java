package com.wentong.ladder.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wentong.ladder.aviator.AviatorHelper;
import com.wentong.ladder.entity.MetaHttpRequestEntity;
import com.wentong.ladder.exceptions.HttpRequestException;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.handler.impl.ClassMappingHandler;
import com.wentong.ladder.mapper.MetaHttpRequestMapper;
import com.wentong.ladder.utils.ReflectUtil;
import com.wentong.ladder.vo.AddOrderParam;
import com.wentong.ladder.vo.RawObject;
import com.wentong.ladder.vo.TemporaryModel;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.wentong.ladder.http.HttpGetter.getResponse;

@RestController
@RequestMapping("pt")
public class PTController {

    private final MetaHttpRequestMapper metaHttpRequestMapper;

    public PTController(MetaHttpRequestMapper metaHttpRequestMapper) {
        this.metaHttpRequestMapper = metaHttpRequestMapper;
    }

    @GetMapping("test/{business}")
    public String test(@PathVariable String business) throws Exception {

        MetaHttpRequestEntity metaHttpRequestEntity = metaHttpRequestMapper.selectOne(Wrappers.<MetaHttpRequestEntity>lambdaQuery().eq(MetaHttpRequestEntity::getRefBusiness, business).eq(MetaHttpRequestEntity::getRefPlatform, "PT356"));
        if (metaHttpRequestEntity == null) {
            return "not found";
        }

        RawObject rawObject = getRawObject();

        MappingHandler<RawObject, Object> mappingHandler = new ClassMappingHandler<>();
        Object o = ReflectUtil.getNoArgsConstructor(Class.forName(metaHttpRequestEntity.getRefClass())).newInstance();
        Object mapResult = mappingHandler.mapping(rawObject, o);

        Response response = getResponse(metaHttpRequestEntity, JSONObject.from(o));
        if (response.code() != 200) {
            throw new HttpRequestException("请求失败");
        }

        String responseBody = response.body().string();
        if (StrUtil.isBlank(metaHttpRequestEntity.getResponseSuccessJudgement())) {
            return responseBody;
        }
        boolean successResponse = (boolean) AviatorHelper.COMPILED_FUNCTION.apply(metaHttpRequestEntity.getResponseSuccessJudgement()).execute(JSONObject.parseObject(responseBody));
        if (successResponse) {
            Map<Object,Object> mappedResponse = (Map<Object, Object>) AviatorHelper.COMPILED_FUNCTION.apply(metaHttpRequestEntity.getResponseMapping()).execute(JSONObject.parseObject(responseBody));
            return JSON.toJSONString(mappedResponse);
        } else {
            Map<Object,Object> mappedResponse = (Map<Object, Object>) AviatorHelper.COMPILED_FUNCTION.apply(metaHttpRequestEntity.getResponseMessage()).execute(JSONObject.parseObject(responseBody));
            return JSON.toJSONString(mappedResponse);
        }

    }

    @NotNull
    private static RawObject getRawObject() {
        String addStr = "{\"cargoNum\":4,\"cargoPrice\":53.8,\"cargoType\":0,\"cargoWeight\":1000,\"coordinateType\":0,\"deliveryServiceCode\":6242,\"expectedFinishTime\":20230811121400,\"head\":{\"groupID\":\"288288\",\"shipPlatformCode\":103,\"shipPlatformCodeSub\":\"01\",\"shopID\":\"770854\",\"traceID\":\"mqtaked1ea1b8229ad50fa1b_IIw\"},\"info\":\"消费者备注:顾客需要餐具\",\"invoiceTitle\":\"无\",\"isInvoiced\":1,\"mtWmOper\":1,\"orderActualAmount\":0,\"orderPaymentStatus\":1,\"orderPickupTime\":0,\"orderSequence\":\"17\",\"orderSource\":\"美团\",\"orderType\":0,\"originId\":\"7265900125862496242\",\"payType\":0,\"pickUpType\":\"2\",\"product\":[{\"itemActualPrice\":21.8,\"productName\":\"【肌肉满满】香脆鸡排番茄肉酱意面\",\"productNum\":1,\"productPrice\":21.8,\"remark\":\"[1人份]\"},{\"itemActualPrice\":0,\"productName\":\"[满赠]满5.0元赠精美餐垫纸 1份\",\"productNum\":1,\"productPrice\":0,\"remark\":\"无\"}],\"productTypeNum\":2,\"receiverAddress\":\"安徽淮记食限公司 (2层 淮记本食)\",\"receiverLat\":41.778474,\"receiverLng\":123.404263,\"receiverName\":\"许\",\"receiverPhone\":\"1565657874,3544\",\"reportId\":\"273307\",\"sassOrderNo\":\"H20238111192013723941510017\",\"takeOutOrderKey\":\"100098865492\",\"takeoutShopId\":\"1629654\",\"tips\":0,\"totalDeliveryPrice\":0,\"transporterId\":0,\"upstreamOrderKey\":\"726589906114\"}";
        AddOrderParam addOrderParam = JSON.parseObject(addStr, AddOrderParam.class);
        String tem = "{\"isNew\":1,\"storeChannelBean\":{\"appKey\":\"19acd509c68947b8b7b30672976622d0\",\"areaName\":\"田家庵区\",\"auditStatus\":40,\"city\":\"淮南市\",\"cityCode\":\"\",\"equityAccount\":\"\",\"hualalaGroupName\":\"集团\",\"hualalaGroupid\":\"288288\",\"hualalaShopid\":\"70854\",\"id\":273307,\"isYRAcount\":0,\"isYuRechage\":0,\"isgroupByding\":0,\"isplayArea\":\"0\",\"platformType\":103,\"positionSource\":3,\"province\":\"340000\",\"secret\":\"1992keyxld\",\"special_key\":\"17611189283\",\"subPlatformType\":\"01\",\"thirdProductType\":\"\",\"thirdShopid\":\"09476dee0e837sssss877f0df61ddf683ee4\",\"transportAddress\":\"西门龙x吞口熊10号楼137\",\"transportLatitude\":\"41.794218\",\"transportLongitude\":\"123.393448\",\"transportName\":\"吞口熊の牛排手作意面（淮南店）\",\"transportTel\":\"130463\",\"type\":2},\"thirdOrderID\":\"726900125862496242\",\"thirdShopID\":\"09476deef0df61ddf683ee4\"}";
        TemporaryModel temporaryModel = JSON.parseObject(tem, TemporaryModel.class);
        return new RawObject(addOrderParam, temporaryModel);
    }

}
