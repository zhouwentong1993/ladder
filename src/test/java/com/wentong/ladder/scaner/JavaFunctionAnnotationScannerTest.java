package com.wentong.ladder.scaner;

import com.googlecode.aviator.AviatorEvaluator;
import com.wentong.ladder.functions.vo.AddOrderParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JavaFunctionAnnotationScannerTest {

    @Test
    void scan() {
        new JavaFunctionAnnotationScanner().scan("com.wentong.ladder");


        AddOrderParam addOrderParam = new AddOrderParam();
        addOrderParam.setPlatformType("PT365");
        Map<String,Object> map = new HashMap<>();
        map.put("addOrderParam", addOrderParam);
        String expression = "prePriceFunction(addOrderParam)";
        Object result = AviatorEvaluator.execute(expression, map);

        System.out.println("Result: " + result);
    }
}