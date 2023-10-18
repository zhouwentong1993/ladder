package com.wentong.ladder.utils;

import java.lang.reflect.Constructor;

public final class ReflectUtil<T> {

    /**
     * 获取 clazz 中的无参构造方法
     * @throws IllegalArgumentException 如果该类中无无参构造方法，则抛出该异常
     */
    public static Constructor<?> getNoArgsConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // 遍历构造方法数组，找到无参数的构造方法
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        throw new IllegalArgumentException("No no args constructor found in class:" + clazz);
    }

}
