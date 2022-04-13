import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    public ArrayList<E> contents;
    public int size;
    // TODO: YOUR CODE HERE (no code should be needed here if not
    private int side;
    // implementing the more optimized version)

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    public void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        // TODO: YOUR CODE HERE
        int reVal = index;
        reVal = reVal * 2;
        return reVal;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        // TODO: YOUR CODE HERE
        int reVal = index;
        reVal = reVal * 2 + 1;
        return reVal;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        // TODO: YOUR CODE HERE
        int reVal = index;
        reVal = reVal / 2;
        return reVal;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. If the elements are equal, return either index. */
    private int min(int index1, int index2) {
        // TODO: YOUR CODE HERE
        try{
            if (Objects.requireNonNull(getElement(index1)).compareTo(Objects.requireNonNull(getElement(index2))) > 0){
                return index2;
            }else{
                return index1;
            }
        }catch (Exception e){
            if (getElement(index1) == null){
                return index2;
            }
            if (getElement(index2) == null){
                return index1;
            }
        }
        return 0;
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E findMin() {
        // TODO: YOUR CODE HERE
        return contents.get(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        // TODO: YOUR CODE HERE
        int p = getParentOf(index);
        if (p != 0 && Objects.requireNonNull(getElement(p)).compareTo(Objects.requireNonNull(getElement(index))) > 0){
            // require parent index > 0
            swap(index, p);
            bubbleUp(p);
        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        // TODO: YOUR CODE HERE
        int sizeHere = size();
        int left = getLeftOf(index);
        int right = getRightOf(index);
        int min = min(left, right);
        bubbleDownHelper(left, right, min, index);
    }

    private void bubbleDownHelper(int leftIdx, int rightIdx, int minIdx, int index) {
        if (getElement(minIdx) != null){
            if (getElement(minIdx).compareTo(getElement(index)) < 0){
                swap(index, minIdx);
                bubbleDown(minIdx);
            }
        }else if (getElement(leftIdx) != null){
            if (getElement(index).compareTo(getElement(leftIdx)) > 0){
                swap(index, leftIdx);
                bubbleDown(leftIdx);
            }
        }else if (getElement(rightIdx) != null){
            if (getElement(leftIdx).compareTo(getElement(index)) < 0){
                swap(index, rightIdx);
                bubbleDown(rightIdx);
            }
        }
    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        // TODO: YOUR CODE HERE
        return this.size;
    }



    /* Inserts ELEMENT into the MinHeap. If ELEMENT is already in the MinHeap,
       throw an IllegalArgumentException.*/
    public void insert(E element) {
        // TODO: YOUR CODE HERE
        //int sizeHere = size();
        if (contains(element)){
            throw new IllegalArgumentException();
        }
        this.size ++;
        // if we only have one element here

        if (size == 1){
            contents.add(size,element);
        }else{
            contents.add(size, element);
            // require bubble up when necessary
            bubbleUp(size);
        }

    }

    /* Returns and removes the smallest element in the MinHeap. */
    public E removeMin() {
        // TODO: YOUR CODE HERE
        E elementToBeRemoved = getElement(1);
        int sizeHere = this.size;
        // swap the item when we call back
        // swap the item with the item before it
        swap(1,sizeHere);
        // remove the size before it
        contents.remove(sizeHere);
        // move down
        bubbleDown(1);
        // decrease the size number after remove
        this.size = sizeHere - 1;
        // return the removed element
        return elementToBeRemoved;
    }

    /* Replaces and updates the position of ELEMENT inside the MinHeap, which
       may have been mutated since the initial insert. If a copy of ELEMENT does
       not exist in the MinHeap, throw a NoSuchElementException. Item equality
       should be checked using .equals(), not ==. */
    public void update(E element) {
        // TODO: YOUR CODE HERE
        int sizeHere = size();
        if (!contains(element)){
            throw new NoSuchElementException();
        }
        /*
        int indexInTheHeap = 0;
        boolean found = false;
        for (int i = 0; i < sizeHere; i++){
            if (Objects.equals(getElement(i), element)){
                indexInTheHeap = i;
                found = true;
            }
        }

        if (!found){
            indexInTheHeap = sizeHere + 5;
        }

         */

        // set element at correct position
        int index = contents.indexOf(element);
        setElement(index, element);
        bubbleUp(index);
        bubbleDown(index);
    }

    /* Returns true if ELEMENT is contained in the MinHeap. Item equality should
       be checked using .equals(), not ==. */
    public boolean contains(E element) {
        // TODO: YOUR CODE HERE
        /*
        for (int i = 0; i < size(); i++){
            if (getElement(i).equals(element.item())){
                return true;
            }
        }

         */
        return contents.contains(element);

    }
}
