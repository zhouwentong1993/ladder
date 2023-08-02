package com.wentong.ladder.expression;

import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@MappedClass
@NoArgsConstructor
public class Address {

    @MappedField(expression = "address.city", desc = "姓名", type = MappedType.EXPRESSION)
    private String city;
    @MappedField(expression = "address.street", desc = "姓名", type = MappedType.EXPRESSION)
    private String street;


}
