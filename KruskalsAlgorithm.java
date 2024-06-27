import java.util.*;

class Edgess implements Comparable<Edgess> {
    int u, v;
    int weight;
    //Constructor for initializing edges and weights
    public Edgess(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edgess other) {
        // Compare edges based on their weights
        // Includes lexicographical order tie breaking mechanism
        if (weight < other.weight) return -1;
        else if (weight > other.weight) return 1;
        else if (u < other.u || (u == other.u && v < other.v)) return -1;
        else return 1;
    }
}

class UnionFind {
    int[] parent;
    int[] position;

    //Constructor for a Union-Find data structure with 'n' elements
    public UnionFind(int n) {

        parent = new int[n];
        position = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Initially its own parent
            position[i] = 0;
        }
    }

    // Find parent of to which u belongs
    public int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    // Union two sets
    public boolean makeUnion(int u, int v) {
        int parentU = find(u);
        int parentV = find(v);

        if (parentU == parentV) return false; // Already in same set

        // Reduced depth - logn
        if (position[parentU] < position[parentV]) { parent[parentU] = parentV;}
        else if (position[parentU] > position[parentV]) { parent[parentV] = parentU; }
        else { parent[parentV] = parentU; position[parentU]++; }
        return true;
    }
}

public class KruskalsAlgorithm {

    public static void displayMST(List<Edgess> mst){

        // Display MST
        System.out.println("Minimum Spanning Tree - Edges with weights:");
        int weight=0;
        for (Edgess edge : mst) {
            System.out.println(edge.u + " - " + edge.v + " : " + edge.weight);
            weight+=edge.weight;
        }
        System.out.println("Total weight = "+weight);

    }
    public static List<Edgess> kruskalMST(List<Edgess> edges, int n) {
        Collections.sort(edges); // Sort edges by weight

        List<Edgess> mst = new ArrayList<>(); // Initialize MST
        UnionFind uf = new UnionFind(n); // Initialize Union - Find

        for (Edgess edge : edges) {
            if (uf.makeUnion(edge.u, edge.v)) {
                mst.add(edge);  // Add to MST if no cycle
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        int n = 8; // Number of vertices

        List<Edgess> edges = new ArrayList<>(); // Add edges and weights
        edges.add(new Edgess(0, 1, 10));  // A - B : 10
        edges.add(new Edgess(0, 3, 10));  // A - D : 10
        edges.add(new Edgess(0, 4, 5));   // A - E : 5
        edges.add(new Edgess(1, 2, 9));   // B - C : 9
        edges.add(new Edgess(2, 3, 10));  // C - D : 10
        edges.add(new Edgess(2, 6, 9));   // C - G : 9
        edges.add(new Edgess(3, 4, 10));  // D - E : 10
        edges.add(new Edgess(3, 6, 10));  // D - G : 10
        edges.add(new Edgess(4, 7, 10));  // E - H : 10
        edges.add(new Edgess(5, 7, 10));  // F - H : 10
        edges.add(new Edgess(6, 7, 9));   // G - H : 9

        List<Edgess> mst = kruskalMST(edges, n); //Run Kruskal's Algorithm

        displayMST(mst); // Function to display MST


    }
}
