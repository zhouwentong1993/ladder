package com.wentong.ladder.scaner;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.registry.MappingFieldWrapper;
import com.wentong.ladder.registry.MappingRegistry;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用来扫描指定包下的注解
 */
@Slf4j
public class AnnotationScanner {

    public void scan(String basePackage) {
        Set<Class<?>> classes = ClassUtil.scanPackage(basePackage);
        for (Class<?> clazz : classes) {
            boolean classWithAnnotation = AnnotationUtil.hasAnnotation(clazz, MappedClass.class);
            if (classWithAnnotation) {
                List<MappingFieldWrapper> mappingFieldWrapperList = new ArrayList<>();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    MappedField annotation = field.getAnnotation(MappedField.class);
                    if (annotation != null) {
                        MappingFieldWrapper mappingFieldWrapper = new MappingFieldWrapper(annotation.expression(), annotation.desc(), annotation.type(), field);
                        mappingFieldWrapperList.add(mappingFieldWrapper);
                    }
                }
                MappingRegistry.register(clazz, mappingFieldWrapperList);
            }
        }
    }

}
