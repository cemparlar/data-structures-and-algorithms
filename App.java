import java.util.Random;

public class App {

    private static int indexMin = 0;
    private static int i = 0;

    /**
     * This function finds the minimum value in an array
     * @param arr is the array that the function will find the minimum value in
     * @return the index of the minimum value in the array
     */
    public static int minFinder(int[] arr) {
        int index = 0; //int index represents the index at which the smallest value will be located after for loop

        //The following loop updates the index if the value at 'i' is smaller //than the value at the 'index'
        for(int i = 1; i < arr.length; i++) {
            if(arr[index] > arr[i])
                index = i;
        }
        //once the minimum value is found, function returns the index of it in the array
        return index;
    }

    /**
     * This function is a recursive function that finds the minimum value in an array
     * @param arr is the array that the function will find the minimum value in
     * @return the index of the minimum value in the array
     */
    public static int minFinder_recursive(int[] arr) {

        i++; //int i is a global static variable used for recursive iteration through array and starts at 1
        //global static variable indexMin is 0, and represents the index location of the minimum value in array

        //The following if condition updates the indexMin if the value at iterator index i is a smaller value
        //than the value at the index indexMin
        if(arr[indexMin] > arr[i])
            indexMin = i;

        if(i + 1 == arr.length) // stop if the end of the array is reached
            return indexMin;
        else
            // else continue until the end of the array is reached
            return minFinder_recursive(arr);

    }


    public static void main(String[] args) throws Exception {
        int[] myArr = new int[10000];
        Random myRand = new Random(123); //creating Random object
        // Range for random to select from
        int min = 5;
        int max = 1000;

        int indexMin = 0;
        int indexMin2 =0;

        for(int i = 0; i< myArr.length; i++) {
            myArr[i] = myRand.nextInt(max-min+1) + min; //storing random integers in an array
        }
        //here we check the last item of array to see if it is full:
        System.out.println(myArr[myArr.length-1]); //printing last element, not the biggest or smallest but just the last one


        //calculating the runtime for the minFinder() function
        long startTime = System.nanoTime();
        indexMin=minFinder(myArr);
        long endTime = System.nanoTime();
        long duration = endTime-startTime;

        //calculating the runtime for the minFinder_recursive() function
        long startTimeRec = System.nanoTime();
        indexMin2= App.minFinder_recursive(myArr);
        long endTimeRec = System.nanoTime();
        long durationRec = endTimeRec-startTimeRec;

        System.out.println("The minimum is at location: " + indexMin + ". The value is: " + myArr[indexMin]);
        System.out.println("The loop minFinder method took: " + duration + " nanoseconds.");

        System.out.println("The recursive minimum is at location: " + indexMin2 + ". The value is: " + myArr[indexMin2]);
        System.out.println("The recursive minFinder method took: " + durationRec + " nanoseconds.");

    }
}
