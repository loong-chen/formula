package com.storlead.salary.formula.operator;

/**
 * 操作符类型
 *
 * @author chenlong
 * @date 2020/3/11 17:26
 */
public enum  OperatorEnum{

    /**
     * 加
     */
    PLUS(1,'+'),

    /**
     * 减
     */
    SUBTRACT(1,'-'),

    /**
     * 乘
     */
    MULTIPLY(2,'*'),

    /**
     * 除
     */
    DIVIDE(2,'/'),

    /**
     * 左括号
     */
    LEFT_BRACKET(10,'('),

    /**
     * 右括号
     */
    RIGHT_BRACKET(-10,')'),

    /**
     * 幂运算
     */
    POW(3,'^'),

    /**
     * 数字或者变量
     */
    NUMBER(0,' ');

    /**
     * 优先级
     */
    private int priority;

    /**
     * 操作符
     */
    private char op;

    OperatorEnum(int priority, char op){
        this.priority = priority;
        this.op = op;
    }

    public char getOp(){
        return this.op;
    }

    public int getPriority(){
        return this.priority;
    }

    public void setOp(char op){
        this.op = op;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    // 根据value返回枚举类型,主要在switch中使用
    public static OperatorEnum getByOp(char op) {
        for (OperatorEnum code : values()) {
            if (code.getOp() == op) {
                return code;
            }
        }
        return NUMBER;
    }


}