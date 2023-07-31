package com.wentong.ladder.expression;

import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;
import lombok.Data;

@MappedClass
@Data
public class Person2 {

    @MappedField(expression = "name", desc = "姓名", type = MappedType.CONSTANT)
    private String name;

}
