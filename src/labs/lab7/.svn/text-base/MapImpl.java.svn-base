package cs235.hash;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
 

public class MapImpl implements Map{
//implement a method called find()  it will work just like the contains method,
//but it will return what you are looking for. 
//create a new class called Pair where it will have the key and value and give it to put()
private SetImpl MapSet = new SetImpl();

public Object get(Object key) {
	Pair p = new Pair(key, null);
	if (MapSet.find(p)!=null)
	{
		Pair found = (Pair)MapSet.find(p);
		return found.valueGetter();
	}
	// TODO Auto-generated method stub
	return null;
}

public Object remove(Object key) {
	Object ret=null;
	if (get(key) !=null)
	{
		ret = get(key);
	}
	MapSet.remove(new Pair (key, ret));
	return ret;	
	// TODO Auto-generated method stub
}

public Object put(Object key, Object value) {
	Object ret = get(key);
	Pair lehi = new Pair(key, value);
	//System.out.println(lehi.keyGetter() + " " + lehi.valueGetter());
	if (MapSet.contains(lehi))
	{	
		MapSet.remove(lehi);	
	}
	MapSet.add(lehi);
	return ret;
}

public void clear() { // just use the clearn from setImpl
		// TODO Auto-generated method stub
		MapSet.clear();
	}

	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}


	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}


	public Set entrySet() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}




	public boolean isEmpty() { // just use it from the setImpl
		
		// TODO Auto-generated method stub
		return MapSet.isEmpty();
	}


	public Set keySet() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}




	public void putAll(Map arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}


	public int size() { // just use it from the setImpl
		
		// TODO Auto-generated method stub
		return MapSet.size();
	}


	public Collection values() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
