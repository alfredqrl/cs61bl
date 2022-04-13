package deque;

public class LinkedListDeque<T> implements Deque<T>{

    private class Node {
        public T item;
        public Node next;
        public Node prev;
        public Node(T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node that = (Node) o;
            return item == that.item;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    /*
    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(x, null, null);
        sentinel.prev = new Node(x, null, null);
        sentinel.next.next = sentinel;
        sentinel.prev.prev = sentinel;
        size = 1;
    }
    */

    @Override
    public void addFirst(T item){
        Node newNodeFirst = new Node(item, null, null);
        if (size == 0){
            newNodeFirst.next = sentinel;
            newNodeFirst.prev = sentinel;
            sentinel.next = newNodeFirst;
            sentinel.prev = newNodeFirst;
            size++;
            return;
        }
        newNodeFirst.next = sentinel.next;
        newNodeFirst.prev = sentinel;
        sentinel.next.prev = newNodeFirst;
        sentinel.next = newNodeFirst;
        size++;
    }

    @Override
    public void addLast(T item){
        Node newNodeLast = new Node(item, null, null);
        if (size == 0){
            newNodeLast.next = sentinel;
            newNodeLast.prev = sentinel;
            sentinel.next = newNodeLast;
            sentinel.prev = newNodeLast;
            size++;
            return;
        }
        newNodeLast.prev = sentinel.prev;
        sentinel.prev.next = newNodeLast;
        sentinel.prev = newNodeLast;
        newNodeLast.next = sentinel;
        size++;
    }

    @Override
    public boolean isEmpty(){
        return this.size == 0;
    }

    @Override
    public int size(){
        if (size <= 0){
            return 0;
        }
        return size;
    }

    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr.next != sentinel){
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println(curr.item);
    }

    @Override
    public T removeFirst(){
        if (sentinel.next == null){
            return null;
        }
        Node curr = sentinel.next;
        T value = curr.item;
        sentinel.next = curr.next;
        curr.next.prev = sentinel;
        curr.next = null;
        curr.prev = null;
        size = size - 1;
        return value;
    }

    @Override
    public T removeLast(){
        if (sentinel.prev == null){
            return null;
        }
        Node curr = sentinel.prev;
        T value = curr.item;
        sentinel.prev = curr.prev;
        curr.prev.next = sentinel;
        curr.prev = null;
        curr.next = null;
        size = size - 1;
        return value;
    }

    @Override
    public T get(int index){
        Node curr = sentinel.next;
        int i = 0;
        while (curr != sentinel){
            if (i == index){
                return curr.item;
            }
            i++;
            curr = curr.next;
        }
        return null;
    }

    private T recursiveHelper(int index, Node start){
        //int i = index;
        if (sentinel == start){
            return null;
        }
        if (index == 0){
            return start.item;
        }
        index--;
        return recursiveHelper(index,start.next);
    }

    public T getRecursive(int index){
        return recursiveHelper(index, sentinel.next);
    }

    @Override
    public boolean equals(Object o){
        // check if it is null
        // check if it is a deque "using instanceof"
        // cast object to the deque
        // check sizes are equal
        // ensure each element of both dequeues are equal
        // use .equals method instead of ==
        if ((o instanceof Deque)){
            int i = 0;
            Deque<T> other = (Deque<T>) o;
            if (other.size() != size){
                return false;
            }else{
                while (i < size){
                    if (!this.get(i).equals(other.get(i))){
                        return false;
                    }
                    i++;
                }
                return true;
            }
        }else{
            return false;
        }
    }

}
