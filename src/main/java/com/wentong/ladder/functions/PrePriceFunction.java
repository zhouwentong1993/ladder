package com.wentong.ladder.functions;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;
import com.wentong.ladder.annotations.JavaFunction;
import com.wentong.ladder.aviator.AviatorHelper;
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
            String responseSuccessJudgement = metaHttpRequestEntity.getResponseSuccessJudgement();
            boolean successResponse = (boolean) AviatorHelper.COMPILED_FUNCTION.apply(responseSuccessJudgement).execute(BeanUtil.beanToMap(response));
            // 是一个成功的响应，则对其进行映射
            MappingHandler<JSONObject, Object> responseMappingHandler = new ClassMappingHandler<>();
            Objects.requireNonNull(response.body(), "response body can not be null");
            if (successResponse) {
                // 这里暂时只处理 JSON 格式的响应结果
                // 不对，这里是典型的一对多的映射，也就是每个平台都要映射到这一个对象，不能通过创建对象的模式来映射，应该通过语句映射，执行防范，返回一个 AviatorObject
                Object responseMapping = AviatorHelper.COMPILED_FUNCTION.apply(metaHttpRequestEntity.getResponseMapping()).execute(JSONObject.parseObject(response.body().string()));
                return AviatorRuntimeJavaType.valueOf(responseMapping);
            } else { // 取 message 映射
                // 因为报错通常有两种情况，一是 HTTP 级别的报错，比如 500。另外是业务报错，这时会是 JSON 结构。
                Object messageMapping = AviatorHelper.COMPILED_FUNCTION.apply(metaHttpRequestEntity.getResponseMessage()).execute(Map.of("response", response));
                return AviatorRuntimeJavaType.valueOf(messageMapping);
            }

        } catch (Exception e) {
            throw new ClassInitializeException(e);
        }
    }
}
