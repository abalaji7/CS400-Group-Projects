  
// --== CS400 File Header Information ==--
// Name: Matthew Zajac
// Email: mjzajac@wisc.edu
// Team: NF
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * An extension of the RedBlackTree class that adds methods to print out the tree, either in order,
 * or in reverse order.
 */
public class RedBlackTreeExtension<T extends Comparable<T>> extends RBTMovie<T> {

  /**
   * Calls the recursive method to start the in order traversal starting at the root
   */
  public void inOrderTraversal() {
    checkNodeInOrder((Node<T>) root);
  }
  
  /**
   * Calls the recursive method to start the reverse order traversal starting at the root
   */
  public void reverseOrderTraversal() {
    checkNodeReverseOrder((Node<T>) root);
  }
  
  /**
   * If the current node is not null, it first checks the left subtree, then prints out the node's
   * value, then checks the right subtree, printing the tree in order.
   */
  public void checkNodeInOrder(Node<T> node) {
    if (node == null) {
      return;
    }
    checkNodeInOrder(node.leftChild);
    System.out.println(node.data.toString());
    checkNodeInOrder(node.rightChild);
  }
  
  /**
   * If the current node is not null, it first checks the right subtree, then prints out the node's
   * value, then checks the left subtree, printing the tree in reverse order.
   */
  public void checkNodeReverseOrder(Node<T> node) {
    if (node == null) {
      return;
    }
    checkNodeReverseOrder(node.rightChild);
    System.out.println(node.data.toString());
    checkNodeReverseOrder(node.leftChild);
  }
}