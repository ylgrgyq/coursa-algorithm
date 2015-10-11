package algo.lecture.stack;

/**
 * Created on 15/10/11.
 * Author: ylgrgyq
 */
public class ControlSymbolProcessor extends SymbolProcessor {
    @Override
    public boolean process(Character symbol, Context ctx) {
        if ('(' == symbol){
            super.process(symbol, ctx);

            System.out.println("Pass (");
            return true;
        } else if (')' == symbol){
            super.process(symbol, ctx);

            Integer v1 = ctx.values.pop();
            Integer v2 = ctx.values.pop();
            Operator<Integer> operator = ctx.operators.pop();
            Integer result = operator.apply(v1, v2);
            ctx.values.push(result);
            System.out.println(operator + " applys to " + v1 + " and " + v2);
            return true;
        }
        return false;
    }
}
