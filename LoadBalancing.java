/**
 * Contains static routines for solving the problem of balancing m jobs on p processors
 * with the constraint that each processor can only perform consecutive jobs.
 */

public class LoadBalancing {

    /**
     * Checks if it is possible to assign the specified jobs to the specified number of processors such that no
     * processor's load is higher than the specified query load.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param queryLoad the maximum load allowed for any processor
     * @param p the number of processors
     * @return true iff it is possible to assign the jobs to p processors so that no processor has more than queryLoad load.
     */
    public static boolean isFeasibleLoad(int[] jobSizes, int queryLoad, int p) {
        // TODO: Implement this

        if (p == 0) {
            return false;
        }

        //int min_p = 1; //minimum number of processors needed
        int size = jobSizes.length; // no. of jobs
        int jobs_in_each_p = size / p; // best case most no. of jobs in each processor,
        if (jobs_in_each_p == 0) {
            jobs_in_each_p = 1;
        }
        int max_jobs_in_each_p = size - p + 1; // worst case most.no of jobs in each processor

        // because we want to know if its possible
        int [] temp_arr = new int[size];
        int index = 0; // index into temp_arr
        // fill up the array
        for (int i = 0; i < size; i++) {
            temp_arr[i] = 0;
        }
        int max_result = 0;

        int sum = 0; // sum of job sizes in each min_p processor
        // then collect each sum to temp_arr;
        // find the Max of temp_arr and check that it is less than queryLoad



        for (int i = 0; i < size; i++) {
            for (int j = i; j < i + jobs_in_each_p; j++) { // jobs in each min_p number of processors
                sum = sum + jobSizes[j];
            }
            temp_arr[index] = sum;
            ++index;
            sum = 0; // reset sum
            if (size - i <= jobs_in_each_p) { // not enough jobs  left to form groups of 5j / 3p
                // break out of the outer for loop
                i = size;
            }
        }
        max_result = searchMax(temp_arr);
        if (max_result <= queryLoad) {
            return true;
        }
        else {
            // search for the second max?
        }
        return false;

    }



    public static int searchMax(int[] jobSizes) {
        // TODO: Implement this
        int size = jobSizes.length;
        int low = 0; // index of first element
        int high = size - 1; // index of last element
        int max = jobSizes[low]; // the max item
        int maxIndex = 0; // index of the max item


        for (int i = 0; i < size; i++) {
            if (jobSizes[i] > max) {
                max = jobSizes[i];
                maxIndex = i;
            }
        }

        return max;
    }

