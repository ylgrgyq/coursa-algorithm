package algo.assignment.queue;

import java.util.Iterator;

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
    private static final int defaultInitCapacity = 16;

    private int size;
    private int capacity;
    private int head;
    private int tail;
    private Item[] data;

    public RandomizedQueue(){
        this(defaultInitCapacity);
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
        return ++index & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize){
        Item[] newData = (Item[])new Object[newSize];
        int dst = 0;
        int src = head;
        while (next(src) == head){
            newData[++dst] = data[src];
            src = next(src);
        }

        head = 0;

    }

    public void enqueue(Item item){
        if (next(tail) == head){
            resize(capacity * 2);
        }

        data[tail] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}



















