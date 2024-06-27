import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Point {
    int x, y; // Change data type to int

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPairOfPoints {
    private static List<Point> closestPair = new ArrayList<>();

    // Function to find the closest pair of points using the divide-and-conquer algorithm
    public static double findClosestPair(List<Point> points) {
        int n = points.size();
        if (n <= 1) {
            return Double.POSITIVE_INFINITY; // No pair to compare
        }

        // Sort points by x-coordinate
        points.sort((p1, p2) -> Integer.compare(p1.x, p2.x)); // Use Integer.compare for integer sorting

        // Divide the points into two halves
        int mid = n / 2;
        List<Point> leftHalf = points.subList(0, mid);
        List<Point> rightHalf = points.subList(mid, n);

        // Recursively find the closest pair in each half
        double leftMin = findClosestPair(leftHalf);
        double rightMin = findClosestPair(rightHalf);

        // Find the minimum distance between pairs in different halves
        double minDistance = Math.min(leftMin, rightMin);

        // Merge the results and check for closer pairs across the division
        int midX = points.get(mid).x; // Use integer for midX

        // Create two pre-sorted lists (by y and x coordinates) for left and right halves
        List<Point> leftYSorted = new ArrayList<>();
        List<Point> rightYSorted = new ArrayList<>();

        for (Point point : points) {
            if (Math.abs(point.x - midX) < minDistance) {
                if (point.x <= midX) {
                    leftYSorted.add(point);
                } else {
                    rightYSorted.add(point);
                }
            }
        }

        // Combine the y-sorted lists and sort them
        List<Point> ySorted = new ArrayList<>();
        ySorted.addAll(leftYSorted);
        ySorted.addAll(rightYSorted);
        ySorted.sort((p1, p2) -> Integer.compare(p1.y, p2.y)); // Use Integer.compare for integer sorting

        // Check for closer pairs within the strip
        for (int i = 0; i < ySorted.size(); i++) {
            for (int j = i + 1; j < Math.min(i + 12, ySorted.size()); j++) {
                double dist = distance(ySorted.get(i), ySorted.get(j));
                if (dist < minDistance) {
                    minDistance = dist;
                    closestPair.clear();
                    closestPair.add(ySorted.get(i));
                    closestPair.add(ySorted.get(j));
                }
            }
        }

        return minDistance;
    }

    // Function to calculate the Euclidean distance between two points
    public static double distance(Point p1, Point p2) {
        int dx = p1.x - p2.x; // Use integer for delta-x
        int dy = p1.y - p2.y; // Use integer for delta-y
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        List<Point> points = new ArrayList<>();

        // Generate random points
        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(41); // Generate random integers between 0 and 40
            int y = rand.nextInt(41);
            points.add(new Point(x, y));
        }

        // Find the closest pair of points
        double closestDistance = findClosestPair(points);

        System.out.println("Closest pair distance: " + closestDistance);

        // Display the coordinates of the closest pair of points
        System.out.println("Closest Pair Points:");
        for (Point point : closestPair) {
            System.out.println("x: " + point.x + ", y: " + point.y);
        }
    }
}
