package com.wentong.ladder.functions;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wentong.ladder.annotations.JavaFunction;
import com.wentong.ladder.entity.MetaHttpRequestEntity;
import com.wentong.ladder.exceptions.ClassInitializeException;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.handler.impl.ClassMappingHandler;
import com.wentong.ladder.http.HttpGetter;
import com.wentong.ladder.mapper.MetaHttpRequestMapper;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 获取预估价的自定义函数实现
 * @param <AddOrderParam> 待转换的对象，这里是下单对象
 * @param <BigDecimal> 转换成的对象，这里是预估价格，从预估价接口获得。
 */
@JavaFunction
@Component
public class PrePriceFunctionDefinition<AddOrderParam, BigDecimal> implements FunctionDefinition<com.wentong.ladder.functions.vo.AddOrderParam, java.math.BigDecimal> {

    @Autowired
    private MetaHttpRequestMapper metaHttpRequestMapper;

    @Override
    public java.math.BigDecimal apply(com.wentong.ladder.functions.vo.AddOrderParam addOrderParam) {
        MetaHttpRequestEntity metaHttpRequestEntity = metaHttpRequestMapper.selectOne(Wrappers.<MetaHttpRequestEntity>lambdaQuery().eq(MetaHttpRequestEntity::getRefBusiness, "CREATE_ORDER_PRICE").eq(MetaHttpRequestEntity::getRefPlatform, addOrderParam.getPlatformType()));
        Objects.requireNonNull(metaHttpRequestEntity, "metaHttpRequestEntity can not be null");
        MappingHandler<com.wentong.ladder.functions.vo.AddOrderParam, Object> mappingHandler = new ClassMappingHandler<>();
        try {
            Object mapping = mappingHandler.mapping(addOrderParam, Class.forName(metaHttpRequestEntity.getRefClass()));
            Response response = HttpGetter.getResponse(metaHttpRequestEntity, mapping);

        } catch (Exception e) {
            throw new ClassInitializeException(e);
        }
        return null;
    }
}
