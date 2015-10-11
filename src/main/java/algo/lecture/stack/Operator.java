package algo.lecture.stack;

/**
 * Created on 15/10/11.
 * Author: ylgrgyq
 */
public interface Operator<T> {
    T apply(T v1, T v2);
}
