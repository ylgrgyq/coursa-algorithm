package algo.lecture.queue;

import java.util.Iterator;

import static com.google.common.base.Preconditions.*;

/**
 * Created on 15/10/9.
 * Author: ylgrgyq
 */
public class ArrayQueue<T> implements MyQueue<T>{
    public static final int minInitCapacity = 2;

    private T[] data;
    private int capacity;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue(){
        this(minInitCapacity);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int initCapacity){
        data = (T[]) new Object[initCapacity];
        capacity = initCapacity;
    }

    @Override
    public void enqueue(T item) {
        if (isFull()){
            resize(capacity * 2);
        }

        data[tail] = item;
        tail = next(tail);

        ++size;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity){
        T[] newData = (T[]) new Object[newCapacity];
        int src = head;
        int dest = 0;
        while (src != tail){
            newData[dest++] = data[src];
            src = next(src);
        }

        head = 0;
        tail = dest;
        data = newData;
        capacity = newCapacity;
    }

    @Override
    public T dequeue() {
        checkState(! isEmpty());

        T item = data[head];
        data[head] = null;
        head = next(head);

        --size;

        if (size == capacity / 4 && capacity / 2 >= minInitCapacity){
            resize(capacity / 2);
        }

        return item;
    }

    private int next(int current){
        return ++current & (capacity - 1);
    }

    // when next(tail) == head means queue is full
    // when tail == head means queue is empty
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull(){
        return next(tail) == head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr(head);
    }

    private class Itr implements Iterator<T> {
        int cursor;

        public Itr(int head){
            this.cursor = head;
        }

        @Override
        public boolean hasNext() {
            return cursor != tail;
        }

        @Override
        public T next() {
            T item = data[cursor];
            cursor = ArrayQueue.this.next(cursor);

            return item;
        }
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        for (int i = 0; i < 32; i++) {
            q.enqueue(i);
        }

        q.forEach(System.out::println);

        for (int i = 0; i < 32; i++) {
            System.out.println(q.dequeue());
        }

        System.out.println("haha");
    }
}
