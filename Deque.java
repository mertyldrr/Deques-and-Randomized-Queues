import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *              -- ABOUT DEQUE --
 * A double-ended queue or deque is a generalization of a stack 
 * and a queue that supports adding and removing items from 
 * either the front or the back of the data structure.
 */

public class Deque<Item> implements Iterable<Item>
{
    // Store Items in a doubly-linked list
    private Node head, tail;
    private int n;

    // node class 
    private class Node
    {
        Item item;
        Node next;
        /* using previous for removeLast() method, we want constant 
        time for this operation to meet performance requirements */
        Node prev;
        
        private Node(Item item)
        {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    // construct an empty deque
    public Deque()
    {
        head = null;
        tail = null;
        n = 0;
    }

    // iterator
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = head;

        public boolean hasNext() { return (current != null); }
        public void remove() { throw new UnsupportedOperationException(); }
        // return the item in current and advance current to the next node
        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return (n == 0);
    }

    // size of the deque
    public int size()
    {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        Node oldHead = head;
        head = new Node(item);
        if (isEmpty()) tail = head;
        else
        {
            head.next = oldHead;
            oldHead.prev = head;
        }
        n++; 
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        Node oldTail = tail;
        tail = new Node(item);
        if (isEmpty()) head = tail;
        else
        {
            oldTail.next = tail;
            tail.prev = oldTail;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.item;
        if (head.next != null) head = head.next;
        head.prev = null;
        n--;
        if (isEmpty())
        {
            head = null;
            tail = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = tail.item;
        if (tail.prev != null) tail = tail.prev;
        tail.next = null;
        n--;
        if (isEmpty())
        {
            head = null;
            tail = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    // unit testing
    public static void main(String[] args)
    {
        Deque<Integer> test = new Deque<>();
        test.addFirst(1);
        test.removeFirst();
    } 
}