import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class will be used to compare the performance of the selection sort, insertion sort, and heap sort algorithms
 * This code snippet is based on the concepts from the textbook "Data Structures and Algorithms in Java, 6th Edition"
 * by Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser.
 * pages 386-388
 * */
public class SortAlgorithmsComparison {

    /**
     *  This method implements the selection sort algorithm using a PriorityQueue ADT (MyPQ)
     *  First phase, inserting all the elements into the PQ without sorting - O(n)
     *  Second phase, 'Select Sort' the elements from the PQ, adding them back to the sequence S - O(n^2)
     * @param arr - array of integers
     */
    public static LinkedList<Integer> selectionSort(int[] arr) {
        MyPQ pqSort = new MyPQ();
        LinkedList<Integer> sequence = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            sequence.add(arr[i]);
        }
        // Phase 1: insert all elements of the sequence S into the unsorted PriorityQueue P
        while (sequence.size() > 0) {
            // insert first element of the sequence into the PQ O(1)
            pqSort.insert(sequence.remove(0));
        }
        // Phase 2: Selection sort the elements into the sequence linked list
        while (pqSort.size() > 0) {
            // remove the min value element from unsorted and add it to sorted O(n)
            sequence.add(pqSort.removeMin());
        }
        return sequence; // return the sorted linked list
    }



    /**
     * This method implements the insertion sort algorithm using a PriorityQueue ADT (MyPQSort)
     * First phase, 'Insertion Sort' the elements into the PQ ordered - O(n^2)
     * Second phase, we remove the elements from the PQ, adding them back to the sequence S - O(n)
     * @param arr - array of integers
     */
    public static LinkedList<Integer> insertionSort(int[] arr) {
        MyPQSort pqSort = new MyPQSort();
        LinkedList<Integer> sequence = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            sequence.add(arr[i]);
        }
        // Phase 1: Insert sort all elements of the sequence S into the PriorityQueue P
        while (sequence.size() > 0) {
            // insert the min value element from unsorted sequence and add it to sorted PQ O(n)
            pqSort.insert(pqSort.removeMin(sequence));
        }
        // Phase 2: Add all elements from the PriorityQueue into the sequence linked list
        while (pqSort.size() > 0) {
            // remove the min index from sorted PQ and add it to sorted sequence O(1)
            sequence.add(pqSort.removeMin());
        }
        return sequence;
    }

    /**
     * This method implements the heap sort algorithm
     * The heap sort was implemented using a BinaryTree ADT, based on the textbook material (Chapter 9.3)
     * The heap sort method uses the MyHeap class to implement the BinaryTree ADT
     * In heap sort we sort in the first phase by inserting the elements into the BinaryTree - O(n log n)
     * In the second phase we remove the elements from the BinaryTree and add them to the sequence linked list - O(n log n)
     * @param arr - array of integers
     */
    public static LinkedList<Integer> heapSort(int[] arr) {
        MyHeap heapSort = new MyHeap();
        LinkedList<Integer> sequence = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            sequence.add(arr[i]);
        }
        // Phase 1: Insert sort all elements of the sequence S into the Heap BinaryTree
        while (sequence.size() > 0) {
            // insert first element of the sequence into the heap tree and heapify O(log n)
            heapSort.insert(sequence.remove());
        }
        // Phase 2: Add all elements from the Heap BinaryTree into the sequence linked list
        while (heapSort.size() > 0) {
            // remove the min index from unsorted and add it to sorted O(log n)
            sequence.add(heapSort.removeMin());
        }
        return sequence;



    }

    // Method for finding a specific integer value in an

    public static void main(String[] args) {
        int numItem = 10; // This decides how big you array is
        int[] myArr = new int[numItem];
        Random myRand = new Random(); // creating Random object
        // Range for random to select from
        int min = 5;
        int max = 1000;

        int indexMin = 0;

        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = myRand.nextInt(max - min + 1) + min; // storing random integers in an array
        }
        //create an ordered array going from 1 to 1000
        int[] orderedArr = new int[numItem];
        for (int i = 0; i < orderedArr.length; i++) {
            orderedArr[i] = i + 1;
        }

        // Time test: Selection sort
        LinkedList<Integer> sorted = new LinkedList<>();
        double startTime = System.nanoTime();
        sorted = selectionSort(myArr);
        double endTime = System.nanoTime();
        double duration = (endTime - startTime);
        System.out.println("The time it took to select-sort is: " + String.format("%.2f", duration/1000000) + " milliseconds");

        // Time test: Insert sort
        double startTime2 = System.nanoTime();
        sorted = insertionSort(myArr);
        double endTime2 = System.nanoTime();
        double duration2 = (endTime2 - startTime2);
        System.out.println("The time it took to insert-sort is: " + String.format("%.2f", duration2/1000000) + " milliseconds");

        // Time test: Heap sort
        double startTime3 = System.nanoTime();
        sorted = heapSort(myArr);
        double endTime3 = System.nanoTime();
        double duration3 = (endTime3 - startTime3);
        System.out.println("The time it took to heap-sort is: " + String.format("%.2f", duration3/1000000) + " milliseconds");


        // The slowest sort is the selection sort, followed by the insertion sort, and the fastest is the heap sort

        //Repeat the same tests for each sort algorithm, but this time use an ordered array

        // Time test: Selection sort
        LinkedList<Integer> sorted2 = new LinkedList<>();
        double startTime4 = System.nanoTime();
        sorted2 = selectionSort(orderedArr);
        double endTime4 = System.nanoTime();
        double duration4 = (endTime4 - startTime4);
        System.out.println("The time it took to select-sort is: " + String.format("%.2f", duration4/1000000) + " milliseconds");

        // Time test: Insert sort
        double startTime5 = System.nanoTime();
        sorted2 = insertionSort(orderedArr);
        double endTime5 = System.nanoTime();
        double duration5 = (endTime5 - startTime5);
        System.out.println("The time it took to insert-sort is: " + String.format("%.2f", duration5/1000000) + " milliseconds");

        // Time test: Heap sort
        double startTime6 = System.nanoTime();
        sorted2 = heapSort(orderedArr);
        double endTime6 = System.nanoTime();
        double duration6 = (endTime6 - startTime6);
        System.out.println("The time it took to heap-sort is: " + String.format("%.2f", duration6/1000000) + " milliseconds");
    }
}

