package algo.lecture.stack;

import java.util.Arrays;
import java.util.Iterator;

import static com.google.common.base.Preconditions.*;

/**
 * Created on 15/10/8.
 * Author: ylgrgyq
 */
public class ArrayStack<T> implements MyStack<T>{
    private static final int minCapacity = 16;

    private T[] bucket;
    private int head = -1;
    private int capacity;

    public ArrayStack() {
        this(minCapacity);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int initCapacity){
        bucket = (T[]) new Object[initCapacity];
        capacity = initCapacity;
    }

    @Override
    public void push(T item) {
        checkArgument(item != null);

        if (capacity == size()){
            resize(2 * capacity);
        }

        bucket[++head] = item;
    }

    private void resize(int capacity){
        if (capacity >= minCapacity){
            bucket = Arrays.copyOf(bucket, capacity);
            this.capacity = capacity;
        }
    }

    @Override
    public T pop() {
        checkState(!isEmpty());

        T item = bucket[head];
        bucket[head--] = null;

        if (size() == capacity / 4){
            resize(capacity / 2);
        }

        return item;
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }

    @Override
    public int size() {
        return head + 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new InternalIterator();
    }

    private class InternalIterator implements Iterator<T>{
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor <= head;
        }

        @Override
        public T next() {
            return bucket[cursor++];
        }
    }

    public static void main(String[] args) throws Exception{
        MyStack<Integer> s = new ArrayStack<>();

        for (int i = 0; i < 3 * minCapacity; i++) {
            s.push(i);
        }

        s.forEach(System.out::println);

        for (int i = 0; i < 2 * minCapacity - 1; i++) {
            s.pop();
        }

        s.forEach(System.out::println);

        s.pop();

        Thread.sleep(1000);
    }
}
