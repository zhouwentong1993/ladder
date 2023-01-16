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
}
