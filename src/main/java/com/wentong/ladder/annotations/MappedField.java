package com.wentong.ladder.annotations;

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
}
