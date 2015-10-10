package algo.lecture.stack;

/**
 * Created on 15/10/8.
 * Author: ylgrgyq
 */
public interface MyStack<T> extends Iterable<T>{
    void push(T item);

    T pop();

    boolean isEmpty();

    int size();
}
