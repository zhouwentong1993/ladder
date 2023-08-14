package com.wentong.ladder.functions.token;

import com.googlecode.aviator.runtime.type.AviatorObject;
import com.wentong.ladder.annotations.JavaFunction;
import com.wentong.ladder.functions.AbstractLadderFunction;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@JavaFunction
public class GetTokenLadderFunction extends AbstractLadderFunction {

    @Override
    public AviatorObject call(Map<String, Object> env) {

        return null;
    }
}
