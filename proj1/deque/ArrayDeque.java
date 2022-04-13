package deque;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayDeque<E> implements Deque<E> {
    private float usage;
    private int size;
    private int nextFirst;
    private int nextLast;
    protected E[] Array;

    public ArrayDeque() {
        Array = (E[]) new Object[8];
        size = 0;
        nextFirst = 2;
        usage = 0;
        if (nextFirst == 7) {
            nextLast = 0;
        } else {
            nextLast = nextFirst + 1;
        }
    }

    private boolean checkUsageR() {
        return (usage < 0.25 && (Array.length > 16));
    }

    private void resize() {

        if (checkUsageR() || (size == Array.length)) {

            int targetSize = Array.length;

            if (checkUsageR()) {
                targetSize /= 2;
            } else {
                targetSize *= 2;
            }

            E[] NewArray = (E[]) new Object[targetSize];
            int maxIndex = 0;
            int startIndex = 0;

            if (checkUsageR()) {
                for (int i = 0; i < Array.length - 1; i++) {
                    if (Array[i] == null && Array[i + 1] != null) {
                        startIndex = i + 1;
                    }
                    if (Array[i] != null && Array[i + 1] == null) {
                        maxIndex = i;
                    }
                }
                System.arraycopy(Array, startIndex, NewArray, 1, maxIndex - startIndex + 1);

                Array = NewArray;
                nextFirst = 0;
                nextLast = maxIndex - startIndex + 2;

            } else {
                maxIndex = Array.length;
                startIndex = 0;

                System.arraycopy(Array, startIndex, NewArray, 1, maxIndex - startIndex);

                Array = NewArray;
                nextFirst = 0;
                nextLast = maxIndex - startIndex + 1;
            }

        }

    }

    private int addpointerFirst(int nextFirst) {
        if (nextFirst != 0) {
            nextFirst -= 1;
        } else {
            nextFirst = Array.length - 1;
        }
        return nextFirst;
    }

    private int addpointerLast(int nextLast) {
        if (nextLast != Array.length - 1) {
            nextLast += 1;
        } else {
            nextLast = 0;
        }
        return nextLast;
    }

    public void addFirst(E item) {
        resize();

        Array[nextFirst] = item;
        size += 1;
        usage = size / Array.length;
        nextFirst = addpointerFirst(nextFirst);
    }

    public void addLast(E item) {
        resize();

        Array[nextLast] = item;
        size += 1;
        usage = size / Array.length;
        nextLast = addpointerLast(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(Array[i] + " ");
        }
        System.out.println(Array[nextLast - 1]);
    }

    private int rmpointerFirst(int nextFirst) {
        if (nextFirst != Array.length - 1) {
            nextFirst += 1;
        } else {
            nextFirst = 0;
        }
        return nextFirst;
    }

    private int rmpointerLast(int nextLast) {
        if (nextLast != 0) {
            nextLast -= 1;
        } else {
            nextLast = Array.length - 1;
        }
        return nextLast;
    }

    public E removeFirst() {

        int index = rmpointerFirst(nextFirst);
        E returnValue = Array[index];
        Array[index] = null;
        size -= 1;
        usage = (float) size / (float) Array.length;

        nextFirst = index;
        resize();

        return returnValue;
    }

    public E removeLast() {

        int index = rmpointerLast(nextLast);
        E returnValue = Array[index];
        Array[index] = null;
        size -= 1;
        usage = (float) size / (float) Array.length;
        nextLast = index;
        resize();

        return returnValue;

    }

    public E get(int index) {
        if (index >= this.Array.length) {
            return null;
        } else {
            return this.Array[index];
        }

    }

    public E getRecursive(int index){
        return null;
    }

}


