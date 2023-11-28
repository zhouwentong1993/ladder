package com.wentong.ladder.annotations;

import com.wentong.ladder.enums.MappedType;

import java.lang.annotation.*;

/**
 * 标识字段映射关系
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface MappedField {

    /**
     * 表达式
     */
    String expression();

    /**
     * 描述信息
     */
    String desc();

    /**
     * 映射类型
     */
    MappedType type();

    /**
     * 校验规则，Aviator 表达式格式。
     * 如果为空，则不校验。
     */
    String validate() default "";
}
