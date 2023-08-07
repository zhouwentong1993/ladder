package com.wentong.ladder.registry;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MappingRegistry {

    private static final Map<Class<?>, List<MappingFieldWrapper>> REGISTRY = new ConcurrentHashMap<>();

    public static void register(Class<?> clazz, List<MappingFieldWrapper> mappingFieldWrappers) {
        log.info("Register class: {} to registry.", clazz);
        REGISTRY.put(clazz, mappingFieldWrappers);
    }

    public static List<MappingFieldWrapper> get(Class<?> clazz) {
        return REGISTRY.get(clazz);
    }

}
