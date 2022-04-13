import java.util.Arrays;

/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 *
 * @author Maurice Lee and Wan Fung Chui
 */

public class IntList {

    /** The integer stored by this node. */
    public int item;
    /** The next node in this IntList. */
    public IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }


    /** Returns an IntList consisting of the elements in ITEMS.
     * IntList L = IntList.list(1, 2, 3);
     * System.out.println(L.toString()) // Prints 1 2 3 */
    public static IntList of(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        //TODO: YOUR CODE HERE
        IntList head = this;
        IntList curr = head;
        int i = 0;
        while (curr != null){
            if (i == position){
                return curr.item;
            }
            curr = curr.next;
            i++;
        }
        if (position < 0 || position >= i){
            throw new IllegalArgumentException("Index out of range!");
        }
        return -1;
    }
    
    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "1 2 3".
     *
     * @return The String representation of the list.
     */

    public String toString() {
        //TODO: YOUR CODE HERE
        IntList head = this;
        IntList curr = head;
        StringBuilder re = new StringBuilder();
        int [] l = new int[100];
        Arrays.fill(l, -100);
        int i = 0;
        while (curr != null){
            l[i] = curr.item;
            curr = curr.next;
            i++;
        }
        int j;
        for (j = 0; j < i; j++){
            if (j != -100 && j != i - 1){
                re.append(l[j]).append(" ");
            }else{
                if (j == i - 1){
                    re.append(l[j]);
                }
            }
        }
        return re.toString();
        //return null;
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * NOTE: A full implementation of equals requires checking if the
     * object passed in is of the correct type, as the parameter is of
     * type Object. This also requires we convert the Object to an
     * IntList, if that is legal. The operation we use to do this is called
     * casting, and it is done by specifying the desired type in
     * parenthesis. An example of this is on line 84.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IntList)) {
            return false;
        }
        IntList otherLst = (IntList) obj;

        //TODO: YOUR CODE HERE
        IntList headThis = this;
        IntList headOther = otherLst;
        IntList currThis = headThis;
        IntList currOther = headOther;
        int lenThis = 0;
        int lenOther = 0;
        while (currThis != null){
            lenThis++;
            currThis = currThis.next;
        }
        while (currOther != null){
            lenOther++;
            currOther = currOther.next;
        }
        if (lenOther == lenThis){
            currThis = headThis;
            currOther = headOther;
            while (currThis != null){
                if (currThis.item == currOther.item){
                    currThis = currThis.next;
                    currOther = currOther.next;
                }else{
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Adds the given value at the end of the list.
     *
     * @param value, the int to be added.
     */
    public void add(int value) {
        //TODO: YOUR CODE HERE
        IntList head = this;
        IntList curr = head;
        int i = 0;
        while (curr.next != null){
            curr = curr.next;
        }
        IntList newItem = new IntList(value);
        curr.next = newItem;
        newItem.next = null;
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        //TODO: YOUR CODE HERE
        IntList head = this;
        IntList curr = head;
        int small = 1000000;
        while (curr != null){
            if (curr.item < small){
                small = curr.item;
            }
            curr = curr.next;
        }
        return small;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        //TODO: YOUR CODE HERE
        IntList head = this;
        IntList curr = head;
        int sum = 0;
        while (curr != null){
            sum += curr.item * curr.item;
            curr = curr.next;
        }
        return sum;
    }

    /**
     * Destructively squares each item of the list.
     *
     * @param L list to destructively square.
     */
    public static void dSquareList(IntList L) {
        while (L != null) {
            L.item = L.item * L.item;
            L = L.next;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.item * L.item, null);
        IntList ptr = res;
        L = L.next;
        while (L != null) {
            ptr.next = new IntList(L.item * L.item, null);
            L = L.next;
            ptr = ptr.next;
        }
        return res;
    }

    /** Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.item * L.item, squareListRecursive(L.next));
    }

    /**
     * Returns a new IntList consisting of A followed by B,
     * destructively.
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
    public static IntList dcatenate(IntList A, IntList B) {
        //TODO: YOUR CODE HERE
        if (A == null && B == null){
            return null;
        }else{
            if (A == null){
                return B;
            }
            if (B == null){
                return A;
            }
        }
        IntList headA = A;
        IntList headB = B;
        IntList currA = headA;
        while(currA.next != null){
            currA = currA.next;
        }
        currA.next = headB;
        currA = headA;
        return currA;
    }

    /**
     * Returns a new IntList consisting of A followed by B,
     * non-destructively.
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
     public static IntList catenate(IntList A, IntList B) {
        //TODO: YOUR CODE HERE
         IntList headA = A;
         IntList headB = B;
         IntList curr = A;
         if (A == null && B == null){
             return null;
         }else{
             if (A == null){
                 return B;
             }
             if (B == null){
                 return A;
             }
         }

        IntList C = new IntList(A.item,null);
         IntList currC = C;
         while (curr.next != null){
             curr = curr.next;
             currC.next = new IntList(curr.item,null);
             currC = currC.next;
         }
         currC.next = headB;
        return C;
     }
}
