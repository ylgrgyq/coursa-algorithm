package algo.assignment.queue;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created on 15/10/14.
 * Author: ylgrgyq
 *
 * Implement:
 * public class Deque<Item> implements Iterable<Item> {
 *     public Deque()                           // construct an empty deque
 *     public boolean isEmpty()                 // is the deque empty?
 *     public int size()                        // return the number of items on the deque
 *     public void addFirst(Item item)          // add the item to the front
 *     public void addLast(Item item)           // add the item to the end
 *     public Item removeFirst()                // remove and return the item from the front
 *     public Item removeLast()                 // remove and return the item from the end
 *     public Iterator<Item> iterator()         // return an iterator over items in order from front to end
 *     public static void main(String[] args)   // unit testing
 * }
 */

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item data;
        Node next;
        Node prev;

        Node (Item data){
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item){
        Preconditions.checkNotNull(item);

        Node newNode = new Node(item);
        newNode.next = head;
        if (head != null){
            head.prev = newNode;
        } else {
            tail = newNode;
        }

        head = newNode;

        ++size;
    }

    public void addLast(Item item) {
        Preconditions.checkNotNull(item);

        Node newNode = new Node(item);
        newNode.prev = tail;
        if (tail != null){
            tail.next = newNode;
        } else {
            head = newNode;
        }

        tail = newNode;

        ++size;
    }

    public Item removeFirst() {
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        Node n = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        n.next = null;

        --size;

        if (size() == 0){
            tail = null;
        }

        return n.data;
    }

    public Item removeLast() {
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        Node n = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        n.prev = null;

        --size;

        if (size == 0){
            head = null;
        }

        return n.data;
    }

    public Iterator<Item> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Item>{
        private Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Item next() {
            if (! hasNext()){
                throw new NoSuchElementException("Queue is empty");
            }

            Item next = cursor.data;
            cursor = cursor.next;
            return next;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addFirst(2);

        for (Integer i : d) {
            System.out.println(i);
        }

        d.addLast(1);
        d.addLast(2);

        for (Integer i : d) {
            System.out.println(i);
        }

        d.removeLast();
        d.removeLast();
        d.removeFirst();
        d.removeFirst();

        for (Integer i : d) {
            System.out.println(i);
        }

        d.addFirst(4);

        for (Integer i : d) {
            System.out.println(i);
        }
    }
}

















