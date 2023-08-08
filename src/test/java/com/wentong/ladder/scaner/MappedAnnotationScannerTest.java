package com.wentong.ladder.scaner;

import com.wentong.ladder.expression.Person1;
import com.wentong.ladder.expression.Person2;
import com.wentong.ladder.registry.MappingRegistry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MappedAnnotationScannerTest {

    @Test
    void scan() {
        MappedAnnotationScanner mappedAnnotationScanner = new MappedAnnotationScanner();
        mappedAnnotationScanner.scan("com.wentong.ladder");
        assertEquals(2, MappingRegistry.get(Person1.class).size());
        assertEquals(1, MappingRegistry.get(Person2.class).size());
    }
}