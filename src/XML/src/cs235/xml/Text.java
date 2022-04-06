
package cs235.xml;


/**
 * The Text interface represents a text node in an XML document tree.
 */
public interface Text extends Node {

    /**
     * @return the text string stored in this node
     */
    String getData();
    
    /**
     * Sets the text string stored in this node.
     * 
     * @param data the text string to be stored in this node
     * @throws IllegalArgumentException if data is null
     */
    void setData(String data);
}
