package algo.lecture.stack;

/**
 * Created on 15/10/11.
 * Author: ylgrgyq
 */
public abstract class SymbolProcessor implements Processor{
    @Override
    public boolean process(Character symbol, Context ctx) {
        if (! ctx.pendingNumber.isEmpty()){
            StringBuilder builder = new StringBuilder();
            ctx.pendingNumber.stream().forEach(builder::append);
            ctx.pendingNumber.clear();

            ctx.values.push(Integer.parseInt(builder.toString()));
            return true;
        }
        return false;
    }
}
