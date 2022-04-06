package cs235.java;

public class StudentImpl extends PersonImpl implements Student{

public StudentImpl(String did,String nm, String mjr, double grade)
{super (did, nm); major = mjr; gpa = grade;}
 public String getMajor()
{
	return major;
}
public void setMajor(String newmajor)
{
	major = newmajor;	
}
public double getGPA()
{
	return gpa;
}
public void setGPA(double newgpa)
{
	final int delimeter = 4;
	if (newgpa <0 || newgpa > delimeter)
	{
		throw new IllegalArgumentException();
	}
	gpa = newgpa;	
}
public String toString() {
	return "student" + "\n"+ getID() +"\n"+getName() + "\n"+ major + "\n" +gpa + "\n";
}

private double gpa;
private String major;
}
