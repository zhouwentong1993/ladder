package com.wentong.ladder.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;

import java.util.function.Function;

/**
 * Aviator 的一些操作
 */
public final class AviatorHelper {

    private static final AviatorEvaluatorInstance INSTANCE = AviatorEvaluator.getInstance();

    private AviatorHelper() {
    }

    public static Function<String, Expression> COMPILE_FUNCTION = param -> INSTANCE.compile(param, true);


}
