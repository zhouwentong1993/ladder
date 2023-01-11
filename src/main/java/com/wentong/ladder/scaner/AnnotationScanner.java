package com.wentong.ladder.scaner;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;

import java.util.Set;

/**
 * 用来扫描指定包下的注解
 */
public class AnnotationScanner {

    public static void main(String[] args) {
        new AnnotationScanner().scan();
    }

    public void scan() {
        Set<Class<?>> classes = ClassUtil.scanPackage("com.wentong.ladder.expression");
        for (Class<?> clazz : classes) {
            boolean classWithAnnotation = AnnotationUtil.hasAnnotation(clazz, MappedClass.class);
            if (classWithAnnotation) {
                MappedField[] annotationsByType = clazz.getAnnotationsByType(MappedField.class);
                if (annotationsByType.length > 0) {
                    for (MappedField mappedField : annotationsByType) {
                        String expression = mappedField.expression();
                        String desc = mappedField.desc();
                        System.out.println(StrUtil.format("expression: {}, desc: {}", expression, desc));
                    }
                }

            }
        }
    }

}
