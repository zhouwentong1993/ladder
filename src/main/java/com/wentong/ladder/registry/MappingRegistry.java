package com.wentong.ladder.registry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MappingRegistry {

    private static final Map<Class<?>, List<MappingFieldWrapper>> REGISTRY = new ConcurrentHashMap<>();

    public static void register(Class<?> clazz, List<MappingFieldWrapper> mappingFieldWrappers) {
        REGISTRY.put(clazz, mappingFieldWrappers);
    }

    public static List<MappingFieldWrapper> get(Class<?> clazz) {
        return REGISTRY.get(clazz);
    }

}
