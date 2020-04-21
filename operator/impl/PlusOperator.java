package com.storlead.salary.formula.operator.impl;

import com.storlead.salary.formula.operator.OperatorEnum;

import java.math.BigDecimal;

public class PlusOperator extends OperatorAdapter<OperatorEnum> {
    public PlusOperator() {
        this.isValue = false;
        this.t = OperatorEnum.getByOp('+');
        this.priority = t.getPriority();
    }


    @Override
    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
        return arg1.add(arg2);
    }
}
