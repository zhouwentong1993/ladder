package com.wentong.ladder.handler.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.DynaBean;
import cn.hutool.core.collection.CollUtil;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.interceptor.LoggingMappingInterceptor;
import com.wentong.ladder.interceptor.MappingInterceptor;
import com.wentong.ladder.registry.MappingRegistry;
import com.wentong.ladder.utils.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@SuppressWarnings("unchecked")
@Slf4j
public class ClassMappingHandler<S, T> implements MappingHandler<S, T> {

    private final MappingInterceptor mappingInterceptor;

    public ClassMappingHandler(MappingInterceptor mappingInterceptor) {
        this.mappingInterceptor = mappingInterceptor;
    }

    public ClassMappingHandler() {
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
            var mappingFieldWrappers = MappingRegistry.get(clazz);

            if (CollUtil.isNotEmpty(mappingFieldWrappers)) {
                mappingFieldWrappers.forEach(w -> {
                    realMapping(source, dynaBean, sourceMap, clazz, w);
                });
            }
            T bean = dynaBean.getBean();
            mappingInterceptor.afterMapping(source, bean);
            return bean;
        } catch (Throwable t) {
            mappingInterceptor.exceptionMapping(source, target, t);
            throw t;
        }

    }

}
