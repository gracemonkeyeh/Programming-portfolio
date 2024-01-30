/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        // TODO: Implement this
        int size = dataArray.length;
        int low = 0; // index of first element
        int high = size - 1; // index of last element
        int max = dataArray[low]; // the max item
        int maxIndex = 0;

        if (size == 0) {
            return 0;
        }

        if (size > Integer.MAX_VALUE) {
            return 0;
        }


        for (int i = 0; i < size; i++) {
            if (dataArray[i] > max) {
                max = dataArray[i];
                maxIndex = i;
            }
        }


        if (max > Integer.MAX_VALUE) { // the max exceeded the largest possible Integer.MAX_VALUE
            return 0;
        }

        return max;
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
