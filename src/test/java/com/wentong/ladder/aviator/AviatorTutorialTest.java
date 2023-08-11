package com.wentong.ladder.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.wentong.ladder.expression.Door;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 一些针对于 Aviator 的测试研究
 */
@Slf4j
public class AviatorTutorialTest {

//    /**
//     * 编译执行
//     */
//    @Test
//    void test1() throws IOException {
//        final Function<String, Expression> COMPILE = AviatorEvaluator.getInstance()::compile;
//
//        long start = System.currentTimeMillis();
//        log.info("start at:{}", start);
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/hello.av", true);
//        for (int i = 0; i < 1000; i++) {
//            expression.execute();
//        }
//        log.info("total:{}", System.currentTimeMillis() - start);
//    }

    /**
     * 直接执行
     */
    @Test
    void test2() {
        String expression = "a - (b - c) > 100";
        Expression ep = AviatorEvaluator.getInstance().compile(expression);
        // 给参数赋值
        boolean result = (boolean) ep.execute(ep.newEnv("a", 100, "b", 200, "c", 50));
        Assertions.assertFalse(result);
    }

    @Test
    void testReturnObj() {
        String expression = "let d = new com.wentong.ladder.expression.Door(); d.frontDoor = '前门';d";
        Object execute = AviatorHelper.COMPILED_FUNCTION.apply(expression).execute();
        Assertions.assertEquals("前门", ((Door) execute).getFrontDoor());
    }

}
