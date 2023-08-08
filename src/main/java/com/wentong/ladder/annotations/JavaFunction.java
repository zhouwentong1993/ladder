package com.wentong.ladder.annotations;

import java.lang.annotation.*;

/**
 * 用来标识该类是 Java 实现的 Aviator 函数
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface JavaFunction {
}
