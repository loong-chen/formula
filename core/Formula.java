package com.storlead.salary.formula.core;

import com.storlead.salary.formula.operator.Operator;
import com.storlead.salary.formula.operator.impl.DivideOperator;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 计算对象
 *
 * @author: chenlong
 * @date: 2020-03-12
 */
@Slf4j
public class Formula {

    private Stack<Operator> postfixExpression;
    private int scale = 15;
    private RoundingMode mode = RoundingMode.HALF_UP;

    /**
     * 方法将会对传入公式进行解析，生成  postfixExpression，之后可以根据这个模板重复运算
     *
     * @param formula {@link String} 字符串公式 可以是直接运算的， 也可以是包含代数的
     */
    public Formula(String formula) {
        postfixExpression = new FormulaParser().parse(formula);
    }

    public Formula(String formula, int scale, RoundingMode mode) {
        this(formula);
        this.scale = scale;
        this.mode = mode;
    }

    public Formula(String formula, int scale) {
        this(formula);
        this.scale = scale;
    }

    /**
     * 表达式中没有变量
     *
     * @param
     * @return java.math.BigDecimal
     * @author chenlong
     * @since 2020/3/12 15:17
     **/
    public BigDecimal calculate() {
        return calculate(null);
    }


    public BigDecimal calculate(Map<String, Object> parameters) {
        return calculate(parameters, null);
    }


    /**
     * 表达式中含变量的计算
     *
     * @param parameters 变量对应的值
     * @return java.math.BigDecimal
     * @author chenlong
     * @since 2020/3/12 15:18
     **/
    public BigDecimal calculate(Map<String, Object> parameters, Map<String, Object> refMap) {

        Stack<BigDecimal> number = new Stack<>();

        for (Operator operator : postfixExpression) {
            if (operator.isValue()) {
                String key = (String) operator.getValue();

                Object val = null;
                if (key.contains("::")) {
                    //如果带冒号,就是引用
                    String[] temp = key.split("::");
                    String className = temp[0];
                    String fieldName = temp[1];

                    Object obj = refMap.get(className);
                    //利用反射取 obj对象的 filedName字段的值
                    String firstLetter = fieldName.substring(0, 1).toUpperCase();
                    String getter = "get" + firstLetter + fieldName.substring(1);
                    Method method = null;
                    try {
                        method = obj.getClass().getMethod(getter);
                        val = method.invoke(obj);
                        number.push(new BigDecimal(String.valueOf(val)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else{
                    //判断是否是数字开头,是数字开头表示是常量
                    if(key.charAt(0) > '0' && key.charAt(0) < '9'){
                        number.push(new BigDecimal(key));
                    }else{
                        //否则是变量，从参数中取值
                        val = parameters.get(key);
                        if(val == null){
                            val = BigDecimal.ZERO;
                        }
                        number.push(new BigDecimal(String.valueOf(val)));
                    }
                }

            } else {

                BigDecimal first = number.pop();
                BigDecimal second = number.pop();

                BigDecimal result;
                if (operator instanceof DivideOperator) {
                    log.error("(DivideOperator) operator).execute: second {} first {}",second,first);

                    result = ((DivideOperator) operator).execute(second, first, scale, mode);
                } else {
                    log.error("operator.execute: second {} first {}",second,first);
                    result = operator.execute(second, first);
                }
                number.push(result);
            }
        }
        return number.get(0);
    }

    public static void main(String[] args) {
        Formula test = new Formula("10/3", 2);
        Map<String, Object> param = new HashMap<>();
        param.put("a1", 9.3);
        param.put("b2", 7);
        param.put("c3", 1);
        System.out.println(test.calculate());
    }


}
