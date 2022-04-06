

package cs235.avl;

public class TreeFactory {

    /**
     * This method is an optional method that you only need to implement if you
     * are going to use the GUI as a visual aid to see what your avl tree looks
     * like as you add/remove nodes from it.
     *
     * Creates and returns an object that implements the Tree interface.
     *
     * @return A new object that implements the Tree interface
     */
    public static Tree getTree()
    {
    	TreeImpl tree = new TreeImpl();
    	return tree;
    }

    /**
     * This method is an optional method that you only need to implement if you
     * want to use the GUI and you want support for undo and redo
     * If you don't need undo support, just leave the method returning null.
     *
     * Copies a tree by copying each node in the tree.
     * The new tree doesn't share any nodes with the old tree.
     *
     * @return a new tree that is a copy of t
     */
    public static Tree copyTree(Tree t)
    {
	return null;
    }

}

