

package cs235.java;

/**
 * The Employee interface represents a university employee
 */
public interface Employee extends Person {

    /**
     * Returns the employee's office address
     * 
     * @return the employee's office address.
     */
    String getOffice();
    
    /**
     * Sets the employee's office address
     * 
     * @param office the new value for the employee's office address.
     *      This may be any non-null String
     * @throws IllegalArgumentException if office is null
     */
    void setOffice(String office);
}

