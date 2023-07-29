/** CSDS 233 Assignment 6
 * @author Gia Hur **/

class Graph {

    private String[] nodes;
    private boolean[][] edges;
    private int numNodes;
    private int size;

    // constructor
    public Graph(int size) {
        nodes = new String[size];
        edges = new boolean[size][size];
        this.size = size;
        numNodes = 0;
    }

// Constructing an undirected, unweighted graph

    // adds a node to the graph and checks for duplicates
    public boolean addNode(String name) { 
        if(findNode(name) != -1) // checks for duplicates
            return false;
        if(numNodes <= nodes.length) // increases size if necessary
            increaseSize();
        int i;
        for(i = 0; i < size && nodes[i] != null; i++)
            ;
        nodes[i] = name;
        numNodes++;
        return true;
    }

    // adds a list of nodes to the graph and checks for duplicates
    public boolean addNodes(String[] names) { 
        for(int i = 0; i < names.length; i++) // calls addNode method for each node in list
            addNode(names[i]);
        return true;
    }

    // adds an edge from node from to node to
    public boolean addEdge(String from, String to) { 
        if(findNode(from) == -1 || findNode(to) == -1) // checks that nodes exist
            return false;
        edges[findNode(from)][findNode(to)] = true; 
        edges[findNode(to)][findNode(from)] = true;
        return true;
    }

    // adds an undirected edge from node from to each node in tolist
    public boolean addEdges(String from, String[] toList) { 
        for(int i = 0; i < toList.length; i++) // calls addEdge method for each edge in list
            addEdge(from, toList[i]);
        return true;
    }

    // removes a node from the graph along with all connected edges
    public boolean removeNode(String name) { 
        int toRemove = findNode(name);
        if(toRemove == -1) // checks that node exists
            return false;
        nodes[toRemove] = null; 
        for(int i = 0; i < size; i++) { // removes all edges connected to node
            for(int j = 0; j < size; j++) {
                if(i == toRemove || j == toRemove) {
                    edges[i][j] = false;
                    edges[j][i] = false;
                }
            }
        }
        numNodes--;
        return true;
    }

    // removes each node in nodelist and their edges from the graph
    public boolean removeNodes(String[] nodeList) { 
        for(int i = 0; i < nodeList.length; i++)
            removeNode(nodeList[i]);
        return true;
    }

    // prints the graph in an adjacency list format alphabetically
    public void printGraph() { 
        String[] temp = alphabetize(nodes);
        for(int i = 0; i < size; i++) {
            if(temp[i] != null)
                System.out.print("\n" + temp[i] + ": "); // prints nodes 
            for(int j = 0; j < size; j++) {
                if(edges[i][j] == true && nodes[j] != null)
                    System.out.print(nodes[j]);
            } 
        }
        System.out.println("\n");
    }
    
    // increases size of both nodes and edges
    private void increaseSize() {
        int previousSize = size;
        String[] previousNodes = nodes;
        boolean[][] previousEdges = edges;
        size = 10 + previousSize; // increases size by 10
        nodes = new String[size];
        edges = new boolean[size][size];
        for(int i=0; i < previousSize; i++) { // copies over values to larger array
            nodes[i] = previousNodes[i];
            for(int j = 0; j < previousSize; j++) {
                edges[i][j] = previousEdges[i][j];
                edges[j][i] = previousEdges[j][i];
            }
        }
    }

    // returns index if node is in graph, returns -1 otherwise
    private int findNode(String name) {
        int i;
        for(i = 0; i < nodes.length; i++) { // traverses graph
            if(nodes[i] == null)
                ;
            else if(nodes[i].equals(name))
                return i;
        }
        return -1;
    }

    // alphabetize given array
    private String[] alphabetize(String[] list) {
        String[] temp = list;
        for(int i = 0; i < size; i++) {  
            for (int j = i + 1; j < size; j++) {  
                if(temp[i] != null && temp[j] != null && temp[i].compareTo(temp[j]) > 0) {  
                    String current = temp[i];  
                    temp[i] = temp[j];  
                    temp[j] = current;  
                }  
            }  
        }  
        return temp;
    }

// Finding paths

