package com.wentong.ladder.handler.impl;

import com.wentong.ladder.expression.Address;
import com.wentong.ladder.expression.Person1;
import com.wentong.ladder.expression.Person3;
import com.wentong.ladder.expression.RawObj;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.scaner.AnnotationScanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassMappingHandlerTest {

    static RawObj rawObj = null;

    @BeforeAll
    static void beforeAll() {
        // init scanner
        AnnotationScanner annotationScanner = new AnnotationScanner();
        annotationScanner.scan("com.wentong.ladder");
        rawObj = new RawObj("name1", null, 199, new Address("city1", "street1"));
    }

    @Test
    void testMappingWithPrimitiveType() {

        MappingHandler<RawObj, Person1> mappingHandler = new ClassMappingHandler<>();
        Person1 p = mappingHandler.mapping(rawObj, Person1.class);
        assertEquals("name1", p.getName());
        assertEquals(1, p.getAge());
    }

    @Test
    void testMappingWithWrapperType() {

        MappingHandler<RawObj, Person3> mappingHandler = new ClassMappingHandler<>();
        Person3 p = mappingHandler.mapping(rawObj, Person3.class);
        assertEquals("name1", p.getName());
        assertEquals(1, p.getAge());
        assertEquals("city1", p.getAddress().getCity());
        assertEquals("street1", p.getAddress().getStreet());
    }

}