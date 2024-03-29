package com.wentong.ladder.handler.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.DynaBean;
import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.aviator.AviatorHelper;
import com.wentong.ladder.exceptions.MappingException;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.interceptor.LoggingMappingInterceptor;
import com.wentong.ladder.interceptor.MappingInterceptor;
import com.wentong.ladder.registry.MappingFieldWrapper;
import com.wentong.ladder.utils.ReflectUtil;
import com.wentong.ladder.vo.HttpMappingVo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 对 HTTP 接口进行映射的过程，返回值为固定的 HttpMappingVo，Vo 中的 data 属性是真正对应的业务对象。
 *
 * @param <S>
 * @param <T>
 */
@Slf4j
@SuppressWarnings("all")
public class AntiHttpMappingHandler<S, T> implements MappingHandler<S, T> {

    private final MappingInterceptor mappingInterceptor;

    private final List<MappingFieldWrapper> mappingFieldWrappers;

    public AntiHttpMappingHandler(MappingInterceptor mappingInterceptor, List<MappingFieldWrapper> mappingFieldWrappers) {
        this.mappingInterceptor = mappingInterceptor;
        this.mappingFieldWrappers = mappingFieldWrappers;
    }

    public AntiHttpMappingHandler(List<MappingFieldWrapper> mappingFieldWrappers) {
        this.mappingFieldWrappers = mappingFieldWrappers;
        this.mappingInterceptor = new LoggingMappingInterceptor();
    }

    @Override
    public T mapping(S source, Class<T> clz) {
        try {
            return mapping(source, (T) ReflectUtil.getNoArgsConstructor(clz).newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T mapping(S source, T target) {
        try {
            mappingInterceptor.beforeMapping(source, target);
            DynaBean dynaBean = DynaBean.create(target);
            Map<String, Object> sourceMap = BeanUtil.beanToMap(source);
            Class<?> clazz = target.getClass();
            if (Objects.equals(clazz.getName(), HttpMappingVo.class.getName())) {
                MappingFieldWrapper successWrapper = select(clazz.getField("success"));
                realMapping(source, dynaBean, sourceMap, clazz, successWrapper);
                boolean success = dynaBean.get("success");
                if (success) { // 如果成功，将数据映射到 data 字段
                    MappingFieldWrapper dataWrapper = select(clazz.getField("data"));
                    realMapping(source, dynaBean, sourceMap, clazz, dataWrapper);
                } else { // 如果失败，将数据映射到 message
                    MappingFieldWrapper messageWrapper = select(clazz.getField("message"));
                    realMapping(source, dynaBean, sourceMap, clazz, messageWrapper);
                }
            } else { // 处理普通映射
                Map<Object, Object> mappedResponse = (Map<Object, Object>) AviatorHelper.COMPILED_FUNCTION.apply(select(HttpMappingVo.class.getField("data")).expression()).execute((JSONObject) source);
                dynaBean.set("data", mappedResponse);
            }
            T bean = dynaBean.getBean();
            mappingInterceptor.afterMapping(source, bean);
            return bean;
        } catch (Exception t) {
            mappingInterceptor.exceptionMapping(source, target, t);
            throw new MappingException(t);
        }
    }

    private MappingFieldWrapper select(Field source) {
        return mappingFieldWrappers.stream().filter(s -> s.refField().equals(source)).findFirst().orElseThrow();
    }

}
