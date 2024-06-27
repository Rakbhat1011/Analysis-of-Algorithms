import java.util.ArrayList;

public class Heap {
    private static ArrayList<Integer> heap = new ArrayList<>();
    private static int size = 0;

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        // Adding elements from array to Heap
        for (int element : arr) {

                size++;
                heap.add(element);
                // Check for Heap Property
                // by moving the element up if necessary
                System.out.println("Contents of Heap: " + heap);
                heapifyUp(size-1);
            }

        // Removing all elements
        for (int element : arr) {

                // Print element being deleted
                System.out.println("Deleted element : " + heap.get(0));
                // Move the element to root
                heap.set(0, heap.get(size - 1));
                // Decrease size
                size--;
                // Maintain Heap Property by moving element down if necessary
                heapifyDown(0, size - 1);
                // Remove last Element from Heap
                heap.remove(size);
                System.out.println("Contents of Heap: " + heap);
            }

    }

    private static void heapifyDown(int pos, int size) {
        int leftChild = 2 * pos + 1; // Calculate left child
        int rightChild = 2 * pos + 2; // Calculate right child
        int test = pos; // Initialize the index with current position

        if (leftChild < size && heap.get(leftChild) < heap.get(test)) {
            test = leftChild; // If left child is smaller, update test index
        }

        if (rightChild < size && heap.get(rightChild) < heap.get(test)) {
            test = rightChild; // If right child is smaller, update test index
        }

        if (test != pos) {
            // Swap the elements at pos
            // Recursively check the element at test index
            swap(pos, test);
            heapifyDown(test, size);
        }
    }

    private static void heapifyUp(int pos) {
        // Function to move the element up the Heap
        // Calculate the parent index
        int parent = (pos - 1) / 2;

        if (pos != 0 && heap.get(parent) > heap.get(pos)) {
            swap(pos, parent);
            heapifyUp(parent);
        }
    }
    // Function to swap elements in Heap
    private static void swap(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    } }