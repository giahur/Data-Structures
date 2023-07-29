/** Creates a Queue with two modified Stacks
 * @author Gia Hur
 **/

import java.util.*;

public class CustomSQueue {

  private Stack<Integer> stack1;
  private Stack<Integer> stack2;
  private Integer element;
  
  // constructor requiring 2 stacks
  public CustomSQueue(Stack<Integer> stack1, Stack<Integer> stack2) {
    this.stack1 = stack1;
    this.stack2 = stack2;
  }
  
  // method emulating Queue add method with Stacks
  public boolean add(Integer element) {
    stack1.push(element);
    return true;
  }
  
  // method emulating Queue poll method with Stacks
  public Integer poll() {
    while(stack1.empty() == false) {
      stack2.push(stack1.pop());
    }
    if(stack2.empty() == false) {
      return stack2.pop();
    }
    else {
      return null;
    }
  }
  
  // main method demonstrates Stacks modified to act as Queues
  public static void main(String[] args) {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    CustomSQueue modQueue = new CustomSQueue(stack1,stack2);
    modQueue.add(1);
    modQueue.add(2);
    modQueue.add(3);
    System.out.println(modQueue.poll());
    System.out.println(modQueue.poll());
    System.out.println(modQueue.poll());
    System.out.println(modQueue.poll());
  }
  
}