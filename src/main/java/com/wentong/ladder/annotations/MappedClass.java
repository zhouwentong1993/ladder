package com.wentong.ladder.annotations;

import java.lang.annotation.*;

/**
 * 用来标识映射的类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface MappedClass {
}
