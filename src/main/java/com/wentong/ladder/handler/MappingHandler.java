package com.wentong.ladder.handler;

import cn.hutool.core.bean.DynaBean;
import cn.hutool.core.util.StrUtil;
import com.wentong.ladder.aviator.AviatorHelper;
import com.wentong.ladder.enums.MappedType;
import com.wentong.ladder.exceptions.ClassInitializeException;
import com.wentong.ladder.registry.MappingFieldWrapper;
import org.springframework.data.mapping.MappingException;

import java.lang.reflect.Field;
import java.util.Map;

import static com.wentong.ladder.utils.CommonUtil.makeFirstLetterLowerCase;

public interface MappingHandler<S, T> {

    /**
     * 通过类的方式映射
     */
    T mapping(S source, Class<T> clz);

    /**
     * 通过对象的方式映射
     */
    T mapping(S source, T target);

    default void realMapping(S source, DynaBean dynaBean, Map<String, Object> sourceMap, Class<?> clazz, MappingFieldWrapper w) {

        validateMappingField(sourceMap, w);

        MappedType mappedType = w.mappedType();
        switch (mappedType) {
            case EXPRESSION:
                expressionFieldMapping(source, dynaBean, sourceMap, clazz, w);
                break;
            case CONSTANT:
                constantFieldMapping(w.refField(), w.expression(), dynaBean);
                break;
            case CONTEXT: // 暂不支持上下文格式。
                break;
            case REF_JAVA_CODE:
                javaCodeFieldMapping(source, dynaBean, w);
                break;
            default:
                break;
        }
    }

    private void javaCodeFieldMapping(S source, DynaBean dynaBean, MappingFieldWrapper w) {
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
