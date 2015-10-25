package algo.assignment.queue;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 15/10/14.
 * Author: ylgrgyq
 *
 * Implement:
 * public class RandomizedQueue<Item> implements Iterable<Item> {
 *     public RandomizedQueue()                 // construct an empty randomized queue
 *     public boolean isEmpty()                 // is the queue empty?
 *     public int size()                        // return the number of items on the queue
 *     public void enqueue(Item item)           // add the item
 *     public Item dequeue()                    // remove and return a random item
 *     public Item sample()                     // return (but do not remove) a random item
 *     public Iterator<Item> iterator()         // return an independent iterator over items in random order
 *     public static void main(String[] args)   // unit testing
 * }
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int defaultMinCapacity = 2;

    private int size;
    private int capacity;
    private int head;
    private int tail;
    private Item[] data;

    public RandomizedQueue(){
        this(defaultMinCapacity);
    }

    @SuppressWarnings("unchecked")
    public RandomizedQueue(int capacity){
        this.capacity = capacity;
        this.data = (Item[])new Object[capacity];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private int next(int index){
        return nextN(index, 1);
    }

    private int nextN(int index, int n){
        return (index + n) & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize){
        if (newSize > defaultMinCapacity) {
            Item[] newData = (Item[]) new Object[newSize];
            int dst = 0;
            int src = head;
            while (src != tail) {
                newData[dst++] = data[src];
                src = next(src);
            }

            head = 0;
            tail = dst;
            data = newData;
            capacity = newSize;
        }
    }

    public void enqueue(Item item){
        Preconditions.checkNotNull(item);

        if (next(tail) == head){
            resize(capacity * 2);
        }

        data[tail] = item;
        tail = next(tail);
        ++size;
    }

    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        int rand = ThreadLocalRandom.current().nextInt(size);
        int randIndex = nextN(head, rand);
        Item retItem = data[randIndex];

        Item h = data[head];
        if (randIndex != head) {
            data[randIndex] = h;
        }

        data[head] = null;
        head = next(head);

        --size;

        if (size == capacity / 4){
            resize(capacity / 4);
        }

        return retItem;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Itr(head);
    }

    private class Itr implements Iterator<Item>{
        private int cursor;

        public Itr(int head){
            this.cursor = head;
        }

        @Override
        public boolean hasNext() {
            return cursor != tail;
        }

        @Override
        public Item next() {
            Item item = data[cursor];
            cursor = RandomizedQueue.this.next(cursor);
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        for (Integer i : q){
            System.out.println(i);
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(q.dequeue());
        }

        for (Integer i : q){
            System.out.println(i);
        }

        try {
            q.dequeue();
        } catch (NoSuchElementException e){
            System.out.println("catch no element exception");
        }
    }
}



















