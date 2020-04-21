package com.storlead.salary.formula.operator.impl;

import com.storlead.salary.formula.operator.OperatorEnum;

import java.math.BigDecimal;

public class PowOperator extends OperatorAdapter<OperatorEnum> {

    public PowOperator() {
        this.isValue = false;
        this.t = OperatorEnum.getByOp('^');
        this.priority = t.getPriority();
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
        return arg1.pow(arg2.intValue());
    }
}
