
package cs235.xml;


/**
 * The Element interface represents an element in an XML document tree.
 */
public interface Element extends Node {

    /**
     * @return the name of this element
     */
    String getTagName();
    
    /**
     * Sets the name of this element.
     * 
     * @param the new name of the element
     * @throws IllegalArgumentException if tagName is null
     */
    void setTagName(String tagName);

    /**
     * Returns the value of the specified attribute.
     * 
     * @param name the name of the attribute whose value is being requested
     * @return the value of the specified attribute, or null if the element
     *          has no attribute with that name
     * @throws IllegalArgumentException if name is null
     */
    String getAttributeValue(String name);
    
    /**
     * @return the number of attributes that this element has
     */
    int getAttributeCount();
    
    /**
     * Returns the name of the ith attribute (indices start at 0)
     * 
     * @param i the index of the attribute whose name is being requested
     * @return the name of the ith attribute (indices start at 0)
     * @throws IllegalArgumentException if i is an invalid index
     */
    String getAttributeName(int i);
    
    /**
     * Returns the value of the ith attribute (indices start at 0)
     * 
     * @param i the index of the attribute whose value is being requested
     * @return the value of the ith attribute (indices start at 0)
     * @throws IllegalArgumentException if i is an invalid index
     */
    String getAttributeValue(int i);
    
    /**
     * Sets the value of the specified attribute on this element.
     * 
     * @param name the name of the attribute that is being set
     * @param value the value of the attribute that is being set
     * @throws IllegalArgumentException if name is null
     * @throws IllegalArgumentException if value is null
     */
    void setAttribute(String name, String value);
}
