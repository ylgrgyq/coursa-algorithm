package algo.lecture.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 15/10/11.
 * Author: ylgrgyq
 */
public class OperatorSymbolProcessor extends SymbolProcessor {
    private final Map<Character, Operator<Integer>> operatorMap = new HashMap<>();

    public OperatorSymbolProcessor(){
        registOperator('+', (v1, v2) -> v1 + v2);
        registOperator('-', (v1, v2) -> v1 - v2);
        registOperator('*', (v1, v2) -> v1 * v2);
        registOperator('/', ((v1, v2) -> v1 / v2));
    }

    public void registOperator(Character symbol, Operator<Integer> op){
        operatorMap.put(symbol, op);
    }

    @Override
    public boolean process(Character symbol, Context ctx) {
        Operator<Integer> op = operatorMap.get(symbol);
        if (op != null){
            super.process(symbol, ctx);

            ctx.operators.push(op);

            System.out.println("Push operator " + symbol);
            return true;
        }

        return false;
    }
}
