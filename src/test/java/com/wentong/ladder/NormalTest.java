package com.wentong.ladder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NormalTest {

    @Test
    void testPathSub() {
        String fullPath = "/Users/zhouwentong/IdeaProjects/ladder/target/classes/";

        // 查找最后一个路径分隔符的索引
        int lastIndex = fullPath.lastIndexOf("/target/classes/");

        // 截取字符串，包括第一个字符，但不包括最后一个字符
        String result = fullPath.substring(0, lastIndex + 1);

        Assertions.assertEquals("/Users/zhouwentong/IdeaProjects/ladder/", result);
    }

    @Test
    void testString() {
        String s1 = "prePriceFunction()";
        String s2 = "addOrderParam";
        String s3 = s1.substring(0, s1.indexOf("(") + 1) + s2 + s1.substring(s1.indexOf("(") + 1);
        Assertions.assertEquals("prePriceFunction(addOrderParam)", s3);
    }

}
