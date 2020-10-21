// --== CS400 File Header Information ==--
// Name: Arun Balaji
// Email: abalaji7@wisc.edu
// Team: nf
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: none

import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes within a binary search tree.  You can use this class' insert
 * method to build a binary search tree, and its toString method to display
 * the level order (breadth first) traversal of values in that tree.
 */
public class RBTMovie<T extends Comparable<T>> {
    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always be maintained.
     */
    protected static class Node<Movie> {
        public Movie data;
        public boolean isBlack;
        public Node<Movie> parent; // null for root node
        public Node<Movie> leftChild; 
        public Node<Movie> rightChild; 
        public Node(Movie data) { this.data = data; this.isBlack = false; }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<Movie>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<Movie> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString() + " " + next.isBlack;
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
        
    }
    protected Node<Movie> root; // reference to root node of tree, null when empty
    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree.  After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(Movie data) throws NullPointerException,
                                      IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");
        Node<Movie> newNode = new Node<Movie>(data);
        if(root == null) { root = newNode; } // add first node to an empty tree
        else insertHelper(newNode,root); // recursively insert into subtree
        root.isBlack = true;
    }
    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
    * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references (as defined by Comparable.compareTo())
     */
    private void insertHelper(Node<Movie> newNode, Node<Movie> subtree) {
        int compare = (int) newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) throw new IllegalArgumentException(
            "This RedBlackTree already contains that value.");
        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.leftChild);
        }
        // store newNode within the right subtree of subtree
        else { 
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.rightChild);
        }
    }
    
    private void enforceRBTreePropertiesAfterInsert(Node<Movie> newNode)
    {
 
    	//ensure that newNode, parent, grandparent, and uncle is not null
    	if(newNode == null || newNode.parent == null || newNode.parent.parent == null || newNode.parent.parent.rightChild == null 
    			|| newNode.parent.parent.leftChild == null) 
    	{ 
    		return;
    	}
    	if(newNode.parent.isBlack == false) //ensures newNode has red parent (RBT property violation)
    	{
    		//ensures newNode is a leftChild, so uncle would be rightChild of grandparent
    		if(newNode.parent.isLeftChild()) 
    		{
    			//checks red uncle
    			if(newNode.parent.parent.rightChild.isBlack == false) 
    			{ 
    				//recoloring newNode's parent, grandparent, and uncle
    				newNode.parent.parent.rightChild.isBlack = true;
    				newNode.parent.parent.isBlack = false;
    				newNode.parent.isBlack = true;
    				//move up the tree to fix any other violations
    				enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
    			}
    			else 
    			{ //black uncle cases
    				if(newNode.isLeftChild()) //(line case) where the parent is a leftChild and newNode is also a leftChild
    				{ 
    					//rotation
    					rotate(newNode.parent, newNode.parent.parent);
    					//recoloring
    					newNode.parent.isBlack = true;
    					newNode.parent.rightChild.isBlack = false;
    				}
    				else 
    				{ //(triangle case) where parent is leftChild and newNode is rightChild
    					rotate(newNode, newNode.parent); //rotation
    					enforceRBTreePropertiesAfterInsert(newNode.leftChild);
    				}
    			}
    		}
    		else //repeats the logic above, but newNode's parent is a rightChild, so uncle would be leftChild of newNode's grandparent
    		{
    			if(newNode.parent.parent.leftChild.isBlack == false) 
    			{ //red uncle case
    				newNode.parent.parent.leftChild.isBlack = true;
    				newNode.parent.parent.isBlack = false;
    				newNode.parent.isBlack = true;
    				enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
    			}
    			else 
    			{ //Tests for case 2 and 3
    				if(newNode.isLeftChild()) 
    				{ //black uncle triangle case
    					rotate(newNode, newNode.parent);
    					enforceRBTreePropertiesAfterInsert(newNode.rightChild); 
    				}
    				else 
    				{
    					//black uncle line case
    					rotate(newNode.parent, newNode.parent.parent); 
    					newNode.parent.isBlack = true;
    					newNode.parent.leftChild.isBlack = true;
    				}
    			}
    		}
    	}
    	else 
    	{
    		return;
    	}

    }
    	
    	 


    	
    
    /**
     * This method performs a level order traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { return root.toString(); }
    /**
     * Performs the rotation operation on the provided nodes within this BST.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation (sometimes called a left-right 
     * rotation).  When the provided child is a rightChild of the provided 
     * parent, this method will perform a left rotation (sometimes called a 
     * right-left rotation).  When the provided nodes are not related in one 
     * of these ways, this method will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<Movie> child, Node<Movie> parent) throws IllegalArgumentException {
    	
    	Node<Movie> tempNode;
		if(!(child == null || parent == null)){
			if(child.isLeftChild()){
				tempNode = child.rightChild;
				child.rightChild = parent;
				parent.parent = child;
				child.parent = parent.parent;
				parent.leftChild = tempNode;
				if(root == parent){
					root = child;
				}
			}
			else{
				tempNode = child.leftChild;
				child.leftChild = parent;
				parent.parent = child;
				child.parent = parent.parent;
				parent.rightChild = tempNode;
				if(root == parent){
					root = child;
				}
			}
		}
		else {
			throw new IllegalArgumentException();
		}
    }

    }