public class ArrayOperations {
    /**
     * Delete the value at the given position in the argument array, shifting
     * all the subsequent elements down, and storing a 0 as the last element of
     * the array.
     */
    public static void delete(int[] values, int pos) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        else{
            for (int i = pos + 1; i < values.length; i++){
                values[i - 1] = values[i];
            }
            values[values.length - 1] = 0;
        }
        // TODO: YOUR CODE HERE
    }

    /**
     * Insert newInt at the given position in the argument array, shifting all
     * the subsequent elements up to make room for it. The last element in the
     * argument array is lost.
     */
    public static void insert(int[] values, int pos, int newInt) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        for (int i = values.length - 1; i > pos; i--){
                values[i] = values[i - 1];
        }
        values[pos] = newInt;
        // TODO: YOUR CODE HERE
    }

    /** 
     * Returns a new array consisting of the elements of A followed by the
     *  the elements of B. 
     */
    public static int[] catenate(int[] A, int[] B) {
        int [] ret = new int [A.length + B.length];
        for (int i = 0; i < A.length; i++){
            ret[i] = A[i];
        }
        
        for (int i = 0; i < B.length; i++){
            ret[i + A.length] = B[i];
        }
        // TODO: YOUR CODE HERE
        return ret;
    }

    /** 
     * Returns the array of arrays formed by breaking up A into
     *  maximal ascending lists, without reordering.
     */
    public static int[][] naturalRuns(int[] A) {
        int[] position = new int [A.length];
        int[] len = new int [A.length];

        int row = 0;
        int col = 1;
        int pos = 0;

        for (int i = 0; i < A.length - 1; i++){
            if (A[i + 1] > A[i]){
                col += 1;
            }
            else{
                position[row] = pos;
                len[row] = col;
                pos = i + 1;
                row = row + 1;
                col = 1;
            }
        }
        position[row] = pos;
        len[row] = col;

        int[][] res = new int [row + 1][];
        
        for (int i = 0; i <= row; i++){
            res[i] = subarray(A, position[i], len[i]);
        }
        return res;
    }

    /*
    * Returns the subarray of A consisting of the LEN items starting
    * at index K.
    */
    public static int[] subarray(int[] A, int k, int len) {
        int[] result = new int[len];
        System.arraycopy(A, k, result, 0, len);
        return result;
    }
}