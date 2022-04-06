

package cs235.java;

public class Factory {
    
    /**
     * Creates, initializes, and returns a new object that implements the Student interface
     * 
     * @param id the student's ID.  This may be any non-null String
     * @param name the student's name.  This may be any non-null String
     * @param major the student's major  This may be any non-null String
     * @param gpa the student's GPA.  This may be any double in the range 0.0 to 4.0
     * @return a reference to the new Student object
     * @throws IllegalArgumentException if any of the input parameters is invalid
     */  
    public static Student createStudent(    String id,
                                            String name,
                                            String major,
                                            double gpa  ) {
	// ***** your code goes here *****
    	final double delimeter = 4;
    	if (id==null|| name==null||major==null||gpa<0 ||gpa>delimeter)
    	{
    		throw new IllegalArgumentException();
    	}
    	
	StudentImpl lehi = new StudentImpl (id, name, major, gpa);
	return lehi;
    }
    
    /**
     * Creates, initializes, and returns a new object that implements the Employee interface
     * 
     * @param id the employee's ID.  This may be any non-null String
     * @param name the employee's name.  This may be any non-null String
     * @param office the employee's office address.  This may be any non-null String
     * @return a reference to the new Employee object
     * @throws IllegalArgumentException if any of the input parameters is invalid
     */  
    public static Employee createEmployee(    String id,
					      String name,
					      String office  ) {
	// ***** your code goes here *****
    	if (id==null|| name==null||office==null)
    	{
    		throw new IllegalArgumentException();
    	}
    	EmployeeImpl sttenio = new EmployeeImpl(id, name, office);
    return sttenio;
    }

}

