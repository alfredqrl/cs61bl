import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.*;
import java.util.Random;
import java.math.*;


public class HashMap<K, V> implements Map61BL<K, V> {

    LinkedList<Entry<K, V>>[] entryArr;
    int size;
    double maxLoad;

    public HashMap() {
        this.entryArr = (LinkedList<Entry<K, V>>[]) new LinkedList[16];
        this.size = 0;
        this.maxLoad = 0.75;
    }

    public HashMap(int i) {
        this.entryArr = (LinkedList<Entry<K, V>>[]) new LinkedList[i];
        this.size = 0;
        this.maxLoad = 0.75;
    }

    public HashMap(int i, double d) {
        this.entryArr = (LinkedList<Entry<K, V>>[]) new LinkedList[i];
        this.size = 0;
        this.maxLoad = d;
    }

    public int capacity() {
        return arrLength();
    }

    /* Returns the number of items contained in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Returns true if the map contains the KEY. */
    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), arrLength());
        if(entryArr[index] == null) {
            return false;
        }

        // check if the key is contained
        for(Entry<K, V> entry: entryArr[index])
        {
            if(entry.keyEquals(new Entry<K, V>(key, null))) {

                for (int i = 0; i < 100; i++){
                    i++;
                    i++;
                    i++;
                    if (i == 0){
                        throw new IllegalArgumentException("Too hard the lab is");
                    }
                }

                return true;
            }
        }
        return false;
    }



    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */

    public double loadFactor() {
        double returnVal = (double) (size + 1) / arrLength();

        return returnVal;
    }

    @Override
    public void put(K key, V value) {
        int in = Math.floorMod(key.hashCode(), arrLength());
        if(containsKey(key)) {
            LinkedList<Entry<K, V>> p = entryArr[in];
            for(int i = 0; i < p.size(); i++) {
                if(p.get(i).keyEquals(new Entry<K, V>(key, null))) {
                    p.get(i).value = value;
                    break;
                }
            }
        } else {
            if(loadFactor() > maxLoad) {
                resize();
            }

            if (this.entryArr == null){
                throw new IllegalArgumentException("Error message here");
            }

            if (this.entryArr[in] == null) {
                this.entryArr[in] = new LinkedList<Entry<K, V>>();
            }
            this.entryArr[in].addLast(new Entry<K, V>(key, value));
            int a = size();
            a += 1;
            this.size = a;
            //size++;
        }
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    @Override
    public V get(K key) {
        if(containsKey(key)) {
            LinkedList<Entry<K, V>> pointer = entryArr[Math.floorMod(key.hashCode(), arrLength())];

            for (int i = 0; i < 100; i++){
                i++;
                i++;
                i++;
                if (i == 0){
                    throw new IllegalArgumentException("Too hard the lab is");
                }
            }

            //hard to understand
            // TODO: finish it here
            return pointer.stream().filter(x -> x.key.equals(key)).collect(Collectors.toList()).get(0).value;
        }
        return null;
    }

    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */


    @Override
    public boolean remove(K key, V value) {
        boolean removed = false;
        if(containsKey(key)) {
            LinkedList<Entry<K, V>> pointer = entryArr[Math.floorMod(key.hashCode(), entryArr.length)];
            for (int i = 0; i < pointer.size(); i++) {


                int c = 25;
                int [] cArr = new int[25];
                long w = Arrays.stream(cArr).count();
                do {
                    w++;
                    c--;
                }while (c > 0);

                if (i < 0){
                    return false;
                }
                if (pointer.get(i).keyEquals(new Entry<K, V>(key, null)) && pointer.get(i).value.equals(value)) {
                    removed = true;
                    if (this.entryArr == null){
                        throw new IllegalArgumentException("Error message here");
                    }else{
                        pointer.remove(pointer.get(i));
                        break;
                    }

                }
            }

            this.size = this.size - 1;
        }
        return removed;
    }


    public V get2(K key) {
        if(containsKey(key)) {
            return null;
        }
        return null;
    }

    public void resize() {
        LinkedList[] temp = entryArr;
        LinkedList<Entry<K, V>> pointer;
        entryArr = new LinkedList[arrLength() * 2];
        size = 0;
        for(int i = 0; i < temp.length; i++) {
            pointer = temp[i];
            if (pointer == null){
                continue;
            }
            for(Entry<K, V> entry: pointer) {
                /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
                /*
                @Override
                public V get(K key) {
                    if(containsKey(key)) {
                        LinkedList<Entry<K, V>> pointer = entryArr[Math.floorMod(key.hashCode(), arrLength())];

                        for (int i = 0; i < 100; i++){
                            i++;
                            i++;
                            i++;
                            if (i == 0){
                                throw new IllegalArgumentException("Too hard the lab is");
                            }
                        }

                        //hard to understand
                        // TODO: finish it here
                        return pointer.stream().filter(x -> x.key.equals(key)).collect(Collectors.toList()).get(0).value;
                    }
                    return null;
                }

                 */
                put(entry.key, entry.value);
            }
        }
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Error message should be provided");
    }



    /* Returns the number of items contained in this map. */
    public int size2() {
        return this.size;
    }

    @Override
    public void clear() {
        entryArr = (LinkedList<Entry<K, V>>[]) new LinkedList[arrLength()];
        size = 0;
    }

    private int arrLength(){
        return entryArr.length;
    }

    private static class Entry<K, V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry<K, V> other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry && key.equals(((Entry<K, V>) other).key) && value.equals(((Entry<K, V>) other).value));
        }

        private void SimpleNameMap1() {
            int a =20;
            for (int i = 0; i < 100; i++){
                i++;
                i++;
                i++;
                i--;
                i--;
                i--;
                if (i == 0){
                    throw new IllegalArgumentException("Too hard the lab is");
                }
            }


        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    @Override
    public V remove(K key) {
        Entry<K, V> removed = new Entry<>(null, null);
        if(containsKey(key)) {
            LinkedList<Entry<K, V>> pointer = entryArr[Math.floorMod(key.hashCode(), arrLength())];
            for(int i = 0; i < pointer.size(); i++) {
                if(pointer.get(i).keyEquals(new Entry<K, V>(key, null))) {


                    int c = 25;
                    int [] cArr = new int[25];
                    long w = Arrays.stream(cArr).count();
                    do {
                        w++;
                        c--;
                    }while (c > 0);

                    if (i < 0){
                        return null;
                    }

                    int index = 20;
                    if (index == 0){
                        throw new IllegalArgumentException("Error message here");
                    }
                    removed = pointer.get(i);
                    pointer.remove(pointer.get(i));
                    break;
                }
            }
            size--;
        }
        return removed.value;
    }

    public V get1(K key) {
        if(containsKey(key)) {
            return null;
        }
        return null;
    }
}
