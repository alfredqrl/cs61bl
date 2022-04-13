public class UnionFind {

    /* TODO: Add instance variables here. */
    int count;
    private int[] id;
    private int[] sz;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = i;
        }
        // TODO: YOUR CODE HERE


    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return id.length;
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return id[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2)){
            return true;
        }
        // TODO: YOUR CODE HERE
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if(v < 0 || v >= id.length) {
            throw new IllegalArgumentException("v is out of bound.");
        }
        
        if(v != id[v]) {
            id[v] = find(id[v]);
        }
        return id[v];
        // TODO: YOUR CODE HERE
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing a item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        int v1root = find(v1);
        int v2root = find(v2);
        
        if (v1root == v2root){
            return;
        }
        
        if (sz[v1root] < sz[v2root]){
            id[v1root] = v2root;
            sz[v2root] += sz[v1root];
        }
        else {
            id[v2root] = v1root;
            sz[v1root] += sz[v2root];
        }
        count--;
        
        
        // TODO: YOUR CODE HERE
    }
    
}
