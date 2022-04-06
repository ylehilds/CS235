

package cs235.java;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PersonIO {
    
    /**
     * Saves an array of Person objects to a file
     * 
     * @param people the array of Person objects to be stored in the file
     * @param fileName the name of the file in which to store the Person objects
     * @return true if the Person objects were successfully written to the file,
     *     or false if an error occurred
     * @throws IllegalArgumentException if people and/or fileName is null
     */
    public static boolean save(Person[] people, String fileName) {
	// ***** your code goes here *****
    	if (fileName == null||people==null)
    	{
    		throw new IllegalArgumentException();
    	}
    	//fileName = "C:\\cs235\\java\\dataBaseWritten.txt";
		PrintWriter printer = null;
		try 
		{
		printer = new PrintWriter(new FileWriter(fileName));
		printer.println(people.length);
		for (int i=0;i<people.length; i++) {
				printer.println(people[i].toString());
		 }
		
		 printer.close();
		}
				catch (IOException e) {
				    System.out.println("invalid number");
				    return false;
				}

		 return true;  
	}
     
    

    /**
     * Loads the Person objects stored in the specified file and returns them in an array
     * 
     * @param fileName the name of the file to be loaded
     * @return an array of Person objects in the same order that they appear in the file.
     *      If the file cannot be opened or the contents of the file are invalid, null is returned
     * @throws FileNotFoundException 
     * @throws IllegalArgumentException if fileName is null
     */
    public static Person[] load(String fileName) {
    	if (fileName == null)
    	{
    		throw new IllegalArgumentException();
    	}
		int number = 0;
		String type;
		PersonImpl[] personArray =null;
		

		try {
			
		Scanner in = new Scanner(new FileReader(fileName));
		number = Integer.parseInt(in.nextLine());
		personArray = new PersonImpl[number];
		
//		String testing = in.next();
	//	while (testing != null)
		//{
		//System.out.println(testing);
		//testing = in.nextLine();
		//}
		
		
		
		
		
		
		
		
		
		
		
			
		for (int i=0;i<number;i++)
		{
		type = in.nextLine();
		if (type.equals("student"))
		{
		StudentImpl studt =null;
		studt=new StudentImpl(in.nextLine(),in.nextLine(),in.nextLine(),
				Double.parseDouble(in.nextLine()));
		personArray[i] = studt;
		}
		else if (type.equals("employee" ))
		{
		EmployeeImpl employ = new EmployeeImpl(in.nextLine(), in.nextLine(), in.nextLine());
		personArray[i] = employ;
		
		}
		in.nextLine();
		//System.out.println(personArray[i]);
		}
		
		in.close();
		} catch (FileNotFoundException e)
		{ 
		System.out.println("Input/output error " + e);
		return null;
		}
	return personArray;	
    }
}
