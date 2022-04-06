package cs235.java;


public class PersonImpl implements Person, Comparable{

private String name;
private String id;
public PersonImpl(String cid, String cname)
{
	name = cname;
	id = cid;
}
public String getID()
{
	return id;
}

public void setID(String newid) 
{
	if (newid==null)
	{
		throw new IllegalArgumentException();
	}
	id = newid;
}

public String getName()
{
	return name;
}

public void setName(String newname)
{
	if (newname==null)
	{
		throw new IllegalArgumentException();
	}
	name = newname;
}
public boolean equals (Object equality) {
	if (equality instanceof Person) {
	    Person w = (Person) equality;
	    return id.equals(w.getID());
	}

	return false;
}

public int compareTo(Object comp) {
	Person x = (Person)comp;
	return id.compareTo(x.getID());
    }
}