/**
 * This class is used to implement the Heap Sort algorithm
 * */
class MyHeap implements ISort{
    private ArrayList<Integer> heap = new ArrayList<>();

    /**
     * This method will insert an element into the heap and restore the heap property
     * @param element - integer value to be inserted into the heap
     * */
    public void insert(int element) {
        heap.add(element);
        upHeap(heap.size() - 1);
    }

    /**
     * This method will return the entry with the lowest element value in the heap
     * @return heap.get(0) - the entry with the lowest element value in the heap
     * */
    public int min() {
        return heap.get(0);
    }

    /**
     * This method will return the entry with the lowest element value in the heap and remove it
     * @return root - the entry with the lowest element value in the heap
     * */
    public int removeMin() {
        if(heap.size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        if(heap.size() == 1) {
            return heap.remove(0);
        }
        int root = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        downHeap(0);
        return root;
    }

    /**
     * This method will "bubble-up" the input, if necessary, to restore the heap property by swapping input with parent.
     * @param index - index of the element to be swapped
     * */
    private void upHeap(int index) {
        if (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                upHeap(parent);
            }
        }
    }

    /**
     * This method will "heapify" the input, if necessary, to restore the heap property by swapping input with child.
     * @param index
     * */
    private void downHeap(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int parent = index;
        if (left < heap.size() && heap.get(left) < heap.get(parent)) {
            parent = left;
        }
        if (right < heap.size() && heap.get(right) < heap.get(parent)) {
            parent = right;
        }
        if (parent != index) {
            swap(index, parent);
            downHeap(parent);
        }
    }

