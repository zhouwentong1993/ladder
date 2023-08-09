package com.wentong.ladder.registry;

import cn.hutool.extra.spring.SpringUtil;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorFunction;
import com.googlecode.aviator.spring.SpringContextFunctionLoader;
import com.wentong.ladder.exceptions.AviatorFunctionException;
import com.wentong.ladder.utils.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Aviator 函数注册表，用来注册用户自定义函数
 */
@Slf4j
public class AviatorFunctionRegistry {

    static {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();

        // 提供 Spring 上下文支持，因为业务逻辑需要从 MySQL、Redis 等数据源获取，通常使用 Spring 方式。
        AviatorEvaluator.addFunctionLoader(new SpringContextFunctionLoader(applicationContext));
    }

    public static void register(String name, Class<?> clazz) {
        log.info("Register function: {} to registry.", name);
        try {
//            AviatorEvaluator.addInstanceFunctions(name, clazz);
            AviatorEvaluator.addFunction((AviatorFunction) ReflectUtil.getNoArgsConstructor(clazz).newInstance());
        } catch (Exception e) {
            throw new AviatorFunctionException(e);
        }
    }

}
