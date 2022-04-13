
import java.util.*;

public class Graph implements Iterable<Integer> {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;

    /* Initializes a graph with NUMVERTICES vertices and no Edges. */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    /* Adds a directed Edge (V1, V2) to the graph. That is, adds an edge
       in ONE directions, from v1 to v2. */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    /* Adds an undirected Edge (V1, V2) to the graph. That is, adds an edge
       in BOTH directions, from v1 to v2 and from v2 to v1. */
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, 0);
    }

    /* Adds a directed Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addEdge(int v1, int v2, int weight) {
        Edge edge = new Edge(v1, v2, weight);
        adjLists[v1].add(edge);
        // TODO: YOUR CODE HERE
    }

    /* Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
        // TODO: YOUR CODE HERE
    }

    /* Returns true if there exists an Edge from vertex FROM to vertex TO.
       Returns false otherwise. */
    public boolean isAdjacent(int from, int to) {
        Edge edge = new Edge(from, to, 0);
        for(Edge e : adjLists[from]){
            if (e.to == to){
                return true;
            }
        }
        // TODO: YOUR CODE HERE
        return false;
    }

    /* Returns a list of all the vertices u such that the Edge (V, u)
       exists in the graph. */
    public List<Integer> neighbors(int v) {
        List<Integer> res = new LinkedList<>();
        for (Edge e : adjLists[v]){
            res.add(e.to);
        }
        // TODO: YOUR CODE HERE
        return res;
    }
    /* Returns the number of incoming Edges for vertex V. */
    public int inDegree(int v) {
        int count = 0;
        for (int k = 0; k < vertexCount; k++) {
            for(Edge e : adjLists[k]){
                if (e.to == v){
                    count++;
                }
            }
        }
        // TODO: YOUR CODE HERE
        return count;
    }

    /* Returns an Iterator that outputs the vertices of the graph in topological
       sorted order. */
    public Iterator<Integer> iterator() {
        return new TopologicalIterator();
    }

    /**
     *  A class that iterates through the vertices of this graph,
     *  starting with a given vertex. Does not necessarily iterate
     *  through all vertices in the graph: if the iteration starts
     *  at a vertex v, and there is no path from v to a vertex w,
     *  then the iteration will not include w.
     */
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;

        public DFSIterator(Integer start) {
            fringe = new Stack<>();
            visited = new HashSet<>();
            fringe.push(start);
        }

        public boolean hasNext() {
            if (!fringe.isEmpty()) {
                int i = fringe.pop();
                while (visited.contains(i)) {
                    if (fringe.isEmpty()) {
                        return false;
                    }
                    i = fringe.pop();
                }
                fringe.push(i);
                return true;
            }
            return false;
        }

        public Integer next() {
            int curr = fringe.pop();
            ArrayList<Integer> lst = new ArrayList<>();
            for (int i : neighbors(curr)) {
                lst.add(i);
            }
            lst.sort((Integer i1, Integer i2) -> -(i1 - i2));
            for (Integer e : lst) {
                fringe.push(e);
            }
            visited.add(curr);
            return curr;
        }

        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    /* Returns the collected result of performing a depth-first search on this
       graph's vertices starting from V. */
    public List<Integer> dfs(int v) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(v);

        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    /* Returns true iff there exists a path from START to STOP. Assumes both
       START and STOP are in this graph. If START == STOP, returns true. */
    public boolean pathExists(int start, int stop) {
        if (start == stop){
            return true;
        }
        List<Integer> res = dfs(start);
        if(res.contains((Integer) stop)){
            return true;
        }
        // TODO: YOUR CODE HERE
        return false;
    }


    /* Returns the path from START to STOP. If no path exists, returns an empty
       List. If START == STOP, returns a List with START. */
    public List<Integer> path(int start, int stop) {
        List<Integer> result = new ArrayList<>();
        result.add(stop);
        List<Integer> all = dfs(start);

        if(start == stop){
            List<Integer> e = new ArrayList<>();
            e.add(start);
            return e;
        }
        if(all.contains(stop)){
            int index_stop = all.indexOf(stop);
            for (int i = 0; i < index_stop; i++){
                if (isAdjacent(all.get(i), stop)){
                    result.add(all.get(i));
                    break;
                }
            }
            while (!result.contains(start)){
                index_stop = all.indexOf(result.get(result.size()-1));
                for (int i = 0; i < index_stop; i++){
                    if (isAdjacent(all.get(i), all.get(index_stop))){
                        result.add(all.get(i));
                        break;
                    }
                }
            }
            List<Integer> finall = new ArrayList<>();
            for(int i= result.size()-1; i >= 0; i--){
                finall.add(result.get(i));
            }
            return finall;
        }
        else {
            ArrayList<Integer> k = new ArrayList<>();
            return k;
        }

    }


    public List<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }


    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private Integer[] inDegree;
        private HashSet<Integer> visited;

        // TODO: Instance variables here!

        TopologicalIterator() {
            fringe = new Stack<>();
            // TODO: YOUR CODE HERE
            visited = new HashSet<>();
            inDegree = new Integer[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                inDegree[i] = inDegree(i);
                if (inDegree(i) == 0) {
                    fringe.push(i);
                }
            }
        }

        public boolean hasNext() {
            // TODO: YOUR CODE HERE
            return !fringe.isEmpty();
        }

        public Integer next() {
            // TODO: YOUR CODE HERE
            Integer ped = fringe.pop();
            for (Edge e : adjLists[ped]) {
                inDegree[e.to]--;
            }
            visited.add(ped);
            for (int i = 0; i < vertexCount; i++) {
                if (!visited.contains(i) && !fringe.contains(i) && inDegree[i] == 0) {
                    fringe.push(i);
                }
            }
            return ped;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public ArrayList<Integer> shortestPath(int start, int stop) {
        PriorityQueue<Node> fringe = new PriorityQueue<>(1, (o1, o2) -> o1.weight - o2.weight);
        ArrayList<Integer> seen = new ArrayList<>();
        Node s = new Node(start, 0, new ArrayList<Integer>());
        fringe.add(s);
        while (!fringe.isEmpty()) {
            Node curr = fringe.poll();
            int v = curr.vertex;
            seen.add(v);
            if (v == stop) {
                return curr.path;
            }
            List<Integer> neighbors = neighbors(curr.vertex);
            for (int u: neighbors) {
                if (!seen.contains(u)) {
                    Edge e = getEdge(curr.vertex, u);
                    Node in = new Node(u, curr.weight + e.weight, curr.path);
                    fringe.add(in);
                }
            }
        }
        return null;
    }

    public Edge getEdge(int u, int v) {
        LinkedList pointer = adjLists[u];
        for (int j = 0; j < pointer.size(); j++) {
            Edge e = (Edge) pointer.get(j);
            if (e.from == u && e.to == v) {
                return e;
            }
        }
        return null;
    }

    private class Edge {

        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }

    }

    private class Node {
        int vertex;
        int weight;
        ArrayList path;

        Node(int vertex, int weight, ArrayList path) {
            this.vertex = vertex;
            this.weight = weight;
            this.path = (ArrayList) path.clone();
            this.path.add(vertex);
        }

        public String toString() {
            return "vertex:" + vertex + " weight:" + weight + " path:" + path.toString();
        }
    }

    private void generateG1() {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(4, 3);
    }

    private void generateG2() {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(4, 3);
    }

    private void generateG3() {
        addUndirectedEdge(0, 2);
        addUndirectedEdge(0, 3);
        addUndirectedEdge(1, 4);
        addUndirectedEdge(1, 5);
        addUndirectedEdge(2, 3);
        addUndirectedEdge(2, 6);
        addUndirectedEdge(4, 5);
    }

    private void generateG4() {
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(4, 2);
    }

    private void printDFS(int start) {
        System.out.println("DFS traversal starting at " + start);
        List<Integer> result = dfs(start);
        Iterator<Integer> iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
    }

    private void printPath(int start, int end) {
        System.out.println("Path from " + start + " to " + end);
        List<Integer> result = path(start, end);
        if (result.size() == 0) {
            System.out.println("No path from " + start + " to " + end);
            return;
        }
        Iterator<Integer> iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
    }

    private void printTopologicalSort() {
        System.out.println("Topological sort");
        List<Integer> result = topologicalSort();
        Iterator<Integer> iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(5);
        g1.generateG1();
        g1.printDFS(0);
        g1.printDFS(1);
        g1.printDFS(2);
        g1.printDFS(3);
        g1.printDFS(4);

        g1.printPath(0, 3);
        g1.printPath(0, 4);
        g1.printPath(1, 3);
        g1.printPath(1, 4);
        g1.printPath(4, 0);

        Graph g2 = new Graph(5);
        g2.generateG2();
        g2.printTopologicalSort();
    }
}