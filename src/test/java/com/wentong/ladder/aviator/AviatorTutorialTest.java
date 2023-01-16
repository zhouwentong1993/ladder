package com.wentong.ladder.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Function;

/**
 * 一些针对于 Aviator 的测试研究
 */
@Slf4j
public class AviatorTutorialTest {

    /**
     * 编译执行
     */
    @Test
    void test1() throws IOException {
        final Function<String, Expression> COMPILE = AviatorEvaluator.getInstance()::compile;

        log.info("start at:{}", System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            AviatorEvaluator.getInstance();
            Expression expression = AviatorEvaluator.getInstance().compileScript("examples/hello.av", true);
            expression.execute();
        }
        log.info("end at:{}", System.currentTimeMillis());
    }

    /**
     * 直接执行
     */
    @Test
    void test2() throws Exception {
        String expression = "a - (b - c) > 100";
        Expression ep = AviatorEvaluator.getInstance().compile(expression);
        // 给参数赋值
        boolean result = (boolean) ep.execute(ep.newEnv("a", 100, "b", 200, "c", 50));
        Assertions.assertFalse(result);
    }

}
