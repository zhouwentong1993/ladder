package com.wentong.ladder.scaner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaFunctionAnnotationScannerTest {

    @Test
    void scan() {
        new JavaFunctionAnnotationScanner().scan("com.wentong.ladder");
    }
}