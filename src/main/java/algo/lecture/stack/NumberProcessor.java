package algo.lecture.stack;

/**
 * Created on 15/10/11.
 * Author: ylgrgyq
 */
public class NumberProcessor implements Processor {
    @Override
    public boolean process(Character symbol, Context ctx) {
        if (Character.isDigit(symbol) || '.' == symbol){
            ctx.pendingNumber.add(symbol);

            System.out.println("Add Number " + symbol);
            return true;
        }

        return false;
    }
}
