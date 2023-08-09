package com.wentong.ladder.scaner;

import cn.hutool.core.bean.BeanUtil;
import com.googlecode.aviator.AviatorEvaluator;
import com.wentong.ladder.functions.vo.AddOrderParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaFunctionAnnotationScannerTest {

    @Test
    void scan() {
        new JavaFunctionAnnotationScanner().scan("com.wentong.ladder");
        AddOrderParam addOrderParam = new AddOrderParam();
        addOrderParam.setPlatformType("PT365");
        String expression = "prePriceFunctionDefinition.apply(addOrderParam)";
        Object result = AviatorEvaluator.execute(expression, BeanUtil.beanToMap(addOrderParam));

        System.out.println("Result: " + result);
    }
}