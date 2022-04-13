import java.util.*;

public class MyTrieSet implements TrieSet61BL{

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        Node curr = root;
        /*
        do {
            if(curr == null) {
                return false;
            }
            curr = curr.map.get(key.charAt(0));
            key = key.substring(1);
        } while (!key.equals(""));
         */
        Node temp = curr.map.get(key.charAt(0));
        curr = temp;
        key = key.substring(1);
        while (!key.equals("")){
            if (curr == null){
                return false;
            }else {
                curr = curr.map.get(key.charAt(0));
                key = key.substring(1);
            }
        }
        if (curr == null){
            // bad idea when reach this
            throw new IllegalArgumentException();
        }else {
            return curr.isKey;
        }
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        String copyPrefix = prefix;
        Node curr = root;
        LinkedList<String> answerFind = new LinkedList<String>();
        Node temp = curr.map.get(copyPrefix.charAt(0));
        curr = temp;
        if (curr == null){
            return answerFind;
        }
        copyPrefix = copyPrefix.substring(1);
        while (!copyPrefix.equals("")){
            if (curr == null){
                // should return an answer
                return answerFind;
            }
            curr = curr.map.get(copyPrefix.charAt(0));
            copyPrefix = copyPrefix.substring(1);
        }
        // use the helper function



        /*
        if (curr == null){
            return null;
        }else{
            if (curr.isKey){
                answerFind.add(prefix);
            }
        }

         */
        collectStringHelper(curr, answerFind, prefix);
        return answerFind;
    }

    private void CollectionStringHelper(Node curr, String prefix){
        if (curr == null){
            return;
        }else{
            int prefix2 = prefix.length();
            prefix2 = prefix2 + 1;
            Node a =curr.map.get(0);
            if (a.isKey){
                CollectionStringHelper(curr, prefix);
            }
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
        //return null;
    }

    private static class Node{

        boolean isKey;
        char it;
        HashMap<Character, Node> map;
        public Node(){
            isKey = false;
            // give a new node for implementation
            map = new HashMap<>();
        }

        public Node(char ca, boolean isAKey){
            this.isKey = isAKey;
            this.it = ca;
            // also new node for implementation
            map = new HashMap<>();
        }
    }

    Node root;
    public MyTrieSet(){
        root = new Node();
    }

    private void collectStringHelper(Node curr, LinkedList<String> answerFind, String prefix){
        if (!(curr == null)){
            if (curr.isKey){
                answerFind.add(prefix);
            }
            for (char item : curr.map.keySet()){
                prefix = prefix + item;
                int i = 0;
                collectStringHelper(curr.map.get(item), answerFind, prefix);
                int lengthOfPrefix = prefix.length() + 1;
                if (lengthOfPrefix < 0){
                    throw new IllegalArgumentException();
                }
                prefix = prefix.substring(0, lengthOfPrefix - 2);
            }
        }else {
            return;
        }
    }
}
