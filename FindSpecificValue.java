import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class FindSpecificValue {


    public static void main(String[] args) {
        int numItem = 1000; // This decides how big your array is
        int[] myArr = new int[numItem];
        Random myRand = new Random(111); // creating Random object
        // Range for random to select from
        int min = 1;
        int max = 100;

        int val = 5;

        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = myRand.nextInt(max - min + 1) + min; // storing random integers in an array
        }

        // Method 1: Linear Search
        // The linearSearch method implements a basic for-loop to find the specific value in array
        // The time complexity of this method is O(n)
        long start = System.nanoTime();
        int index = linearSearch(myArr, val);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("Linear Search Time: " + time + " nanoseconds");

        // Method 2: Array sort then Binary Search
        // The binarySearch method implements array sort and a binary search to find the specific value in array
        // The time complexity of this method is O(n^2) + O(log n) = O(n^2)
        start = System.nanoTime();
        index = binarySearch(myArr, val);
        end = System.nanoTime();
        time = end - start;
        System.out.println("Binary Search Time: " + time + " nanoseconds");

        // Method 3: Heap sort then Binary Search
        // The binaryTreeSearch method implements a heap sort and a binary search to find the specific value in array
        // The time complexity of this method is O(nlog n) + O(log n) = O(nlog n)
        start = System.nanoTime();
        index = binaryTreeSearch(myArr, val);
        end = System.nanoTime();
        time = end - start;
        System.out.println("Binary Tree Search Time: " + time + " nanoseconds");
    }

    /**
     * This method will loop through an array to return the index of the first occurrence of the value in the array
     * @param arr the array to be searched
     * @param val the value to be searched for
     * @return the index of the first occurrence of the value in the array
     * */
    public static int linearSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method will sort the array input for binary search and then return the index of the first occurrence of the value in the array
     * @param arr the array to be searched
     * @param val the value to be searched for
     * @return the index of the first occurrence of the value in the array
     * */
    public static int binarySearch(int[] arr, int val) {
        //Orders on ascending order
        Arrays.sort(arr);

        //Binary Search
        int start = 0;
        int end = arr.length - 1;
        int mid = (start + end) / 2;
        while(start <= end) {
            if (arr[mid] == val) {
                return mid;
            } else if (arr[mid] < val) {
                //If the value is GREATER than the middle value, then the value is in the right half of the array
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = (start + end) / 2;
        }
        return -1;
    }

    /**
     * This method will "Heap Sort" the array input for binary search and then return the index of the first occurrence of the value in the linkedlist
     * @param arr the array to be searched
     * @param val the value to be searched for
     * @return the index of the first occurrence of the value in the array
     * */
    public static int binaryTreeSearch(int[] arr, int val) {
        //Orders on ascending order
        LinkedList<Integer> sortedList = SortAlgorithmsComparison.heapSort(arr); // O(n log n)
        int start = 0;
        int end = sortedList.size() - 1;
        int mid = (start + end) / 2;
        while(start <= end) {
            if (sortedList.get(mid) == val) {
                return mid;
            } else if (sortedList.get(mid) > val) {
                //If the value is SMALLER than the middle value, then the value is in the left half of the array
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return -1;
    }

}