    /**
     * This method will swap the elements at the input indices.
     * @param i - index of the first element to be swapped
     * @param j - index of the second element to be swapped
     * */
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * This method will return the size of the heap. Return 0 if empty.
     * @return heap.size() - the size of the heap
     * */
    public int size() {
        return heap.size();
    }
    /**
     * This method will return a string containing heap elements according to the order in the ArrayList.
     * @return heap - a string containing heap elements according to the order in the ArrayList
     * */
    @Override
    public String toString() {
        return heap.toString();
    }
}

/**
 * This class is used to implement an insertion Sort algorithm using a priority queue
 * */
class MyPQSort implements ISort {

    LinkedList<Integer> list = new LinkedList<Integer>();

    /**
     * This method will insert an element into the class
     * @param element
     * */
    public void insert(int element) {
        // insert an element to the end of the linked list
        this.list.add(element);
    }

    /**
     * This method will return the first entry in the linked list
     * */
    public int min() {
        // return the first entry in the linked list
        return this.list.get(0);
    }

    /**
     * This method will return the entry with the lowest index in the linked list and remove it
     * @return the entry with the lowest index in the linked list
     * */
    public int removeMin() {
        // return the entry with the lowest index in the linked list and remove it
        return this.list.remove(0);

    }

    /**
     * This method will return the entry with the lowest element value in the linked list sequence S and remove it
     * @param sequence
     * @return min - the entry with the lowest element value in the linked list sequence S
     * */
    public int removeMin(LinkedList<Integer> sequence) {
        // return the first entry in the linked list and remove it
        int min = sequence.get(0);
        int index = 0;
        for(int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) < min) {
                min = sequence.get(i);
                index = i;
            }
        }
        return sequence.remove(index);
    }

    /**
     * This method will return the size of the linked list
     * @return size of linked list
     * */
    public int size() {
        // return size of linked list if empty return 0
        return this.list.size();
    }

    /**
     * This method will return a string containing LinkedList elements
     * @return string containing LinkedList elements
     * */
    @Override
    public String toString() {
        // overrides toString method to return a string containing LinkedList elements
        if (this.list.size() == 0) {
            return "[]";
        }
        String str = "[";
        for(int i = 0; i < this.list.size(); i++) {
            str += this.list.get(i) + ", ";
        }
        return str.substring(0, str.length() - 2) + "]";
    }
}

/**
 * This class will be used to implement a selection Sort algorithm using a priority queue
 * */
class MyPQ implements ISort{

    LinkedList<Integer> list = new LinkedList<Integer>();

    /**
     * This method will insert an element to the end of the linked list of the class
     * @param element
     * */
    public void insert(int element) {
        this.list.add(element);
    }

    /**
     * This method will return the entry with the lowest element value in the linked list
     * @return entry with the lowest element value in the linked list
     * */
    public int min() {
        int min = this.list.get(0);
        for(int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i) < min) {
                min = this.list.get(i);
            }
        }
        return min;
    }

    /**
     * This method will return the entry with the lowest element value in the linked list and remove it
     * @return entry with the lowest element value in the linked list and remove it
     * */
    public int removeMin() {
        int min = this.list.get(0);
        int index = 0;
        for(int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i) < min) {
                min = this.list.get(i);
                index = i;
            }
        }
        return this.list.remove(index);

    }

    /**
     * This method will return the size of the linked list
     * @return size of linked list
     * */
    public int size() {
        // if empty return 0
        return this.list.size();
    }

    /**
     * This method will return a string containing LinkedList elements
     * @return string containing LinkedList elements
     * */
    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "[]";
        }
        String str = "[";
        for(int i = 0; i < this.list.size(); i++) {
            str += this.list.get(i) + ", ";
        }
        return str.substring(0, str.length() - 2) + "]";
    }
}
interface ISort {
    void insert(int element); //insert element into the class
    int min();
    int removeMin();
    int size(); //return size if empty return 0
    String toString(); //overrides toString method to return a string containing LinkedList elements
}

