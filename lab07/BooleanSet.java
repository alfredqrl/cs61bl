/**
 * Represent a set of nonnegative ints from 0 to maxElement for some initially
 * specified maxElement.
 */
import java.util.*;
public class BooleanSet implements SimpleSet {

    private boolean[] contains;
    private int size;

    /** Initializes a set of ints from 0 to maxElement. */
    public BooleanSet(int maxElement) {
        contains = new boolean[maxElement + 1];
        size = 0;
    }

    /** Adds k to the set. */
    public void add(int k) {
        // TODO
        for (int i = 0; i < contains.length; i++){
            if (i == k){
                contains[i] = true;
            }
        }
    }

    /** Removes k from the set. */
    public void remove(int k) {
        // TODO
        for (int i = 0; i < contains.length; i++){
            if (i == k){
                contains[i] = false;
            }
        }
    }

    /** Return true if k is in this set, false otherwise. */
    public boolean contains(int k) {
        return contains[k];
    }

    /** Return true if this set is empty, false otherwise. */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /** Returns the number of items in the set. */
    public int size() {
        //TODO
        int s = 0;
        for (int i = 0; i < contains.length; i++){
            if (contains[i]){
                s++;
            }
        }
        return s;
    }

    /** Returns an array containing all of the elements in this collection. */
    public int[] toIntArray() {
        // TODO
        //int [] a = new int[100];
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < contains.length; i++){
            if (contains[i]){
                list.add(i);
            }
        }
        int [] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            a[i] = list.get(i);
        }
        return a;
    }
}