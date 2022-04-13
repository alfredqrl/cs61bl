import java.util.*;

public class CodingChallenges {

    /**
     * Return the missing number from an array of length N containing all the
     * values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        // TODO
        Arrays.sort(values);
        //List<String> list = new ArrayList<>(values);
        //List<Integer> list = Arrays.asList(values);
        List<Integer> list1 = new ArrayList<Integer>();
        for(int text:values) {
            list1.add(text);
        }

        for (int i = 0; i < values.length + 1; i++){
            if (!list1.contains(i)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if and only if two integers in the array sum up to n.
     * Assume all values in the array are unique.
     */
    public static boolean sumTo(int[] values, int n) {
        // TODO
        int sum = 0;
        for (int i = 0; i < values.length - 1; i++){
            for (int j = i + 1; j < values.length; j++){
                if (values[i] + values[j] == n){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        // TODO
        Map<String, Integer> map = new HashMap<String,Integer>();
        int count = 0;
        String temp;

        if (s1.length() != s2.length()){
            return false;
        }

        for (int i = 0; i < s1.length(); i++){
            if (!map.containsKey(s1.substring(i, i + 1))){
                temp = s1.substring(i, i + 1);
                for (int j = 0; j < s1.length(); j++){
                    if (s1.substring(j, j + 1).equals(temp)){
                        count++;
                    }
                }
                map.put(s1.substring(i, i + 1),count);
                count = 0;
            }
        }

        //System.out.println(map);

        boolean [] bool = new boolean[map.size()];
        int idx = 0;
        count = 0;
        String temp2;
        for (int i = 0; i < s2.length(); i++){
            if (map.containsKey(s2.substring(i, i + 1))){
                temp2 = s2.substring(i, i + 1);
                for (int j = 0; j < s2.length(); j++){
                    if (temp2.equals(s2.substring(j, j + 1))){
                        count++;
                    }
                }
                if (count == map.get(temp2)){
                    bool[idx] = true;
                    idx++;
                }
                count = 0;
            }
        }

        boolean [] compare = new boolean[map.size()];
        Arrays.fill(compare,true);

        if (Arrays.equals(compare, bool)){
            return true;
        }

        return false;
    }

    /*
    public  static  void main(String [] args){
        //String s = "tole";
        System.out.println(isPermutation("tole", "elot"));

    }
     */
}

