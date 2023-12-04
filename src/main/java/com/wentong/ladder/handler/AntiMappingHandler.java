package com.wentong.ladder.handler;

import com.wentong.ladder.registry.MappingFieldWrapper;

import java.util.Map;

/**
 * 反向映射规则，由 source 对象映射为 target 对象，按照 rules 规则。
 * 与映射接口不同，映射接口 source 对象带着规则，这里的 source 对象是 JSON 对象，无法携带，通过 rules 携带。
 * @param <S> 源对象
 * @param <T> 目标对象
 */
public interface AntiMappingHandler<S, T> {

    T mapping(S s, Class<T> clz, Map<String, MappingFieldWrapper> rules);

}
