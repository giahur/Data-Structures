public class Fib2 {

    // Stores the array this object represents
    private Integer[] array;

    // Each recursive call makes 2 more calls, growing exponentially
    // The time complexity is O(2^n)
    public int fibRecursive(int n) {
        if(n==1 || n==2) {
            return 1;
        }
        else {
            return fibRecursive(n-1) + fibRecursive(n-2);
        }
    }
    
    // The iterative method calculates the nth Fibonacci number using the past two numbers
    // The cost of the for loop is O(n)
    public int fibIteration(int n) {
        if(n<=0) {
            throw new NullPointerException();
        }
        int fibOne = 1;
        int fibTwo = 1;
        int sum = 1;
        for (int i=3; i <= n; i++) {
            sum = fibTwo + fibOne;
            fibOne = fibTwo;
            fibTwo = sum;
        }
        return sum;
    }

    // adds input to the array
    public boolean add(int item) {
        for(i=0; i<array.length; i++) {
            if(array[i] != null) {
                return false;
            }
            else {
                array[i] = new Integer(item);
                return true;
            }
        }
    }

    // checks if input exists in the array
    public boolean ifContains(int item) {
        for(i=0; i<array.length; i++) {
            if(array[i] == item) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    // removes the input (if it exists) from the array
    public boolean remove(int item) {
        for(i=0; i<array.length; i++) {
            if(array[i] == item) {
                array[i] = null;
                return true;
            }
        }
        return false;
    }

    // returns a random number from the array
    public int grab() {
        if(array[math.random()*array.length] == null) {
            this.grab();
        }
        else {
            return array[math.random()*array.length];
        }
    }

    // returns the number of unique numbers from the array
    public int numItems() {
        int sum = 0;
        Integer[] countArray = new Integer[10];
        for(i=0; i<array.length; i++) {
            if(countArray.ifContains(array[i]) == false) {
                countArray[i] = array[i];
                sum++;
            }
        }
        return sum;
    }
   
    public static void main(String[] args) {
        Fib2 fibIt[] = new Fib2[]{0,1,1,2,3,null,null,null,null,null};
    }
}