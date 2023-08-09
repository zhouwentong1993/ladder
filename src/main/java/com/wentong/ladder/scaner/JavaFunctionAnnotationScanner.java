package com.wentong.ladder.scaner;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import com.google.common.annotations.VisibleForTesting;
import com.wentong.ladder.annotations.JavaFunction;
import com.wentong.ladder.config.LadderConfig;
import com.wentong.ladder.registry.AviatorFunctionRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.wentong.ladder.utils.CommonUtil.makeFirstLetterLowerCase;

@Slf4j
@Component
public class JavaFunctionAnnotationScanner {

    @Autowired
    private LadderConfig ladderConfig;

    @PostConstruct
    public void scan() {
        log.info("JavaFunctionAnnotationScanner scan");
        realScan(ladderConfig.ladderFunctionScanBasePackage);
    }

    @VisibleForTesting
    public void scan(String path) {
        realScan(path);
    }

    private static void realScan(String basePackage) {
        Set<Class<?>> classes = ClassUtil.scanPackage(basePackage);
        for (Class<?> clazz : classes) {
            boolean classWithAnnotation = AnnotationUtil.hasAnnotation(clazz, JavaFunction.class);
            if (classWithAnnotation) {
                AviatorFunctionRegistry.register(makeFirstLetterLowerCase(clazz.getSimpleName()), clazz);
            }
        }
    }


}
