package com.wentong.ladder.functions.token;

import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;
import com.wentong.ladder.annotations.JavaFunction;
import com.wentong.ladder.functions.AbstractLadderFunction;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@JavaFunction
public class GetTokenLadderFunction extends AbstractLadderFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {

        return AviatorRuntimeJavaType.valueOf("2f5451e57f51264f56d781de60f980da2e59193a");
    }
}
