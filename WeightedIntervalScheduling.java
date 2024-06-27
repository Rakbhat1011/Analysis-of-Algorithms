import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Job {
    char id;
    int start, finish, weight;

    public Job(char id, int start, int finish, int weight) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.weight = weight;
    }
}

public class WeightedIntervalScheduling {

    // Function to find the last compatible job for a given job
    public static int findLastCompatible(Job[] jobs, int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (jobs[i].finish <= jobs[index].start) {
                return i;
            }
        }
        return -1;
    }

    // Function to compute the optimal solution for Weighted Interval Scheduling
    public static int computeSolution(Job[] jobs, List<Job> selectedJobs) {
        // Sort jobs by finish times in ascending order
        Arrays.sort(jobs, (a, b) -> a.finish - b.finish);
        int n = jobs.length;
        int[] M = new int[n];
        M[0] = jobs[0].weight;

        // Calculate the maximum weight for each job
        for (int j = 1; j < n; j++) {
            int takeJob = jobs[j].weight;
            int l = findLastCompatible(jobs, j);

            if (l != -1) {
                takeJob += M[l];
            }

            if (takeJob > M[j - 1]) {
                M[j] = takeJob;
                selectedJobs.add(jobs[j]); // Store the selected job
            } else {
                M[j] = M[j - 1];
            }
        }

        return M[n - 1]; // Maximum weight achievable with all jobs
    }

    public static void main(String[] args) {
        Job[] jobs = {
                new Job('A', 1, 4, 5),
                new Job('B', 3, 7, 4),
                new Job('C', 3, 5, 6),
                new Job('D', 5, 9, 7),
                new Job('E', 6, 9, 6)
        };

        List<Job> selectedJobs = new ArrayList<>();
        int maxWeight = computeSolution(jobs, selectedJobs);
        Collections.reverse(selectedJobs); // Reverse the list to get the order of selected jobs

        System.out.println("Maximum Weight: " + maxWeight);
        System.out.println("Selected Jobs:");
        for (Job job : selectedJobs) {
            System.out.println("Job " + job.id + " (Weight: " + job.weight + ")");
        }
    }
}