    // uses depth-first search to return the path between nodes from and to
    public String[] DFS(String from, String to, String neighborOrder) { 
        if(findNode(from) == -1 || findNode(to) == -1) // checks that nodes exist
            return new String[0];
        int[] parents = new int[size];
        boolean[] visited = new boolean[size];
        DFSHelper(findNode(from), findNode(to), -1, parents, visited);
        String[] path = new String[size];
        for(int i = 0; i < size; i++) {
            if(i != -1 && parents[i] != -1)
                path[i] = nodes[parents[i]];
        }
        return path;
    }

    // uses breadth-first search to return the path between nodes from and to
    public String[] BFS(String from, String to, String neighborOrder) { 
        if(findNode(from) == -1 || findNode(to) == -1) // checks that nodes exist
            return new String[0];
        int[] parents = new int[size];
        boolean[] visited = new boolean[size];

        visited[findNode(from)] = true;
        parents[findNode(from)] = -1;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(visited[i] == true && edges[i][j]) {
                    visited[j] = true;
                    parents[j] = i;
                }
            }
        }

        String[] path = new String[size];
        int j = 0;
        if(visited[findNode(to)] == true) {
            for(int i = findNode(to); i != -1; i++) {
                    path[j] = nodes[i];
                    i = parents[i];
                    j++;
            }
        }
        return path;
    }

    // uses Dijkstra’s algorithm to return shortest path between nodes from and to
    public String[] shortestPath(String from, String to) { 
        if(findNode(from) == -1 || findNode(to) == -1) // checks that nodes exist
            return new String[0];
        int[] parents = new int[size];
        boolean[] visited = new boolean[size];
        visited[findNode(from)] = true;
        parents[findNode(from)] = -1;

        return new String[0];
    }
    
    // returns the second shortest path between nodes from and to
    //public String[] secondShortestPath(String from, String to)

    private int[] DFSHelper(int index, int toIndex, int parentIndex, int[] parents, boolean[] visited) { 
        if(index == toIndex)
            return parents;
        visited[index] = true;
        parents[index] = parentIndex;
        for(int i = 0; i < size; i++) {
            if(visited[i] != true && edges[index][i] == true)
                DFSHelper(i, index, toIndex, parents, visited);
        }
        return new int[0];
    }

    //Print given array as String
    public static void printArray(String[] arr) {
        String s = "[";
        for(int i = 0; i < arr.length-1; i++){
            s = s + arr[i] + ", ";
        }
        s = s + arr[arr.length-1] + "]";
        System.out.println(s);
    }

    public static void main(String[] args) {
        Graph graph1 = new Graph(5);
        System.out.print("Current Graph: ");
        
        System.out.print("After addNode: ");
        graph1.addNode("A");
        graph1.printGraph();

        System.out.print("After addNodes: ");
        graph1.addNodes(new String[] {"B","C","D"});
        graph1.printGraph();

        System.out.print("After addEdge: ");
        graph1.addEdge("A", "B");
        graph1.printGraph();

        System.out.println("After addEdges: ");
        graph1.addEdges("A", new String[] {"C","B","D"});
        graph1.printGraph();

        System.out.println("After removeNode: ");
        graph1.removeNode("A");
        graph1.printGraph();

        System.out.println("After removeNodes: ");
        graph1.removeNodes(new String[] {"C","D"});
        graph1.printGraph();

        System.out.println("Graph for finding path algorithms: ");
        graph1.addNodes(new String[] {"A","C","D"});
        graph1.addEdges("A", new String[] {"C","B","D"});
        graph1.printGraph();

        printArray(graph1.DFS("A","B","j"));
        printArray(graph1.BFS("A","B","j"));
    }
}