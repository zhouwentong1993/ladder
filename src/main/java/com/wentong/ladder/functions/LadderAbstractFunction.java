package com.wentong.ladder.functions;

import com.googlecode.aviator.runtime.function.AbstractFunction;

import static com.wentong.ladder.utils.CommonUtil.makeFirstLetterLowerCase;

public abstract class LadderAbstractFunction extends AbstractFunction {

    @Override
    public String getName() {
        return makeFirstLetterLowerCase(getClass().getSimpleName());
    }

}
