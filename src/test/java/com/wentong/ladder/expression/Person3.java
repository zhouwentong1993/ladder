package com.wentong.ladder.expression;

import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;
import lombok.Data;

@MappedClass
@Data
public class Person3 {

    @MappedField(expression = "name", desc = "姓名", type = MappedType.EXPRESSION)
    private String name;

    @MappedField(expression = "1", desc = "年龄", type = MappedType.CONSTANT)
    private int age;

    @MappedField(expression = "1", desc = "年龄", type = MappedType.EXPRESSION)
    private Address address;

}
