package com.storlead.salary.formula.operator.impl;

import com.storlead.salary.formula.operator.OperatorEnum;

/**
 * 右括号
 *
 * @author chenlong
 * @date 2020/3/12 10:18
 */
public class RightBracketOperator extends OperatorAdapter<OperatorEnum>  {

    public RightBracketOperator() {
        this.isValue = false;
        this.t = OperatorEnum.getByOp(')');
        this.priority = t.getPriority();
    }
}