import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 *                -- ABOUT RANDOMIZED QUEUE --
 * A randomized queue is similar to a stack or queue, except that the item 
 * removed is chosen uniformly at random among items in the data structure
 */

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] myArray;
    private int n;
 
    // consturct an empty randomized queue
    public RandomizedQueue()
    {
        n = 0;
        myArray = (Item[]) new Object[1];
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        Item[] items;
        int current;

        // copy the Items in the original array to the Iterator and shuffle items[]
        public RandomizedQueueIterator()
        {
            current = 0;
            items = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) 
            {
                items[i] = myArray[i];
            }
            StdRandom.shuffle(items);
        }
        
        public boolean hasNext()
        {
            return current < n;
        }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            // return the item in current and advance current to the next index
            return items[current++];
        }
    }
    
    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return (n == 0);
    }
    // return the number of items on the randomized queue
    public int size()
    {
        return n;
    }

    /**
     * add the item
     * also doubles the length of the array when it is full
     */
    public void enqueue(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        if (n == myArray.length) resize(2 * myArray.length);
        myArray[n] = item;
        n++;
    }
    
    /** 
     *  remove and return a random item
     *  this operation picks an element at random to return, then overwrites
     *  the Item at that index with the last Item in the array, then sets
     *  the last Item-containing element to null
     * 
     *  also halves the length of the array when it is one-quarter empty
     */
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException();
        int x = StdRandom.uniform(n);
        Item item = myArray[x];
        myArray[x] = myArray[n-1];
        myArray[n-1] = null;
        n--;
        if (n > 0 && n == myArray.length / 4) resize(myArray.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty()) throw new NoSuchElementException();
        int x = StdRandom.uniform(n);
        return myArray[x];
    }

    // resizes the array with capacity parameter   
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
        {
            copy[i] = myArray[i];
        }
        myArray = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    // unit testing
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();
        test.enqueue(1);
        test.dequeue();
    }
}