package com.wentong.ladder.code.test;

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
@NoArgsConstructor
@AllArgsConstructor
@MappedClass
public class Door {

    @MappedField(expression = "address.door.frontDoor", desc = "前门", type = MappedType.EXPRESSION)
    private String frontDoor;
    @MappedField(expression = "address.door.backDoor", desc = "后门", type = MappedType.EXPRESSION)
    private String backDoor;

}
