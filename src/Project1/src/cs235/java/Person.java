

package cs235.java;

/**
 * The Person interface represents a person who is affiliated with a university
 */
public interface Person {
    
    /**
     * Returns the person's ID
     * 
     * @return the person's ID.
     */
    String getID();
    
    /**
     * Sets the person's ID
     * 
     * @param id the new value for the person's ID.
     *      This may be any non-null String
     * @throws IllegalArgumentException if id is null
     */
    void setID(String id);
    
    /**
     * Returns the person's name
     * 
     * @return the person's name.
     */
    String getName();
    
    /**
     * Sets the person's name
     * 
     * @param name the new value for the person's name.
     *      This may be any non-null String
     * @throws IllegalArgumentException if name is null
     */
    void setName(String name);
}

