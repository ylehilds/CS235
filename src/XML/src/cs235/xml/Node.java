
package cs235.xml;


/**
 * The Node interface is the base class for all types of XML nodes.
 * It provides operations for managing and traversing the tree structure.
 * The Node interface is extended by the Document, Element, and Text interfaces.
 */
public interface Node {

    /**
     * These constants represent the different kinds of XML nodes.
     * 
     * DOCUMENT_NODE - a node that represents an XML document (must be the root)
     * ELEMENT_NODE - a node that represents an XML element
     * TEXT_NODE - a node that represents text content
     */
    int DOCUMENT_NODE = 1;
    int ELEMENT_NODE = 2;
    int TEXT_NODE = 3;

    /**
     * @return the type of this node (DOCUMENT_NODE, ELEMENT_NODE, or TEXT_NODE)
     */
    int getType();

    /**
     * @return the parent node of this node, or null if it has no parent
     */
    Node getParent();

    /**
     * @return the first child of this node, or null if it has no children
     */
    Node getFirstChild();

    /**
     * @return the last child of this node, or null if it has no children
     */
    Node getLastChild();

    /**
     * @return the next sibling of this node, or null if it has no next sibling
     */
    Node getNextSibling();

    /**
     * @return the previous sibling of this node, or null if it has no previous sibling
     */
    Node getPreviousSibling();

    /**
     * Inserts a new child node under the target node.
     * 
     * @param newChild the new child node to be inserted
     * @param child the child node before which the new node should be inserted
     * @throws IllegalArgumentException if newChild is null
     * @throws IllegalArgumentException if child is null
     * @throws IllegalArgumentException if child is not a child of the target node
     */
    void insertChildBefore(Node newChild, Node child);
    
    /**
     * Appends a child to the target node's list of children.
     * 
     * @param newChild the new child node to be appended
     * @throws IllegalArgumentException if newChild is null
     */
    void appendChild(Node newChild);
    
    /**
     * Removes a child from the target node's list of children.
     * 
     * @param child the child to be removed
     * @throws IllegalArgumentException if child is null
     * @throws IllegalArgumentException if child is not a child of the target node
     */
    void removeChild(Node child);
    
    /**
     * Replaces an existing child node with a new node.
     * 
     * @param newChild the new node that is replacing the old node
     * @param oldChild the old node that is being replaced
     * @throws IllegalArgumentException if newChild is null
     * @throws IllegalArgumentException if oldChild is null
     * @throws IllegalArgumentException if oldChild is not a child of the target node
     */
    void replaceChild(Node newChild, Node oldChild);
}
