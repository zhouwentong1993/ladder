package com.wentong.ladder.functions;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.wentong.ladder.annotations.JavaFunction;
import com.wentong.ladder.entity.MetaHttpRequestEntity;
import com.wentong.ladder.exceptions.ClassInitializeException;
import com.wentong.ladder.functions.vo.AddOrderParam;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.handler.impl.ClassMappingHandler;
import com.wentong.ladder.http.HttpGetter;
import com.wentong.ladder.mapper.MetaHttpRequestMapper;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@JavaFunction
public class PrePriceFunction extends LadderAbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        AddOrderParam addOrderParam = (AddOrderParam) FunctionUtils.getJavaObject(arg1, env);
        MetaHttpRequestEntity metaHttpRequestEntity = SpringUtil.getBean("metaHttpRequestMapper", MetaHttpRequestMapper.class).selectOne(Wrappers.<MetaHttpRequestEntity>lambdaQuery().eq(MetaHttpRequestEntity::getRefBusiness, "CREATE_ORDER_PRICE").eq(MetaHttpRequestEntity::getRefPlatform, addOrderParam.getPlatformType()));
        Objects.requireNonNull(metaHttpRequestEntity, "metaHttpRequestEntity can not be null");
        MappingHandler<AddOrderParam, Object> mappingHandler = new ClassMappingHandler<>();
        try {
            Object mapping = mappingHandler.mapping(addOrderParam, Class.forName(metaHttpRequestEntity.getRefClass()));
            Response response = HttpGetter.getResponse(metaHttpRequestEntity, mapping);

        } catch (Exception e) {
            throw new ClassInitializeException(e);
        }
        return null;
    }
}
