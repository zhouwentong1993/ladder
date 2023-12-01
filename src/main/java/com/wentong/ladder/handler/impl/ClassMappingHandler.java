package com.wentong.ladder.handler.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.DynaBean;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wentong.ladder.aviator.AviatorHelper;
import com.wentong.ladder.enums.MappedType;
import com.wentong.ladder.exceptions.ClassInitializeException;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.interceptor.LoggingMappingInterceptor;
import com.wentong.ladder.interceptor.MappingInterceptor;
import com.wentong.ladder.registry.MappingFieldWrapper;
import com.wentong.ladder.registry.MappingRegistry;
import com.wentong.ladder.utils.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.MappingException;

import java.lang.reflect.Field;
import java.util.Map;

import static com.wentong.ladder.utils.CommonUtil.makeFirstLetterLowerCase;

@SuppressWarnings("unchecked")
@Slf4j
public class ClassMappingHandler<S, T> implements MappingHandler<S, T> {

    // 默认的是 log 拦截器，可以通过 set 方法修改。
    private MappingInterceptor mappingInterceptor = new LoggingMappingInterceptor();

    public void setMappingInterceptor(MappingInterceptor mappingInterceptor) {
        this.mappingInterceptor = mappingInterceptor;
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
                    log.info("Start mapping field:{}", w.refField().getName());

                    validateMappingField(sourceMap, w);

                    MappedType mappedType = w.mappedType();
                    switch (mappedType) {
                        case EXPRESSION:
                            expressionFieldMapping(source, dynaBean, sourceMap, clazz, w);
                            break;
                        case CONSTANT:
                            constantFieldMapping(w.refField(), w.expression(), dynaBean);
                            break;
                        case CONTEXT:
                            break;
                        case REF_JAVA_CODE:
                            javaCodeFieldMapping(source, dynaBean, w);
                            break;
                        default:
                            break;
                    }
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

    private static void javaCodeFieldMapping(S source, DynaBean dynaBean, MappingFieldWrapper w) {
        String className = makeFirstLetterLowerCase(source.getClass().getSimpleName());
        Map<String, Object> param = Map.of(className, source);
        String expression = w.expression();
        // 组成实际交由 Aviator 执行的表达式
        String realExpression = expression.substring(0, expression.indexOf("(") + 1) + className + expression.substring(expression.indexOf("(") + 1);
        dynaBean.set(w.refField().getName(), AviatorHelper.COMPILED_FUNCTION.apply(realExpression).execute(param));
    }

    private void expressionFieldMapping(S source, DynaBean dynaBean, Map<String, Object> sourceMap, Class<?> clazz, MappingFieldWrapper w) {
        if (w.refField().getType().isPrimitive() || w.refField().getType().equals(String.class)) {
            dynaBean.set(w.refField().getName(), AviatorHelper.COMPILED_FUNCTION.apply(w.expression()).execute(sourceMap));
        } else {
            try {
                dynaBean.set(w.refField().getName(), mapping(source, (Class<T>) w.refField().getType()));
            } catch (Exception e) {
                throw new ClassInitializeException("Init class:" + clazz + ", field:" + w.refField().getName() + " failed!", e);
            }
        }
        return;
    }

    private static void validateMappingField(Map<String, Object> sourceMap, MappingFieldWrapper w) {
        if (StrUtil.isNotBlank(w.validate())) { // 执行校验逻辑
            boolean validateResult = (boolean) AviatorHelper.COMPILED_FUNCTION.apply(w.validate()).execute(sourceMap);
            if (!validateResult) {
                throw new MappingException("属性：" + w.refField().getName() + "校验表达式:" + w.validate() + "不通过。");
             }
        }
    }

    private void constantFieldMapping(Field field, String stringValue, DynaBean dynaBean) {
        Class<?> fieldType = field.getType();

        Object convertedValue = null;

        if (fieldType == int.class || fieldType == Integer.class) {
            convertedValue = Integer.parseInt(stringValue);
        } else if (fieldType == long.class || fieldType == Long.class) {
            convertedValue = Long.parseLong(stringValue);
        } else if (fieldType == float.class || fieldType == Float.class) {
            convertedValue = Float.parseFloat(stringValue);
        } else if (fieldType == double.class || fieldType == Double.class) {
            convertedValue = Double.parseDouble(stringValue);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            convertedValue = Boolean.parseBoolean(stringValue);
        } else if (fieldType == String.class) {
            convertedValue = stringValue;
        }
        // 可以根据需要添加其他数据类型的处理逻辑

        // 如果无法处理该类型，则抛出异常或者使用默认值等处理方式
        if (convertedValue == null) {
            throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        }

        dynaBean.set(field.getName(), convertedValue); // 设置属性值

    }

}
