import java.util.*;

class Edge { // Class to initialize edge properties
    int source, destination, weight;

    public Edge(int source, int destination, int weight) { // Assign values source, destination and weight
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class BellmanFord {

    public static void main(String[] args) {

        int V = 5; // Number of vertices
        BellmanFord graph = new BellmanFord(V);

        // Add edges to graph

        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 3, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 1, 4);
        graph.addEdge(4, 0, 1);
        graph.addEdge(4, 2, 2);


        /*Negative cycle

        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 7);
        graph.addEdge(1, 4, -5);
        graph.addEdge(1, 3, 6);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3); */


        int source = 0;
        graph.bellmanFord(source);
    }

    private int V; // Number of vertices
    private int[] distance; // Array of distances
    private List<Edge> edges;

    public BellmanFord(int V) {
        this.V = V;
        distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        // Adding edge to the list of edges in the graph
        edges.add(new Edge(source, destination, weight));
    }

    public void bellmanFord(int source) {
        distance[source] = 0;

        // Iterating over vertices
        for (int i = 0; i < V - 1; i++) {
            // Iterating over edges
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int w = edge.weight;

                // Relaxation  Updating distance if a shorter path is found
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int w = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                System.out.println("Negative weight cycle present in graph.");
                return;
            }
        }

        // Display the shortest distances
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": Distance = " + distance[i]);
        }
    }

}
