/** Integer nodes with value and pointer to next node
  * @author Gia Hur
  * worked with Chris Wu
  **/

public class LLIntNode {
  
  private int value;
  private LLIntNode next;
  
  // constructor requiring each node to have a value and a pointer to the next node
  public LLIntNode(int value, LLIntNode next) {
    this.value = value;
    this.next = next;
  }
  
  public int getValue() {
    return value;
  }
  
  public LLIntNode getNext() {
    return next;
  }
  
  public void setNext(LLIntNode next) {
    this.next = next;
  }
  
}