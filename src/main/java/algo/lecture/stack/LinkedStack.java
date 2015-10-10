package algo.lecture.stack;

import static com.google.common.base.Preconditions.*;

import java.util.Iterator;

/**
 * Created on 15/10/8.
 * Author: ylgrgyq
 */
public class LinkedStack<T> implements MyStack<T> {
    private class Node{
        T item;
        Node next;

        Node(T item){
            this.item = item;
        }
    }

    private Node head;
    private int size;

    @Override
    public void push(T item) {
        checkArgument(item != null);

        Node oldHead = head;
        head = new Node(item);
        head.next = oldHead;

        ++size;
    }

    @Override
    public T pop() {
        checkState(!isEmpty());

        Node item = head;
        head = head.next;
        item.next = null;

        --size;

        return item.item;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
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
        private Node cursor;

        public Itr(Node head){
            this.cursor = head;
        }

        @Override
        public boolean hasNext() {
            return cursor != null && cursor.next != null;
        }

        @Override
        public T next() {
            cursor = cursor.next;
            return cursor.item;
        }
    }

    public static void main(String[] args) throws Exception{
        MyStack<Integer> s = new LinkedStack<>();

        s.forEach(System.out::println);

        for (int i = 0; i < 20; i++) {
            s.push(i);
        }

        s.forEach(System.out::println);

        s.pop();

        Thread.sleep(1000);
    }
}
