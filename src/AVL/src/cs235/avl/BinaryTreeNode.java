
/**
 * NOTE: This is an optional interface that you will only need to
 * implement if you want to use the AvlGUI.
 */

package cs235.avl;

public interface BinaryTreeNode {

    /**
     * Returns the data that is stored in this node
     * 
     * @return the data that is stored in this node.
     */
    Object getData();

    /**
     * Returns the left child of this node or null if it doesn't have one.
     * 
     * @return the left child of this node or null if it doesn't have one.
     */
    BinaryTreeNode getLeftChild();

    /**
     * Returns the right child of this node or null if it doesn't have one.
     * 
     * @return the right child of this node or null if it doesn't have one.
     */
    BinaryTreeNode getRightChild();

    /**
     * Returns the height of this node. The height is the number of edges
     * from this node to this nodes farthest child.
     */
    int getHeight();

}

