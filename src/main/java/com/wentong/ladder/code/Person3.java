package com.wentong.ladder.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;

/**
 * Auto generated class.
 * DO NOT MODIFY!!!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedClass
public class Person3 {
    @MappedField(expression = "name", desc = "姓名", type = MappedType.EXPRESSION)
    private String name;
    @MappedField(expression = "1", desc = "年龄", type = MappedType.CONSTANT)
    private int age;
    @MappedField(expression = "1", desc = "地址", type = MappedType.EXPRESSION)
    private Address address;
}
