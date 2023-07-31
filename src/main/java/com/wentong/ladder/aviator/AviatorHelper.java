package com.wentong.ladder.aviator;

import cn.hutool.core.lang.Dict;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Aviator 的一些操作
 */
public final class AviatorHelper {

    private static final AviatorEvaluatorInstance INSTANCE = AviatorEvaluator.getInstance();

    private AviatorHelper() {
    }

    // 缓存编译结果，提升性能
    public static Function<String, Expression> COMPILED_FUNCTION = param -> INSTANCE.compile(param, true);

    // 执行
    public static BiFunction<String, Dict, Object> EXECUTE_FUNCTION = INSTANCE::execute;


}
