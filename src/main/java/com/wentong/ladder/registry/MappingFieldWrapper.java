package com.wentong.ladder.registry;

import com.wentong.ladder.enums.MappedType;

import java.lang.reflect.Field;

public record MappingFieldWrapper(String expression, String desc,String validate, MappedType mappedType, Field refField) {

}
