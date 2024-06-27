import java.util.LinkedList;
import java.util.Queue;

public class bfs {
    private int V; // Number of vertices
    private int[][] adjMatrix; // Adjacency matrix

    public bfs(int v) { //// Constructor to initialize the graph with vertices
        V = v;
        V = v;
        adjMatrix = new int[V][V];
    }

    public static void main(String[] args) {
        int[][] inputMatrix = {
                {0, 1, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0}
        };

        int V = inputMatrix.length;
        bfs graph = new bfs(V); // Create a graph


        for (int i = 0; i < V; i++) {
            for (int j = i; j < V; j++) {
                if (inputMatrix[i][j] == 1) {
                    graph.addEdge(i, j);
                }
            }
        }

        int[][] bfsMatrix = graph.bfs(0); // Perform BFS starting from source 0
        displayOutputMatrix(bfsMatrix); // Display the traversal result
    }

    public void addEdge(int i, int j) { // Function to add Edge into the Adjacency Matrix
        adjMatrix[i][j] = 1;
        adjMatrix[j][i] = 1;
    }

    public int[][] bfs(int startVertex) { // Output adjacency matrix
        int[][] bfsMatrix = new int[V][V];
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;  // Initialize Visited array with start vertex as true
        queue.add(startVertex); // Add the source vertex

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll(); // Dequeue vertex from the queue for exploration
            // Iterate through all vertices to check for adjacent vertices
            // If there is an edge from the currentVertex to vertex 'i'
            // and if i has not been visited yet
            // Set the edge between currentVertex and 'i' as 1
            // Set the reverse edge also as 1 (Undirected Graph)
            for (int i = 0; i < V; i++) {
                if (adjMatrix[currentVertex][i] == 1 && !visited[i]) {
                    bfsMatrix[currentVertex][i] = 1;
                    bfsMatrix[i][currentVertex] = 1;

                    // Mark i as visited and add it to queue for further exploration
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        return bfsMatrix;
    }

    public static void displayOutputMatrix(int[][] matrix) { // Display the output matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

