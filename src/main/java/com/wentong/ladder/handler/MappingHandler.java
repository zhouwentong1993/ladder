package com.wentong.ladder.handler;

public interface MappingHandler<S, T> {

    /**
     * 通过类的方式映射
     */
    T mapping(S source, Class<T> clz);

    /**
     * 通过对象的方式映射
     */
    T mapping(S source, T target);

}
