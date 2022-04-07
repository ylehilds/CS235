
/**
 * NOTE: This is an optional interface that you will only need to
 * implement if you want to use the AvlGUI.
 */

package cs235.avl;

public interface Tree extends java.util.Set {

    /**
     * Returns the root node for this tree
     * 
     * @return the root node for this tree.
     */
    BinaryTreeNode getRootNode();

}

