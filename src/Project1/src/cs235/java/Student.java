

package cs235.java;

/**
 * The Student interface represents a university student
 */
public interface Student extends Person {
    
    /**
     * Returns the student's major
     * 
     * @return the student's major.
     */
    String getMajor();
    
    /**
     * Sets the student's major
     * 
     * @param major the new value for the student's major.
     *      This may be any non-null String
     * @throws IllegalArgumentException if major is null
     */
    void setMajor(String major);
    
    /**
     * Returns the student's GPA
     * 
     * @return the student's GPA.
     */
    double getGPA();
    
    /**
     * Sets the student's GPA
     * 
     * @param gpa the new value for the student's GPA.
     *       This may be any double in the range 0.0 to 4.0
     * @throws IllegalArgumentException if gpa is not in the range 0.0 to 4.0
     */
    void setGPA(double gpa);
}

