package com.wentong.ladder.expression;

import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@MappedClass
@NoArgsConstructor
public class Door {
    @MappedField(expression = "address.door.frontDoor", desc = "前门", type = MappedType.EXPRESSION)
    private String frontDoor;
    @MappedField(expression = "address.door.backDoor", desc = "后门", type = MappedType.EXPRESSION)
    private String backDoor;

}
