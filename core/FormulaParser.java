package com.storlead.salary.formula.core;

import com.storlead.salary.formula.operator.Operator;
import com.storlead.salary.formula.operator.OperatorEnum;
import com.storlead.salary.formula.operator.impl.LeftBracketOperator;
import com.storlead.salary.formula.operator.impl.OperatorAdapter;
import com.storlead.salary.formula.operator.impl.ValueOperator;

import java.util.Stack;


/**
 * 公式解析
 *
 * @author chenlong
 * @date 2020-03-12
 */
class FormulaParser {

    /**
     * 解析公式
     *
     * @param formula
     * @return
     */
    Stack<Operator> parse(String formula) {
        formula = removeSpace(formula);
        char[] chars = formula.toCharArray();

        Stack<Operator> numStack = new Stack<>();
        Stack<Operator<OperatorEnum>> operatorStack = new Stack<>();

        int i = 0;
        while (i < chars.length) {

            switch (OperatorEnum.getByOp(chars[i])) {
                case LEFT_BRACKET:
                    Operator<OperatorEnum> leftBracket = new LeftBracketOperator();
                    operatorStack.push(leftBracket);
                    break;
                case RIGHT_BRACKET:
                    Operator<OperatorEnum> top = null;
                    do {
                        top = operatorStack.pop();
                        if (top.getValue() != OperatorEnum.LEFT_BRACKET) {
                            numStack.push(top);
                        }

                    } while (top.getValue() != OperatorEnum.LEFT_BRACKET);
                    break;

                case PLUS:
                case SUBTRACT:
                case MULTIPLY:
                case DIVIDE:
                case POW:
                    Operator<OperatorEnum> operator = OperatorAdapter.creatOperator(chars[i]);
                    if (operatorStack.isEmpty()) {
                        operatorStack.push(operator);
                    } else {
                        Operator<OperatorEnum> top1 = operatorStack.peek();
                        if (top1 instanceof LeftBracketOperator) {
                            operatorStack.push(operator);
                        } else {
                            while (!operatorStack.isEmpty() && operatorStack.peek().getPriority() >= operator.getPriority()) {
                                numStack.push(operatorStack.pop());
                            }
                            operatorStack.push(operator);

                        }

                    }
                    break;


                default:
                    // 不是括号和操作符，统一视作代数。
                    StringBuilder builder = new StringBuilder();
                    int j = i;
                    do {
                        builder.append(chars[j]);
                        j++;
                    } while (j < chars.length && OperatorEnum.getByOp(chars[j]) == OperatorEnum.NUMBER);
                    i = j - 1;
                    ValueOperator valueOperator = new ValueOperator(builder.toString());
                    numStack.push(valueOperator);
                    break;
            }
            i++;

        }

        while (!operatorStack.isEmpty()) {
            numStack.push(operatorStack.pop());
        }
        return numStack;
    }

    /**
     * 去除掉公式中所有的空格
     *
     * @param formula 公式
     * @return {@link String} 去除掉空格的公式
     */
    private static String removeSpace(String formula) {
        return formula.replaceAll(" ", "");
    }

}
