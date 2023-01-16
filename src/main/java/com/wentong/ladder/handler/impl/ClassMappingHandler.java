package com.wentong.ladder.handler.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.DynaBean;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;
import com.wentong.ladder.handler.MappingHandler;

import java.lang.reflect.Field;

public class ClassMappingHandler<S,T> implements MappingHandler<S,T> {

    @Override
    public T mapping(S source, Class<T> clz) {
        return null;
    }

    @Override
    public T mapping(S source, T target) {
        DynaBean dynaBean = DynaBean.create(target);
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            MappedField annotation = AnnotationUtil.getAnnotation(field, MappedField.class);
            if (annotation != null) {
                MappedType type = annotation.type();
                String expression = annotation.expression();
                switch (type) {
                    case EXPRESSION:
                        break;
                    default:
                        break;
                }
            }
        }
        return null;
    }
}
