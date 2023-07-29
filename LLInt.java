/** Singly linked list of integers
  * @author Gia Hur
  * worked with Chris Wu
  **/

public class LLInt {
  
  // variable containing head of linked list
  private LLIntNode head;
  
  // constructor requiring head of linked list
  public LLInt(LLIntNode head) {
    this.head = head;
  }
  
  // method to print linked list as a String
  public String toString() {
    String stringLL = "" + head.getValue();
    LLIntNode next = head.getNext();
    while(next != null) {
      stringLL = stringLL + ", " + next.getValue();
      next = next.getNext();
    }
    return stringLL;
  }
  
  // method to reverse order of linked list nodes
  public void reverse() {
    LLIntNode first = head;
    LLIntNode second = head.getNext();
    LLIntNode third = head.getNext().getNext();
    first.setNext(null);
    while(third.getNext() != null) { 
      second.setNext(first);
      first = second;
      second = third;
      third = third.getNext();
    }
    second.setNext(first);
    third.setNext(second);
    head = third;
  }
  
  // main method creates and reverses linked list with example values
  public static void main(String[] args) {
    LLIntNode node1 = new LLIntNode(44, new LLIntNode(37, new LLIntNode(56,new LLIntNode(13,new LLIntNode(8,null)))));
    LLInt list = new LLInt(node1);
    System.out.println(list);
    list.reverse();
    System.out.println(list);
  }
  
}