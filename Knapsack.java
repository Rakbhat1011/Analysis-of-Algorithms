import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    public static int knapsack(int n, int W, int[] weights, int[] values) {

        // Create a 2D array to store the maximum values for subproblems
        int[][] M = new int[n + 1][W + 1];
        // List to track selected items
        List<Integer> selectedItems = new ArrayList<>();

        // Initialize the first row with 0 for all possible knapsack capacities
        for (int w = 0; w <= W; w++) {
            M[0][w] = 0;
        }

        // Loop through each item and each possible knapsack capacity to fill the 2D array
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] > w) {
                    // If the current item's weight is greater than the current capacity, skip it
                    M[i][w] = M[i - 1][w];
                } else {
                    // Otherwise, consider whether to include the item or not
                    M[i][w] = Math.max(M[i - 1][w], values[i - 1] + M[i - 1][w - weights[i - 1]]);
                }
            }
        }

        // Backtrack to find the selected items in the knapsack (Optional)
        int w = W;
        for (int i = n; i > 0 && w > 0; i--) {
            if (M[i][w] != M[i - 1][w]) {
                selectedItems.add(i - 1);
                w -= weights[i - 1];
            }
        }

        // Print the selected items
        System.out.print("Selected items: ");
        for (int i = selectedItems.size() - 1; i >= 0; i--) {
            int itemIndex = selectedItems.get(i);
            System.out.print((char) ('A' + itemIndex) + " ");
        }
        System.out.println();

        // Return the maximum value in the knapsack
        return M[n][W];
    }

    public static void main(String[] args) {
        int n = 5;
        int W = 30;
        int[] weights = {10, 8, 7, 7, 6};
        int[] values = {2, 10, 14, 8, 10};

        int result = knapsack(n, W, weights, values);
        System.out.println("Maximum value in the knapsack: " + result);
    }
}
