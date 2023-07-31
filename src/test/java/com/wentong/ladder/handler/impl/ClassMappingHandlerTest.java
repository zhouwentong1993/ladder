package com.wentong.ladder.handler.impl;

import com.wentong.ladder.expression.Person1;
import com.wentong.ladder.expression.RawObj;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.scaner.AnnotationScanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassMappingHandlerTest {

    @BeforeAll
    static void beforeAll() {
        // init scanner
        AnnotationScanner annotationScanner = new AnnotationScanner();
        annotationScanner.scan("com.wentong.ladder");
    }

    @Test
    void testMapping() {

        MappingHandler<RawObj, Person1> mappingHandler = new ClassMappingHandler<>();
        Person1 p = mappingHandler.mapping(new RawObj("name1", null, 199), Person1.class);
        assertEquals("name1", p.getName());
        assertEquals(1, p.getAge());

    }
}