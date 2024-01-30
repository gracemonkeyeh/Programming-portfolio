//import jdk.incubator.vector.VectorOperators;

//import java.util.Random;

public class SortingTester {

    public static boolean checkSort(ISort sorter, int size) {
        // TODO: implement this
        // create a new array of key value pairs and fill them up
        size = 4;
        KeyValuePair[] TestArr = new KeyValuePair[size];

        long cost;
        TestArr[0] = new KeyValuePair(1,1);
        TestArr[1] = new KeyValuePair(2,2);
        TestArr[2] = new KeyValuePair(3,3);
        TestArr[3] = new KeyValuePair(4,4);


        // create array
        /*for (int i = 0; i < size; i++) {
            TestArr[i] = new KeyValuePair(size - i, i);
        }*/

        /*TestArr[0] = new KeyValuePair(1, 1);
        TestArr[1] = new KeyValuePair(0, 2);

        for (int i = 2; i < size; i++) {
            TestArr[i] = new KeyValuePair(i, i);

        }*/

        if (size == 1 || size == 0) {
            return true;
        }




        cost = sorter.sort(TestArr); // sort the array using the unknown sorter by key

        System.out.println("Next sort: The cost of sorting was " + cost);

        // after sorting, check that the keys are in ascending order
        int next;
        for (int i = 1; i < size; i++) {

            if (TestArr[i - 1].getKey() > TestArr[i].getKey()) {
                //System.out.println("TestArr[" + i + "] key is " + TestArr[i].getKey() + " TestArr[" + i + "] value is " + TestArr[i].getValue());
                return false;
            }

        }
        return true;
    }

    public static boolean isStable(ISort sorter, int size) {
        // TODO: implement this
        // if any two keys are the same and appear side by side, check that the order of the values did not change

        KeyValuePair[] TestArr = new KeyValuePair[size];

        if (size == 1 || size == 0) {
            return true;
        }
        if (checkSort(sorter, size) == false) { // the sorter is evil
            return false;
        }



        TestArr[0] = new KeyValuePair(1, 1); // purposely put 3 values with the same key
        TestArr[1] = new KeyValuePair(2, 2);
        TestArr[2] = new KeyValuePair(5, 3);
        TestArr[3] = new KeyValuePair(3, 7);
        TestArr[4] = new KeyValuePair(4, 8);
        TestArr[5] = new KeyValuePair(5, 4);
        TestArr[6] = new KeyValuePair(6, 10);
        TestArr[7] = new KeyValuePair(7, 11);
        TestArr[8] = new KeyValuePair(8, 12);
        TestArr[9] = new KeyValuePair(9, 13);



        for (int i = 10; i < size; i++) {
            TestArr[i] = new KeyValuePair(i, size - i);
        }

        sorter.sort(TestArr);

        for (int i = 0; i < size - 1; i++) {


            if (TestArr[i].getKey() == TestArr[i + 1].getKey()) {
                if (TestArr[i].getValue() > TestArr[i + 1].getValue()) {
                    // the value of i should be less than value of i+1, not more
                    return false;
                }
                else {
                    //System.out.println("The values did not change");
                }
            }
        }



        return true;
    }

    public static void main(String[] args) {
        // TODO: implement this
        boolean check;
        boolean retVal;
        int size = 500;

        ISort sortingObjectA = new SorterA();
        check = checkSort(sortingObjectA, size);
        if (check) {
            System.out.println("Its sorted correctly");
        }
        else {
            System.out.println("NOT sorted");
        }

        retVal = isStable(sortingObjectA, size);
        if (retVal) {
            System.out.println("Sorting object A is stable");
        }
        else {
            System.out.println("Sorting object A is not stable");
        }


        ISort sortingObjectB = new SorterB();
        check = checkSort(sortingObjectB, size);
        if (check) {
            System.out.println("Its sorted correctly");
        }
        else {
            System.out.println("NOT sorted");
        }

        retVal = isStable(sortingObjectB, size);
        if (retVal) {
            System.out.println("Sorting object B is stable");
        }
        else {
            System.out.println("Sorting object B is not stable");
        }


        ISort sortingObjectC = new SorterC();
        check = checkSort(sortingObjectC, size);
        if (check) {
            System.out.println("Its sorted correctly");
        }
        else {
            System.out.println("NOT sorted");
        }

        retVal = isStable(sortingObjectC, size);
        if (retVal) {
            System.out.println("Sorting object C is stable");
        }
        else {
            System.out.println("Sorting object C is not stable");
        }


        ISort sortingObjectD = new SorterD();
        check = checkSort(sortingObjectD, size);
        if (check) {
            System.out.println("Its sorted correctly");
        }
        else {
            System.out.println("NOT sorted");
        }

        retVal = isStable(sortingObjectD, size);
        if (retVal) {
            System.out.println("Sorting object D is stable");
        }
        else {
            System.out.println("Sorting object D is not stable");
        }

        ISort sortingObjectE = new SorterE();
        check = checkSort(sortingObjectE, size);
        if (check) {
            System.out.println("Its sorted correctly");
        }
        else {
            System.out.println("NOT sorted");
        }

        retVal = isStable(sortingObjectE, size);
        if (retVal) {
            System.out.println("Sorting object E is stable");
        }
        else {
            System.out.println("Sorting object E is not stable");
        }

        ISort sortingObjectF = new SorterF();
        check = checkSort(sortingObjectF, size);
        if (check) {
            System.out.println("Its sorted correctly");
        }
        else {
            System.out.println("NOT sorted");
        }

        retVal = isStable(sortingObjectF, size);
        if (retVal) {
            System.out.println("Sorting object F is stable");
        }
        else {
            System.out.println("Sorting object F is not stable");
        }

    }
}
