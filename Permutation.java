import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Takes a command-line integer k; reads in a sequence of N strings from
 * standard input using StdIn.readString(); and prints out exactly k of them,
 * uniformly at random.
 * 
 * @author Mert <mertyildirir14@gmail.com>
 */

public class Permutation
{
    public static void main(String[] args) 
    {
        RandomizedQueue<String> test = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
        {
            test.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) 
        {
            StdOut.println(test.dequeue());
        }
    }
}