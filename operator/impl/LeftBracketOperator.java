package com.storlead.salary.formula.operator.impl;

import com.storlead.salary.formula.operator.OperatorEnum;

/**
 * 左括号
 *
 * @author chenlong
 * @date 2020/3/12 10:16
 */
public class LeftBracketOperator extends OperatorAdapter<OperatorEnum> {

    public LeftBracketOperator() {
        this.isValue = false;
        this.t = OperatorEnum.getByOp('(');
        this.priority = 10;
    }

}