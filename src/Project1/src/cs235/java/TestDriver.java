

package cs235.java;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class TestDriver {
    public static void main(String[] args) {
	test("people", data);
        System.out.println("Tests Completed Successfully"); 
    }
    static void test(String name, String[][] test) {

        System.out.println("checking people");
        checkPeople(test);
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

        System.out.println("checking toString");
	checkToString(test);
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

        System.out.println("checking equals");
	checkEquals(test);
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

        System.out.println("checking compareTo");
	checkCompareTo(test);
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

        System.out.println("checking save");
	checkSave(name, test);
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

        System.out.println("checking load");
	checkLoad(name, test);
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

	checkExceptions(); 
    }

    final static String[][] data = {
        {"student", "000010", "Bob Brown", "History", "4.0"},
        {"student", "000009", "Kim Black", "Mathematics", "3.9"},
        {"student", "000008", "Ted Green", "Computer Science", "3.8"},
        {"student", "000007", "Bill White", "Computer Engineering", "3.7"},
        {"employee", "000006", "Robert Millett", "106 JSB"},
        {"employee", "000005", "Mark Clement", "3305 TMCB"},
        {"employee", "000004", "Tony Martinez", "3304 TMCB"},
        {"employee", "000003", "Kelly Flanagan", "303 ASB"},
        {"employee", "000002", "Alan Wilkins", "302 ASB"},
        {"employee", "000001", "Merrill J. Bateman", "1 ASB"},
    };

    final static int Type = 0;
    final static int ID = 1;
    final static int Name = 2;
    final static int Major = 3;
    final static int Office = 3;
    final static int GPA = 4;

    static Person[] makePeople(String[][] test) {
        Person[] list = new Person[test.length];
        for (int i = 0; i < test.length; i++)
	    list[i] = makePerson(test[i]);
        return list;
    }
    static Person makePerson(String[] test) {
	Person person = null;
	if (test[Type].equals("student"))
	    person = Factory.createStudent(test[ID], test[Name], test[Major],
					   Double.parseDouble(test[GPA]));
	else if (test[Type].equals("employee"))
	    person = Factory.createEmployee(test[ID], test[Name], test[Office]);
	return person;
    }
    
    static void checkPeople(String[][] test) {
	Person[] people = makePeople(test);
	checkPeople(test, people);
    }
    static void checkPeople(String[][] test, Person[] people) {
        for (int i = 0; i < test.length; i++)
            checkPerson(test[i], people[i]);
    }
    static void checkPerson(String[] test, Person person) {
	checkStrings(test[ID], person.getID());
	checkStrings(test[Name], person.getName());
	if (test[Type].equals("student")) {
            Student student = (Student)person;
	    checkStrings(test[Major], student.getMajor());
	    checkDoubles(Double.parseDouble(test[GPA]), student.getGPA());
        } else if (test[Type].equals("employee")) {
            Employee employee = (Employee)person;
	    checkStrings(test[Office], employee.getOffice());
        }
    }

    static void checkToString(String[][] test) {
	Person[] people = makePeople(test);
        for (int i = 0; i < test.length; i++)
            checkToString(test[i], people[i]);
    }
    static void checkToString(String[] test, Person person) {
	String expected = makeString(test);
	String actual = person.toString();
	checkStrings(expected, actual);
    }
    
    static void checkEquals(String[][] test) {
	Person[] list1 = makePeople(test);
	Person[] list2 = makePeople(test);
        for (int i = 0; i < list1.length; i++) {
	    if (!(list1[i].equals(list1[i]))) {
		System.out.println("people should be equal");
		failed();
	    }
	    if (!(list1[i].equals(list2[i]))) {
		System.out.println("people should be equal");
		failed();
	    }
	    if (i > 0 && list1[i].equals(list1[i-1])) {
		System.out.println("people should not be equal");
		failed();
	    }
	    if (i > 0 && list1[i].equals(list2[i-1])) {
		System.out.println("people should not be equal");
		failed();
	    }
        }
    }
    static void checkCompareTo(String[][] test) {
	Person[] list = makePeople(test);
        java.util.Arrays.sort(list);
	for (int i = 0; i < list.length; i++) {
	    if (java.util.Arrays.binarySearch(list, list[i]) != i) {
		System.out.println("binarySearch found person at wrong place");
		failed();
	    }
	}
	for (int i = 0; i < list.length; i++) {
	    Comparable comp = (Comparable)list[i];
	    if (i > 0 && comp.compareTo(list[i-1]) <= 0) {
		System.out.println("compareTo should be positive");
		failed();
	    }
	    if (comp.compareTo(list[i]) != 0) {
		System.out.println("compareTo should be equal");
		failed();
	    }
	    if (i < list.length-1 && comp.compareTo(list[i+1]) >= 0) {
		System.out.println("compareTo should be negative");
		failed();
	    }
	}
    }

    static void checkSave(String name, String[][] test) {
	Person[] list = makePeople(test);
	PersonIO.save(list, name);
	String expected = makeString(test);
	String actual = readFile(name);
	checkStrings(expected, actual);
    }
    static void checkLoad(String name, String[][] test) {
	String expected = makeString(test);
	writeFile(name, expected);
	Person[] list = PersonIO.load(name);
	if (list == null) {
	    System.out.println("load returned null when reading file: " + name);
	    failed();
	}
	checkPeople(test, list);
    }
    static String readFile(String filename) {
	String s = "";
	try {
	    BufferedReader br = new BufferedReader (new FileReader (filename));
	    String line;
	    while ((line = br.readLine()) != null)
		s += line + "\n";
	    br.close();
	}
	catch (IOException e) {
	    System.out.println("Error reading file: " + filename);
	}
	return s;
    }
    static void writeFile(String filename, String text) {
	try {
	    PrintWriter pw = new PrintWriter (new FileWriter (filename));
	    pw.print(text);
	    pw.close();
	} catch (IOException e) {
	    System.out.println("Error writing file: " + filename);
	}
    }

    static String makeString(String[] test) {
	String s = "";
	for (int i = 0; i < test.length; i++)
	    s += test[i] + "\n";
	return s;
    }
    static String makeString(String[][] test) {
	String s = test.length + "\n";
	for (int i = 0; i < test.length; i++)
	    s += makeString(test[i]) + "\n";
	return s;
    }

    static void checkStrings(String expected, String actual) {
	if (!(expected.equals(actual))) {
	    System.out.println("EXPECTED: " + expected);
	    System.out.println("ACTUAL: " + actual);
	    failed();
	}
    }
    static void checkInts(int expected, int actual) {
	if (expected != actual) {
	    System.out.println("EXPECTED: " + expected);
	    System.out.println("ACTUAL: " + actual);
	    failed();
	}
    }
    static void checkDoubles(double expected, double actual) {
	if (expected != actual) {
	    System.out.println("EXPECTED: " + expected);
	    System.out.println("ACTUAL: " + actual);
	    failed();
	}
    }
    static void checkBooleans(boolean expected, boolean actual) {
	if (expected != actual) {
	    System.out.println("EXPECTED: " + expected);
	    System.out.println("ACTUAL: " + actual);
	    failed();
	}
    }

    static void failed() {
	System.out.println(".\t.\t.\t.\t.\t test FAILED");
	System.exit(1);
    }

    final static double MinGPA = 0.0;
    final static double MaxGPA = 4.0;
    final static double MiddleGPA = 2.5;
    final static double TooBigGPA = 4.1;
    final static double TooSmallGPA = -0.1;

    static void checkExceptions() {
        System.out.println("checking bad filenames");
	Person[] list = new Person[0];
	checkBooleans(false, PersonIO.save(list, "notthere/notthere"));
	list = PersonIO.load("notthere");
	if (list != null) {
	    System.out.println("load should return null when reading file: notthere");
	    failed();
	}
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

        System.out.println("checking students with bad arguments");
        Student student = null;
	checkCreateStudent("", null, "", MinGPA, true);
	checkCreateStudent("", "", null, MinGPA, true);
	checkCreateStudent("", "", "", TooBigGPA, true);
	student = checkCreateStudent("", "", "", MinGPA, false);
        checkDoubles(MinGPA, student.getGPA());
	student = checkCreateStudent("", "", "", MaxGPA, false);
        checkDoubles(MaxGPA, student.getGPA());
	System.out.println(".\t.\t.\t.\t.\t test PASSED");
        
        System.out.println("checking setName with bad arguments");
	checkSetName(student, "Bob", false);
	checkStrings("Bob", student.getName());
	checkSetName(student, null, true);
	checkStrings("Bob", student.getName());
	System.out.println(".\t.\t.\t.\t.\t test PASSED");
        
        System.out.println("checking setGPA with bad arguments");
	checkSetGPA(student, MiddleGPA, false);
        checkDoubles(MiddleGPA, student.getGPA());
	checkSetGPA(student, TooSmallGPA, true);
        checkDoubles(MiddleGPA, student.getGPA());
	System.out.println(".\t.\t.\t.\t.\t test PASSED");

    }

    static Student checkCreateStudent(String id, String name, String major,
				      double gpa, boolean isError) {
        boolean error = false;
        Student student = null;
        try {
            student = Factory.createStudent(id, name, major, gpa);
        }
        catch (IllegalArgumentException e) {
            error = true;
        }
	checkBooleans(isError, error);
	return student;
    }

    static void checkSetName(Student student, String name, boolean isError) {
        boolean error = false;
        try {
            student.setName(name);
        }
        catch (IllegalArgumentException e) {
            error = true;
        }
	checkBooleans(isError, error);
    }

    static void checkSetGPA(Student student, double gpa, boolean isError) {
        boolean error = false;
        try {
            student.setGPA(gpa);
        }
        catch (IllegalArgumentException e) {
            error = true;
        }
	checkBooleans(isError, error);
    }

}

