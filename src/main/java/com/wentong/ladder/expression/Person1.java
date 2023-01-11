package com.wentong.ladder.expression;

import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import lombok.Data;

@MappedClass
@Data
public class Person1 {

    @MappedField(expression = "name", desc = "姓名")
    private String name;

    @MappedField(expression = "age", desc = "年龄")
    private int age;

}
