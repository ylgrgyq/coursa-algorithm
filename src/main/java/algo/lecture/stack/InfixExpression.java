package algo.lecture.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 15/10/10.
 * Author: ylgrgyq
 */
public class InfixExpression {
    private static Map<Character, Operator<Integer>> operatorMap = new HashMap<>();

    static {
        registOperator('+', (v1, v2) -> v1 + v2);
        registOperator('-', (v1, v2) -> v1 - v2);
        registOperator('*', (v1, v2) -> v1 * v2);
        registOperator('/', ((v1, v2) -> v1 / v2));
    }

    public static void registOperator(Character symbol, Operator<Integer> op){
        operatorMap.put(symbol, op);
    }

    public static int compute(String expression) {
        ArrayStack<Operator<Integer>> operators = new ArrayStack<>();
        ArrayStack<Integer> values = new ArrayStack<>();

        try {
            for (int i = 0; i < expression.length(); ++i) {
                char c = expression.charAt(i);
                switch (c) {
                    case '(':
                        System.out.println("Pass (");
                        break;
                    case ')':
                        int v1 = values.pop();
                        int v2 = values.pop();
                        Operator<Integer> operator = operators.pop();
                        int result = operator.apply(v1, v2);
                        values.push(result);
                        System.out.println(operator + " applys to " + v1 + " and " + v2);
                        break;
                    default:
                        if (Character.isDigit(c)) {
                            String number = retrieveNumber(expression, i);
                            values.push(Integer.parseInt(number));

                            i += number.length() - 1;
                            System.out.println("Pushed value:" + number);
                        }

                        Operator<Integer> op = operatorMap.get(c);
                        if (op != null){
                            operators.push(op);
                        }
                }
            }
        } catch (Throwable t) {
            throw new IllegalArgumentException("Expression not valid", t);
        }

        return values.pop();
    }

    private static String retrieveNumber(String expression, int start){
        assert  Character.isDigit(expression.charAt(start));

        int i = start + 1;
        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
            ++i;
        }

        return expression.substring(start, i);
    }

    public static void main(String[] args) {
        String expression = "(111 + ((22 + 3) * (4 * 5)))";

        System.out.println(InfixExpression.compute(expression));
    }

}
