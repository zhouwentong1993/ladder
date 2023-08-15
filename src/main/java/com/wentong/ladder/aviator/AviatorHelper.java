package com.wentong.ladder.aviator;

import cn.hutool.core.lang.Dict;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.googlecode.aviator.Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL;
import static com.googlecode.aviator.Options.TRACE_EVAL;

/**
 * Aviator 的一些操作
 */
public final class AviatorHelper {

    private static final AviatorEvaluatorInstance INSTANCE = AviatorEvaluator.getInstance();

    static {
        INSTANCE.setOption(TRACE_EVAL, true);
        INSTANCE.setOption(ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
    }

    private AviatorHelper() {
    }

    // 缓存编译结果，提升性能
    public static Function<String, Expression> COMPILED_FUNCTION = param -> INSTANCE.compile(param, true);

    // 执行
    public static BiFunction<String, Dict, Object> EXECUTE_FUNCTION = INSTANCE::execute;


}
