import java.util.Random;

/**
 *  The code for the methods are based on: Data Structures and Algorithms in Java, 6th Edition
 *  by Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser.
 *  Chapter 12 - Sorting and Selection,
 *  12.1 - Merge Sort (pgs. 532-544)
 *  12.2 - Quick Sort (pgs. 544-555)
 *  12.2 - Radix Sort (pgs. 559-560)
 */
public class SortingAlgorithmsComparison2 {
    
    public static void main(String[] args) {

        // Run-time testing for each sorting method with different array sizes
        // (100 runs averaged per size per algorithm)
        runTimeTest(100);
        runTimeTest(1000);
        runTimeTest(10000);
        runTimeTest(100000);
        runTimeTest(1000000);
    }


    /**
     * Merge Sort Algorithm (Divide and Conquer) - O(n log n)
     * @param arr
     * @param temp
     * @param left
     * @param right
     */
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }
    /**
     * Merge method for Merge Sort
     * @param arr
     * @param temp
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
    }

    /**
     * Quick Sort Algorithm O(n log n)
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    /**
     * Partition method for Quick Sort
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {
        int pivotValue = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivotValue) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    /**
     * Swap method for Quick Sort
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Radix Sort Algorithm O(n)
     * @param arr
     */
    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }
    /**
     * Get Max method for Radix Sort
     * @param arr
     * @return
     */
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    /**
     * Count Sort method for Radix Sort
     * @param arr
     * @param exp
     */
    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    /**
     * Method to create an array of random integers
     * @param numItem
     * @return
     */
    public static int[] createArray(int numItem) {
        int[] myArr = new int[numItem];
        Random myRand = new Random();
        // Range for random to select from
        int min = 5;
        int max = numItem;

        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = myRand.nextInt(max - min + 1) + min; // storing random integers in an array
        }
        return myArr;
    }

    // Run-time testing for each sorting method with different array sizes
    /**
     * Method to test the run-time of each sorting method averaged over 100 runs
     * @param arraySize
     */
    public static void runTimeTest(int arraySize) {
        int[] myArr;
        int[] temp = new int[arraySize];
        int numRun = 100;
        // Print the size of array
        System.out.println("Array size: " + arraySize);

        // declare variables to store start time, end time, and duration
        long startTime;
        long endTime;
        long duration;
        long sumDuration = 0;

        for (int i = 0; i < numRun; i++) {
            myArr = createArray(arraySize);
            startTime = System.nanoTime();
            mergeSort(myArr, temp, 0, myArr.length - 1);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            sumDuration += duration;
        }
        System.out.println("Merge Sort: " + sumDuration/numRun + " nanoseconds");

        sumDuration = 0; //reset sumDuration
        for (int i = 0; i < numRun; i++) {
            myArr = createArray(arraySize);
            startTime = System.nanoTime();
            quickSort(myArr, 0, myArr.length - 1);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            sumDuration += duration;
        }
        System.out.println("Quick Sort: " + sumDuration/numRun + " nanoseconds");

        sumDuration = 0; //reset sumDuration
        for (int i = 0; i < numRun; i++) {
            myArr = createArray(arraySize);
            startTime = System.nanoTime();
            radixSort(myArr);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            sumDuration += duration;
        }
        System.out.println("Radix Sort: " + sumDuration/numRun + " nanoseconds");
        System.out.println("");
    }
}