package com.storlead.salary.formula.operator.impl;

/**
 * 操作数
 *
 * @author chenlong
 * @date 2020/3/12 9:54
 */
public class ValueOperator extends OperatorAdapter<String> {

    public ValueOperator(String value) {
        this.isValue = true;
        this.t = value;
        this.priority = 0;
    }

}