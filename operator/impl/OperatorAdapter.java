package com.storlead.salary.formula.operator.impl;

import com.storlead.salary.formula.operator.Operator;
import com.storlead.salary.formula.operator.OperatorEnum;

import java.math.BigDecimal;

/**
 * 操作节点适配器
 *
 * @author chenlong
 * @date 2020/3/12 9:44
 */
public class OperatorAdapter<T> implements Operator<T> {

    T t;
    boolean isValue;
    int priority;


    @Override
    public T getValue() {
        return t;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public BigDecimal execute(BigDecimal arg1, BigDecimal arg2) {
        return null;
    }

    @Override
    public boolean isValue() {
        return isValue;
    }

    public static Operator<OperatorEnum> creatOperator(char op){
        switch (OperatorEnum.getByOp(op)){
            case PLUS:
                Operator<OperatorEnum> plusOperator = new PlusOperator();
                return plusOperator;
            case SUBTRACT:
                Operator<OperatorEnum> subtractOperator = new SubtractOperator();
                return subtractOperator;
            case MULTIPLY:
                Operator<OperatorEnum> multiplyOperator = new MultiplyOperator();
                return multiplyOperator;
            case DIVIDE:
                Operator<OperatorEnum> divideOperator = new DivideOperator();
                return divideOperator;
            case POW:
                Operator<OperatorEnum> powOperator = new PowOperator();
                return powOperator;
            default:
                return null;

        }


    }

}