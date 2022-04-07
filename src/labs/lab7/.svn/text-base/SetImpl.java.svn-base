package cs235.hash;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class SetImpl implements Set{
private int size;
private final int initialTableSize= 101;
private int tableSize = initialTableSize;
private List[] hashTable = new ArrayList[tableSize];

public boolean add(Object e) {
	
		if (size==tableSize)
		{
			rehash();
		}
		int index = Math.abs(e.hashCode() % tableSize);
		if(hashTable[index]==null)
			hashTable[index]=new ArrayList();
		if (hashTable[index].contains(e))
		{
			return false;
		}
		hashTable[index].add(e);
		//System.out.println(hashTable[index] + "at index " + index);
		size++;
		return true;
	}
public void rehash()
{
	int index=0;
	Object temp1;
	
	tableSize = 2*tableSize +1;
	List[] temp = new ArrayList[tableSize];
	
	IteratorImpl iter= new IteratorImpl();
	while (iter.hasNext())
	{
		temp1=iter.next();
		index = Math.abs(temp1.hashCode() % tableSize);
		if (temp[index]==null)
		{
			temp[index]=new ArrayList();
		}
		temp[index].add(temp1);
		//System.out.println(temp1+ " "+index);
	}
	hashTable = temp;
	//System.out.println(hashTable.length);
}
	
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	
	public void clear() {
		//System.out.println("I was called ");
		size=0;
		tableSize = initialTableSize;
		hashTable =  new ArrayList[initialTableSize];
		
				
	}
	public boolean contains(Object o) {
		int index = Math.abs(o.hashCode() % tableSize);
		if (hashTable[index] ==null)
		{
			return false;
		}
		if (hashTable[index].contains(o))
		{
			return true;
		}
		return false;
	}
	
	public Object find(Object o) {
		int index = Math.abs(o.hashCode() % tableSize);		
		if (hashTable[index] ==null)
		{
			return null;
		}
		if (hashTable[index].contains(o))
		{
			int pos = hashTable[index].indexOf(o);
			return hashTable[index].get(pos);
		}
		return null;
	}
	
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	
	public boolean isEmpty() {
		if (size==0)
		{
			return true;
		}
		// TODO Auto-generated method stub
		return false;
		
	}
	
	
	public Iterator iterator() {
		IteratorImpl iter = new IteratorImpl();
		return iter;
	}
	public class IteratorImpl implements Iterator{
		private int i=0;
		private int j=0;
		private int track=0;
		private List current;
		public boolean hasNext() {
			if (track < size)
			{	
			return true;
			}
			return false;
		}
		public Object next() {
			
			if (!this.hasNext())
			{
				return null;
			}
			if (current==null)
			{ 
				while(hashTable[i]==null || hashTable[i].isEmpty())
				{
					i++;	
				}
				current = (ArrayList) hashTable[i];
				j=0; 
			}
			if (j<current.size())
			{
				track++;
				return current.get(j++);
			}
			i++;
			while(hashTable[i]==null || hashTable[i].isEmpty())
			{
				i++;
			}
			current = (ArrayList) hashTable[i];
			track++;
			j=0;
			return current.get(j++);		
		}
		public void remove() {
			throw new UnsupportedOperationException();	
		}
	}
	public boolean remove(Object o) {	
		int index = Math.abs(o.hashCode() % tableSize);
		if (hashTable[index]!=null)
		{
			if (hashTable[index].contains(o))
			{
				hashTable[index].remove(o);
				size--;
				return true;				
			}
		}
		return false;
	}

	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public Object[] toArray() {
		int i=0;
		Object[] array = new Object[this.size];
		IteratorImpl iter = new IteratorImpl();
		while (iter.hasNext())
		{
			array[i]=iter.next();
			i++;
		}
		return array;
	
	}
	
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}
