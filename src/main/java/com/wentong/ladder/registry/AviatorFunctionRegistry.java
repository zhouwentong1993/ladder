package com.wentong.ladder.registry;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.spring.SpringContextFunctionLoader;
import com.wentong.ladder.exceptions.AviatorFunctionException;

/**
 * Aviator 函数注册表，用来注册用户自定义函数
 */
public class AviatorFunctionRegistry {

    public static void register(String name, Class clazz) {
        // 提供 Spring 上下文支持，因为业务逻辑需要从 MySQL、Redis 等数据源获取，通常使用 Spring 方式。
        AviatorEvaluator.addFunctionLoader(new SpringContextFunctionLoader());
        try {
            AviatorEvaluator.addInstanceFunctions(name, clazz);
        } catch (Exception e) {
            throw new AviatorFunctionException(e);
        }
    }

}
