package algo.lecture.queue;

import java.util.Iterator;

import static com.google.common.base.Preconditions.*;

/**
 * Created on 15/10/9.
 * Author: ylgrgyq
 */
public class LinkedQueue<T> implements MyQueue<T>{
    private class Node{
        private T item;
        private Node next;

        public Node(T item){
            this.item = item;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void enqueue(T item) {
        Node oldTail = tail;
        tail = new Node(item);
        if (isEmpty()){
            head = tail;
        } else {
            oldTail.next = tail;
        }

        ++size;
    }

    @Override
    public T dequeue() {
        checkState(! isEmpty());

        Node oldHead = head;
        head = head.next;
        oldHead.next = null;

        --size;
        return oldHead.item;
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
        private Node head;

        public Itr(Node head){
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public T next() {
            T item = head.item;
            head = head.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> q = new LinkedQueue<>();
        for (int i = 0; i < 32; i++) {
            q.enqueue(i);
        }

        q.forEach(System.out::println);

        for (int i = 0; i < 32; i++) {
            System.out.println(q.dequeue());
        }
    }
}
