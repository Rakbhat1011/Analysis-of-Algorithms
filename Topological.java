import java.util.*;


public class Topological {
    public static void main(String[] args) {
        int vertices = 7;
        Graphs graph = new Graphs(vertices); // Graph creation with 7 vertices

        //Add edges to graph as given in the input data
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(1, 7);
        graph.addEdge(2, 3);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);


        // Function call to Topological Sor
        List<Integer> topologicalOrder = graph.topologicalSort();

        // Display the topological sorting order
        System.out.println("Topological Ordering of Vertices:");
        for (int vertex : topologicalOrder) {
            System.out.print(vertex + " ");
        }
    }
}

class Graphs {
    private int V; // Number of vertices
    private Map<Integer, List<Integer>> adjList; // Adjacency list

    public Graphs(int vertices) {
        this.V = vertices;
        adjList = new HashMap<>();  // Create new Hashmap for the adjacency list
        for (int i = 1; i <= vertices; i++) {
            adjList.put(i, new LinkedList<>());
        }
    }

    public void addEdge(int source, int destination) {   // Add edges of each vertex
        adjList.get(source).add(destination);
    }

    private void visitNeighbor(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;
        for (Integer neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                //Visit neighbours
                visitNeighbor(neighbor, visited, stack);
            }
        }
        // Push the current vertex to stack
        // when all its neighbors are visited
        stack.push(v);
    }

    public List<Integer> topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V + 1];

        // Perform topological sorting for each unvisited vertex
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {

                visitNeighbor(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {

            // Pop vertices from the stack
            // This is will result in topological ordering
            result.add(stack.pop());
        }

        return result;
    }
}
