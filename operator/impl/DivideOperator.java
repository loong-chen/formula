package com.storlead.salary.formula.operator.impl;

import com.storlead.salary.formula.operator.OperatorEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideOperator extends OperatorAdapter<OperatorEnum> {

    public DivideOperator() {
        this.isValue = false;
        this.t = OperatorEnum.getByOp('/');
        this.priority = t.getPriority();
    }


    @Override
    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
        return arg1.divide(arg2, RoundingMode.HALF_UP);
    }

    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2, int scale, RoundingMode mode) {
        return arg1.divide(arg2, scale, mode);
    }
}
