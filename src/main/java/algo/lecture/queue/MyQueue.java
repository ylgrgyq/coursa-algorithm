package algo.lecture.queue;

/**
 * Created on 15/10/9.
 * Author: ylgrgyq
 */
public interface MyQueue<T> extends Iterable<T>{
    public void enqueue(T item);

    public T dequeue();

    public boolean isEmpty();

    public int size();
}
