package cs235.java;

public class EmployeeImpl extends PersonImpl implements Employee{

public EmployeeImpl(String mid,
 String nme, String offci)
	{
	super(mid, nme); office = offci;

	}
public String getOffice()
{
	return office;
}
public void setOffice(String newoffice)
{
	if (newoffice == null)
	{
		throw new IllegalArgumentException();
	}
	office = newoffice;
}
public String toString() {
	return "employee" + "\n"+getID() +"\n"+getName() + "\n"+office + "\n";
}

private String office;
}
