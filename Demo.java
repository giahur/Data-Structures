/** CSDS 233 Assignment 5 Benchmarking
 * @author Gia Hur **/

// Time sorting algorithms in Sort class
public class Demo extends Sort {

    public static void diffSizeArray(int size) {
        System.out.println("Sort method times for arrays of size " + size);
        System.out.println("insertionSort:");
        int[] randomArray = generateRandomArray(size);
        insertionSortTime(randomArray);
        
        System.out.println("bubbleSort:");
        randomArray = generateRandomArray(size);
        bubbleSortTime(randomArray);

        System.out.println("shellSort:");
        randomArray = generateRandomArray(size);
        shellSortTime(randomArray);
        
        System.out.println("quickSort:");
        randomArray = generateRandomArray(size);
        quickSortTime(randomArray);

        System.out.println("mergeSort:");
        randomArray = generateRandomArray(size);
        mergeSortTime(randomArray);

        System.out.println("upgradedQuickSort:");
        randomArray = generateRandomArray(size);
        upgradedQuickSortTime(randomArray);
    }

    public static int[] reverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int current = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = current;
        }
        return arr;
    }

    public static void insertionSortTime(int[] arr) {
        long startTime = System.nanoTime();
        insertionSort(arr);
        long endTime = System.nanoTime();
        System.out.println("\tRandom inputs:" + (endTime - startTime));

        startTime = System.nanoTime();
        insertionSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tSorted inputs:" + (endTime - startTime));

        reverseArray(arr);
        startTime = System.nanoTime();
        insertionSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tReverse-sorted:" + (endTime - startTime));
    }

    public static void bubbleSortTime(int[] arr) {
        long startTime = System.nanoTime();
        bubbleSort(arr);
        long endTime = System.nanoTime();
        System.out.println("\tRandom inputs:" + (endTime - startTime));

        startTime = System.nanoTime();
        bubbleSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tSorted inputs:" + (endTime - startTime));

        reverseArray(arr);
        startTime = System.nanoTime();
        bubbleSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tReverse-sorted:" + (endTime - startTime));
    }

    public static void shellSortTime(int[] arr) {
        long startTime = System.nanoTime();
        shellSort(arr);
        long endTime = System.nanoTime();
        System.out.println("\tRandom inputs:" + (endTime - startTime));

        startTime = System.nanoTime();
        shellSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tSorted inputs:" + (endTime - startTime));

        reverseArray(arr);
        startTime = System.nanoTime();
        shellSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tReverse-sorted:" + (endTime - startTime));
    }
    
    public static void quickSortTime(int[] arr) {
        long startTime = System.nanoTime();
        quickSort(arr);
        long endTime = System.nanoTime();
        System.out.println("\tRandom inputs:" + (endTime - startTime));

        startTime = System.nanoTime();
        quickSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tSorted inputs:" + (endTime - startTime));

        reverseArray(arr);
        startTime = System.nanoTime();
        quickSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tReverse-sorted:" + (endTime - startTime));
    }
    
    public static void mergeSortTime(int[] arr) {
        long startTime = System.nanoTime();
        mergeSort(arr);
        long endTime = System.nanoTime();
        System.out.println("\tRandom inputs:" + (endTime - startTime));

        startTime = System.nanoTime();
        mergeSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tSorted inputs:" + (endTime - startTime));

        reverseArray(arr);
        startTime = System.nanoTime();
        mergeSort(arr);
        endTime = System.nanoTime();
        System.out.println("\tReverse-sorted:" + (endTime - startTime));
    }
    
    public static void upgradedQuickSortTime(int[] arr) {
        long startTime = System.nanoTime();
        upgradedQuickSort(arr, (int)Math.log(arr.length), 3);
        long endTime = System.nanoTime();
        System.out.println("\tRandom inputs:" + (endTime - startTime));

        startTime = System.nanoTime();
        upgradedQuickSort(arr, (int)Math.log(arr.length), 3);
        endTime = System.nanoTime();
        System.out.println("\tSorted inputs:" + (endTime - startTime));

        reverseArray(arr);
        startTime = System.nanoTime();
        upgradedQuickSort(arr, (int)Math.log(arr.length), 3);
        endTime = System.nanoTime();
        System.out.println("\tReverse-sorted:" + (endTime - startTime) + "\n");
    }
    
    public static void main(String[] args) {
        diffSizeArray(10);
        diffSizeArray(20);
        diffSizeArray(50);
        diffSizeArray(100);
        diffSizeArray(200);
        diffSizeArray(500);
        diffSizeArray(1000);
        diffSizeArray(2000);
        diffSizeArray(5000);
    }
}