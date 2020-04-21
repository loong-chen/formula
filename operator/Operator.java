package com.storlead.salary.formula.operator;

import java.math.BigDecimal;

/**
 * 操作
 * @author chenlong
 * @date 2020-03-12
 */
public interface Operator<T> {

    /**
     * 操作元素,可能是操作符,可能是操作数
     */
    T getValue();

    /**
     * @return {@link Integer} 操作符的优先级
     */
    int getPriority();

    /**
     * 操作符的执行逻辑
     * @param arg1 参数1
     * @param arg2 参数2
     * @return {@link BigDecimal}操作符的执行结果
     */
    BigDecimal execute(BigDecimal arg1, BigDecimal arg2);

    /**
     * 是否是操作数
     *
     * @param
     * @return boolean
     * @author chenlong
     * @since 2020/3/12 9:43
     **/
    boolean isValue();


}
