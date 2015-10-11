package algo.lecture.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15/10/11.
 * Author: ylgrgyq
 */
public class InfixExpression2 {
    private static List<Processor> processors = new ArrayList<>();

    static {
        processors.add(new OperatorSymbolProcessor());
        processors.add(new NumberProcessor());
        processors.add(new ControlSymbolProcessor());
    }

    public static int compute(String expression) {
        Context ctx = new Context();
        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                for(Processor p : processors){
                    if (p.process(c, ctx)){
                        break;
                    }
                }
            }
        } catch (Throwable t) {
            throw new IllegalArgumentException("Expression not valid", t);
        }

        return ctx.values.pop();
    }

    public static void main(String[] args) {
        String expression = "(111 + ((22 + 3) * (4 * 5)))";

        System.out.println(InfixExpression2.compute(expression));
    }
}
