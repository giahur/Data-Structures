public class Fib1 {

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

    public static void main(String[] args) {
        // The recursive method is less efficient
        // Fib1 fibRec = new Fib1();
        // System.out.println(a.fibRecursive(10));
        Fib1 fibIt = new Fib1();
        System.out.println(fibIt.fibIteration(10));
    }
}