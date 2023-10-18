package com.wentong.ladder.functions;

/**
 * 如果需要自定义函数，需要实现该接口，并且在对象上使用 @JavaFunction 注解标注。
 * 注意：如果该自定义函数需要使用 Spring 上下文，需要使用 @Component 注解标注。
 * @param <S> 函数入参
 * @param <T> 函数返回值
 */
public interface FunctionDefinition<S, T> {

    T apply(S s);

}
