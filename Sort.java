/** CSDS 233 Assignment 5 
 * @author Gia Hur **/

// Class with 6 sort methods
public class Sort {
    
    // Insertion sort: elements sorted with respect to elements on its left
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {     //traverses array from index 1
            int current = arr[i];
            int j = 0;
            for (j = i; j > 0 && current > arr[j-1]; j--)     // swaps elements to the left of and less than current
                arr[j] = arr[j-1];
            arr[j] = current;
        }
    }        

    // Bubble sort: Performs passes through array, swaps adjacent out-of-order elements, best-case runtime O(n)
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {     // number of passes
            for (int j = 0; j < i; j++) {     // number of comparisons for each pass
                int current = arr[j];
                if (current < arr[j+1]) {     // swaps elements greater than current
                    arr[j] = arr[j+1];
                    arr[j+1] = current;
                }
            }
        }
    }

    // Shell sort: similar to insertion sort, uses larger strides, uses Hibbard's sequence
    public static void shellSort(int[] arr) {
		int k = 1;
		while(2*k <= arr.length)     // finds max number of sequence less than array length
			k = k*2;
		int incr = k - 1;     // Hibbard's sequence: 2^k-1
		while(incr > 0) {     // continues sequence until increment is 0
			for(int i = incr; i < arr.length; i++) {     // for each element...
				int current = arr[i];
				int j = 0;
				for(j = i; j > incr-1 && current > arr[j-incr]; j = j - incr)     // ...compare to all elements incr away
					arr[j] = arr[j-incr];
				arr[j] = current;
			}
			incr = incr/2;
		}
    }
	

    //  Quick sort: partitions array recursively
    public static void quickSort(int[] arr) {
		helperQuickSort(arr, 0, arr.length-1);
	}
	
	private static void helperQuickSort(int[]arr, int first, int last) {
		if(first >= last)     // array isn't empty, input isn't invalid
			return;
		int mid = partition(arr, first, last);     // finds where i and j met to form two subarrays
		helperQuickSort(arr, first, mid);
		helperQuickSort(arr, mid + 1, last);
	}
	
	private static int partition(int[] arr, int first, int last) {
        int pivot = median(arr, first, last);     // finds good pivot: median of first, last, and middle elements
		int i = first - 1; 
		int j = last + 1; 
		while (true) { // loops continuously (putting i < j here would only stop some cases)
			do {
				i++;    // i = 0 before comparison 
			} 
			while (arr[i] > pivot);
			do {
				j--;
			} 
			while (arr[j] < pivot);
			if (i < j) {     // makes sure indices haven't passed each other
                int element = arr[i];
                arr[i] = arr[j];
                arr[j] = element;
            }
			else
				return j;     // midpoint
		}
	}
    
    private static int median(int[] arr, int first, int last) {     // finds median of first, last, middle elements
        int mid = arr[(first + last)/2];     // finds median of array
        int[] medianArray = {arr[first], mid, arr[last]};
        insertionSort(medianArray);     // sorts 3 elements (middle will be median)
        return medianArray[1];
    }

    // Merge sort: splits array, sorts, then merges
    public static void mergeSort(int[] arr) {
	    helperMergeSort(arr, 0, arr.length-1);
    }

    private static void helperMergeSort(int[] arr, int first, int last) {     // splits arrays and calls helperMerge method
	    if(first >= last)
	    	return;
	    int mid = (first + last)/2;    // continuously divides until subarray is length 1
	    helperMergeSort(arr, first, mid);
	    helperMergeSort(arr, mid + 1, last);
        int[] tempArray = new int[arr.length];
	    helperMerge(arr, tempArray, first, mid, mid + 1, last);     // sorts and merges
    }

    private static void helperMerge(int[] arr, int[] tempArray, int leftFirst, int leftLast, int rightFirst, int rightLast) {
        int i = leftFirst;
        int j = rightFirst;
        int tempIndex = leftFirst;
        while(i <= leftLast && j <= rightLast) {     // stops when either index reaches last
            if(arr[i] > arr[j]) {     // compares
                tempArray[tempIndex] = arr[i];
                i++;
            }
            else {
                tempArray[tempIndex] = arr[j];
                j++;
            }
            tempIndex++;
        }
        while(i <= leftLast) {     // copy remaining elements in left
            tempArray[tempIndex] = arr[i];
            i++;
            tempIndex++;
        }

        while(j <= rightLast) {     // copy remaining elements in right
            tempArray[tempIndex] = arr[j];
            j++;
            tempIndex++;
        }

        for(i = leftFirst; i <= rightLast; i++)     //copy result back into original array
            arr[i] = tempArray[i];
    }

    // Upgraded quick sort: quick sort, switches to merge sort at depth limit d, switches to insertion sort at <k elements
    public static void upgradedQuickSort(int[] arr, int d, int k) {
        helperUpgradedQuickSort(arr, 0, arr.length - 1, d, k);
    }

    public static void helperUpgradedQuickSort(int[] arr, int first, int last, int d, int k){
        if(last - first > k) {     // only checks for depth if # elements > k
            int depth = 0;
            if(depth >= d)     // switches to merge
                mergeSort(arr);
            depth++;     // counts number of recursive calls
            int pivot = partition(arr, first, last);
            helperUpgradedQuickSort(arr, first, pivot - 1, d, k);
            helperUpgradedQuickSort(arr, pivot + 1, last, d, k);
        }
        else     // switches to insertion
            insertionSort(arr); 
    }

    // Generates array of random integers of given size
    public static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (n = 0; n < arr.length; n++)
            arr[n] = (int)(10*Math.random());
        return arr;
    }

    //Print given array as String
    public static void printArray(int[] arr) {
        String s = "[";
        for(int i = 0; i < arr.length-1; i++){
            s = s + arr[i] + ", ";
        }
        s = s + arr[arr.length-1] + "]";
        System.out.println(s);
    }

    // Reads commands in given file and prints results
    public static void readCommands(String filepath) {
    }

    public static void main (String[] args) {
        int[] arr1 = {3,6,1,3,-9,7,18,2,2,0};
        System.out.print("insertionSort: ");
        insertionSort(arr1);
        printArray(arr1);

        int[] arr2 = {4,3,0,-1,3,8,6,2,1};
        System.out.print("bubbleSort: ");
        bubbleSort(arr2);
        printArray(arr2);

        int[] arr3 = {9,0,8,-7,3,6,2,-1,0};
        System.out.print("shellSort: ");
        shellSort(arr3);
        printArray(arr3);
        
        int[] arr4 = {18,3,4,1,0,7,-2,1,6};
        System.out.print("quickSort: ");
        quickSort(arr4);
        printArray(arr4);
        
        int[] arr5 = {5,-4,2,3,9,7,8,1,0,1};
        System.out.print("mergeSort: ");
        mergeSort(arr5);
        printArray(arr5);
        
        int[] arr6 = {1,0,-3,6,9,8,7,12,0,-5};
        System.out.print("upgradedQuickSort: ");
        upgradedQuickSort(arr6, (int)Math.log(arr6.length), 3);
        printArray(arr6);
    }
}