    /**
     * Returns the minimum achievable load given the specified jobs and number of processors.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param p the number of processors
     * @return the maximum load for a job assignment that minimizes the maximum load
     */
    public static int findLoad(int[] jobSizes, int p) {
        // TODO: Implement this

        int begin = 0;
        int end;
        int mid;
        int sum = 0;
        for (int i = 0; i < jobSizes.length; i++) {
            sum = sum + jobSizes[i];
        }
        end = sum;

        while (begin < end) {
            mid = begin + (end - begin) / 2;
            if (isFeasibleLoad(jobSizes, mid, p)) {
                end = mid;
            }
            else {
                begin = mid + 1;
            }
        }
        return begin;

        /*int recurseTimes = p - 1; // number of times to look for new mid
        int start = 0;
        int end = jobSizes.length - 1;
        int mid = 0;
        int result = 0;
        int right = 0; // no. of times recurse to the right
        int left = 0; // no. of times recursed to the left

        // odd number of arrays
        while (recurseTimes > 0) {
            mid = start + (end - start) / 2;
            System.out.println("This is the new mid jobSizes " + jobSizes[mid]);

            if (jobSizes[mid + 1] > jobSizes[mid] && jobSizes[mid - 1] < jobSizes[mid]) { // right side more
                //recurse to the right
                ++right;
                if (recurseTimes - 1 > 0) {
                    System.out.println("hi!");
                    start = mid + 1;
                }

            }
            else if (jobSizes[mid - 1] > jobSizes[mid] && jobSizes[mid + 1] < jobSizes[mid]) { // left side more
                // recurse to the left
                ++left;
                if (recurseTimes - 1 > 0) {
                    end = mid - 1;
                }
            }
            else if (jobSizes[mid - 1] > jobSizes[mid] && jobSizes[mid + 1] > jobSizes[mid]) { // both sides more
                // recurse to the side whose more is less
                if ((jobSizes[mid - 1] - jobSizes[mid]) < (jobSizes[mid + 1] - jobSizes[mid])) {
                    // recurse to the left
                    ++left;
                    System.out.println("Entering here!");
                    end = mid - 1;
                }
                else if ((jobSizes[mid - 1] - jobSizes[mid]) > (jobSizes[mid + 1] - jobSizes[mid])){
                    // recurse to the right
                    ++right;
                    System.out.println("Entering here 2!");
                    if (recurseTimes - 1 > 0) {
                        start = mid + 1;
                    }

                }
            }

            -- recurseTimes;

        }

        // add up the job Sizes from start to mid if left > right
        // add up the job Sizes from mid to end if right > left
        if (left > right) {
            for (int i = start; i <= mid; i++) {
                System.out.println("These are the jobSizes being added " + jobSizes[i]);
                result = result + jobSizes[i];
            }
        }
        else if (right > left) {
            for (int i = mid; i <= end; i++) {
                System.out.println("These are the jobSizes being added " + jobSizes[i]);
                result = result + jobSizes[i];
            }
        }





        return result;*/

        /*int[] jobSizes1 = new int[jobSizes.length];
        for (int i = 0; i < jobSizes.length; i++) {
            jobSizes1[i] = jobSizes[i];
            // manually copy array
        }

        int[] sorted = LoadBalancing.sort(jobSizes);
        for (int i = 0; i < jobSizes.length; i++) {
            System.out.println(sorted[i]);
        }

        int start = 0;
        int end = jobSizes.length - 1; // arr index
        int mid; //index
        int result = 0;

        // add job Sizes
        /*int sum = 0;
        for (int i = 0; i < jobSizes.length; i++) {
            sum = sum + jobSizes[i];
        }
        end = sum;

        int curr_min;*/

       /* while (start <= end) {
            mid = start + (end - start) / 2;
            if (isFeasibleLoad(jobSizes, sorted[mid], p)) {
                result = sorted[mid];
                System.out.println("if statement, result = " + result);
                // now find an even lower mid
                end = mid - 1;
                System.out.println("The new end is " + end);

                for (int i = 0; i < jobSizes.length; i++) {
                    jobSizes[i] = jobSizes1[i];
                    // manually copy array
                }
            }
            else { // the mid is too low, go higher because some processors exceeded the previous query load
                start = mid + 1;
                System.out.println("Else statement: The new start is " + start);
            }

        }
        return result;*/

        // initialize start as the largest element in jobSizes
        // initialize end as the sum of jobSizes
        // we want to find the minimum possible sum of job loads for p processors
        // to do so, we keep feeding a new queryload into IsFeasible() function until we get the smallest possible value

        // while loop, find mid, use isFeasible function with queryLoad = mid
        // create a copy of jobSizes array

            // master copy of jobSizes array
        /*int[] jobSizes1 = new int[jobSizes.length];
        System.arraycopy(jobSizes, 0, jobSizes1, 0, jobSizes.length);

        int start = searchMax(jobSizes);
        System.arraycopy(jobSizes1, 0, jobSizes, 0, jobSizes.length);
        // keep restoring jobSizes array whenever you use searchMax() or isFeasible()

        int mid = 0;
        int result = 0;
        int sum = 0; //  sum of all job sizes

        // add together the job sizes
        for (int i = 0; i < jobSizes.length; i++) {
            sum = sum + jobSizes[i];

        }
        int end = sum;
        System.out.println("The sum is " + sum);




        while (start <= end) {
            mid = start + (end - start) / 2;
            if (isFeasibleLoad(jobSizes, mid, p)) { // the array jobSizes gets altered
                // if isFeasible() returns false, it means mid is too high
                result = mid;
                System.out.println("if statement" + result);
                end = mid - 1;
                System.arraycopy(jobSizes1, 0, jobSizes, 0, jobSizes.length); // restore jobSizes array
            }
            else {
                System.out.println("else statement" + result);
                System.arraycopy(jobSizes1, 0, jobSizes, 0, jobSizes.length);
                end = mid - 1;
                //start = mid + 1;
            }
        }
        return result;*/

        /*while (start <= end) {
            mid = start + (end - start) / 2; // index of middle

            // add up all the jobs from index start to mid;
            for (int i = start; i <= mid; i++) {
                sum = sum + jobSizes[i];
            }

            if (isFeasibleLoad(jobSizes, sum, p)) {
                result  = sum;
                end = mid - 1;
                // keep subtracting until find the smallest possible mid
            }
            else {
                start = mid + 1;
            }
        }*/



    }


    // These are some arbitrary testcases.
    public static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, 100, 80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83},
            {7}
    };

    /**
     * Some simple tests for the findLoad routine.
     */
    public static void main(String[] args) {
        for (int p = 1; p < 30; p++) {
            System.out.println("Processors: " + p);
            for (int[] testCase : testCases) {
                System.out.println(findLoad(testCase, p));
            }
        }
    }
}
