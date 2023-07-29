/** Creates a Stack with a modified Queue
 * @author Gia Hur
 **/

import java.util.*;

public class CustomQStack {
  
  private Queue<Integer> queue;
  private Integer element;
  private Integer size = 0;
  
  public CustomQStack(Queue<Integer> queue) {
    this.queue = queue;
  }

  // method emulating Stack empty method using Queue    
  public boolean empty() {
    if(queue.peek() == null) {
      return true;
    }
    else {
      return false;
    }
  }
  
  // method emulating Stack push method using Queue  
  public Integer push(Integer element) {
    queue.add(element);
    size++;
    return element;
  }
  
  // method emulating Stack pop method using Queue
  public Integer pop() {
    int i = 0;
    while(i < size - 1) {
      queue.add(queue.poll());
      i++;
    }
    size--;
    return queue.poll();
  }
  
  // main method demonstrates Queue modified to act as Stack
  public static void main(String[] args) {
    Queue<Integer> queue = new LinkedList<Integer>();
    CustomQStack stack = new CustomQStack(queue);
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
  }
  
}