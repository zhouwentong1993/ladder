package com.wentong.ladder.functions;

import com.wentong.ladder.annotations.JavaFunction;
import com.wentong.ladder.mapper.MetaHttpRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取预估价的自定义函数实现
 * @param <AddOrderParam> 待转换的对象，这里是下单对象
 * @param <BigDecimal> 转换成的对象，这里是预估价格，从预估价接口获得。
 */
@JavaFunction
@Component
public class PrePriceFunctionDefinition<AddOrderParam, BigDecimal> implements FunctionDefinition<AddOrderParam, BigDecimal> {

    @Autowired
    private MetaHttpRequestMapper metaHttpRequestMapper;

    @Override
    public BigDecimal apply(AddOrderParam s) {
        return null;
    }
}
