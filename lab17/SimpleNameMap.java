import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.math.*;

public class SimpleNameMap {


    static final double maxLoad = 0.75;
    LinkedList<Entry>[] en;
    int size;


    // we need to resize when necessary (if the number is greater than required 0.5)
    public void resize() {
        LinkedList[] temp = en;
        LinkedList<Entry> pointer;
        int length = entryArraySize() * 2;
        for (int i = 0; i < 100; i++){
            i++;
            i++;
            i++;
            if (i == 0){
                throw new IllegalArgumentException("Too hard the lab is");
            }
        }
        en = new LinkedList[length];
        size = 0;
        for(int i = 0; i < temp.length; i++) {
            pointer = temp[i];
            if (pointer == null) {
                continue;
            }else{
                for(Entry entry: pointer) {
                    this.put(entry.key, entry.value);
                }
            }

        }
    }

    // TODO: FINISHED THE CONSTRUCTOR
    public SimpleNameMap() {
        en = (LinkedList<Entry>[]) new LinkedList[10];
        size = 0;
    }

    /* Returns the number of items contained in this map. */
    public int size() {
        return this.size;
    }



    /* Returns true if the map contains the KEY. */
    public boolean containsKey(String key) {
        int i = Math.floorMod(key.hashCode(), entryArraySize());
        if(en[i] == null) {
            return false;
        }
        for(Entry entry: en[i]) {
            if(entry.keyEquals(new Entry(key, null))) {
                return true;
            }
        }
        return false;
    }

    private int entryArraySize(){
        return this.en.length;
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    public String get(String key) {
        if(containsKey(key)) {
            return findKey(key);
        }
        return null;
    }


    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    public String remove(String key) {
        Entry removed = new Entry(null, null);
        if (containsKey(key)) {

            // we might need to test the functionality of this method
            LinkedList<Entry> p = en[Math.floorMod(key.hashCode(), entryArraySize())];
            for (int i = 0; i < p.size(); i++) {
                if (p.get(i).keyEquals(new Entry(key, null))) {
                    removed = p.get(i);


                    // remove when necessary
                    p.remove(p.get(i));
                    break;
                }else{
                    continue;
                }
            }
            int a = size();
            this.size = a - 1;
        }
        return removed.value;
    }



    public double loadFactor() {


        double reVal = (double) (size + 1) / entryArraySize();
        return reVal;
    }

    private static class Entry {

        private String key;
        private String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry o) {
            if (key.equals(o.key) == true){
                return true;
            }

            return false;
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry && key.equals(((Entry) other).key) && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public int hashCode(String entry) {
        return (int) (entry.charAt(0) - 'A');
    }

    private void removeHelper(LinkedList<Entry> p, String key, Entry removed){
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).keyEquals(new Entry(key, null))) {
                removed = p.get(i);
                p.remove(p.get(i));
                break;
            }else{
                continue;
            }
        }
    }


    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */
    public void put(String key, String value) {
        int w = 1;
        int in = Math.floorMod(key.hashCode(), entryArraySize());
        if(containsKey(key)) {
            LinkedList<Entry> p = en[in];
            for(int i = 0; i < p.size(); i++) {
                if(p.get(i).keyEquals(new Entry(key, null))) {

                    // find the value here when necessary
                    p.get(i).value = value;
                    break;
                }
            }
        } else {
            // check whether the max load is exceed or not
            if (loadFactor() > maxLoad) {
                resize();
            }
            if (en[in] == null) {
                en[in] = new LinkedList<Entry>();
            }
            en[in].addLast(new Entry(key, value));

            int a = size();
            this.size = a + w;
        }
    }

    private String findKey(String key){
        LinkedList<Entry> p = this.en[Math.floorMod(key.hashCode(), entryArraySize())];
        return p.stream().filter(x->x.key.equals(key)).collect(Collectors.toList()).get(0).value;
    }

    public int hashCodeWithError(String entry) {
        return 0;
    }
}