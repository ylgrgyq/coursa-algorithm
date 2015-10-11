package algo.lecture.stack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15/10/11.
 * Author: ylgrgyq
 */
public class Context {
    public ArrayStack<Operator<Integer>> operators = new ArrayStack<>();
    public ArrayStack<Integer> values = new ArrayStack<>();

    public List<Character> pendingNumber = new ArrayList<>();

    public static void main(String[] args) {
        Context a = new Context();
        Serializable s = 2;
        a.values.push(2);
    }
}
