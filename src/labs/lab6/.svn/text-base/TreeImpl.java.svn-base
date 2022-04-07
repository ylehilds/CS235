package cs235.avl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;



public class TreeImpl implements Tree {

private BinaryTreeNodeImpl root=null;
private int size;
	/**
	 * NOTE: This is an optional interface that you will only need to
	 * implement if you want to use the AvlGUI.
	 */
	    /**
	     * Returns the root node for this tree
	     * 
	     * @return the root node for this tree.
	     */
	    public BinaryTreeNode getRootNode()
	    {
	    	return  root;
	    }
		public boolean add(Object o) {
		if (!contains(o))//(contains(o)==false)
		{
			root = addrec(o, root);
			size++;
			return true;
		}
			// TODO Auto-generated method stub
			return false;
		}
		
		public BinaryTreeNodeImpl addrec(Object o, BinaryTreeNodeImpl n)
		{
			if (n==null)
			{
				n = new BinaryTreeNodeImpl(o);
			}
			if ( n.data.compareTo(o)<0)
			{
				n.RightChild = addrec(o,n.RightChild);
			}
			if ( n.data.compareTo(o)>0)
			{
				n.LeftChild = addrec(o,n.LeftChild);
			}
			n = balance (n);
			n.updateHeight();
			
			return n;
		}
		public BinaryTreeNodeImpl balance(BinaryTreeNodeImpl n)
		{

			//Calculating the heights of the nodes
			int LC;
			int RC;
			
			if (n.LeftChild==null)
			{
				LC=-1;
			} else LC = n.LeftChild.height;
						
			if (n.RightChild==null)
			{
				RC=-1;
			} else RC = n.RightChild.height;
						
			if (RC-LC > 1)
			{
				doubleRotateLeft(n);
				n =  rotateLeft(n); 
			}
			if (LC-RC > 1)
			{
  				doubleRotateRight(n);
				n = rotateRight(n);
			}
	
			if (n.LeftChild!=null)
			{
				n.LeftChild.updateHeight();
			}
			if (n.RightChild!=null)
			{
				n.RightChild.updateHeight();
			}
			n.updateHeight();
			return n;
		}
		public void doubleRotateRight (BinaryTreeNodeImpl n)
		{
			int LL;
			int LR;
			if (n.LeftChild.LeftChild == null)
			{
				LL=-1;
			} else LL=n.LeftChild.LeftChild.height;
						
			if (n.LeftChild.RightChild==null)
			{
				LR=-1;
			} else LR=n.LeftChild.RightChild.height;
			if (LR > LL)
			{
				n.LeftChild = rotateLeft (n.LeftChild);
			}
			//if (left.right > left.left)
			//n.right = rotateLeft(left)

		}
		public void doubleRotateLeft(BinaryTreeNodeImpl n)
		{
			int RL;
			int RR;
			if (n.RightChild.RightChild==null)
			{
				RR=-1;
			} else RR=n.RightChild.RightChild.height;
						
			if (n.RightChild.LeftChild==null)
			{
				RL=-1;
			} else RL = n.RightChild.LeftChild.height;
			if (RL > RR)
			//if (right.left > right.right)
			{
				n.RightChild = rotateRight (n.RightChild);
			}
		}
		public BinaryTreeNodeImpl rotateLeft(BinaryTreeNodeImpl n)
		{
			
			//System.out.println("rotateLeft was called!");
			BinaryTreeNodeImpl k = n.RightChild;
			n.RightChild = k.LeftChild;
			k.LeftChild=n;
			return k;
		}
		public BinaryTreeNodeImpl rotateRight(BinaryTreeNodeImpl n)
		{
			//System.out.println("rotateRight was called!");
			BinaryTreeNodeImpl k = n.LeftChild;
			n.LeftChild = k.RightChild;
			k.RightChild=n;
			return k;
		}
		public boolean addAll(Collection c) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
		public void clear() {
			root = null;
			size=0;
			// TODO Auto-generated method stub
			
		}
		
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return containsrec(o, root);
		}
		public boolean containsrec(Object o, BinaryTreeNodeImpl n)
		{
			if (n==null)
			{
				return false;
			}
			if (n.data.compareTo(o)==0)
			{
				return true;
			}
			else if ( n.data.compareTo(o)<0)
			{
				return containsrec(o,n.RightChild);
			}
			else if ( n.data.compareTo(o)>0)
			{
				return containsrec(o,n.LeftChild);
			}
				return false;
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
			IteratorImpl iter = new IteratorImpl(root);
			return iter;
		}
		
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			int tempSize = size;
			if (contains(o))
			{
				root = removerec(o, root);
				size--;
			}
			return tempSize > size;
			
		}
		
		public BinaryTreeNodeImpl removerec(Object o, BinaryTreeNodeImpl n)
		{
			
			nullCheck(n);
			
			if (n.data.compareTo(o)<0)
			{
				n.RightChild =  removerec(o,n.RightChild);
			}
			else if (n.data.compareTo(o)>0)
			{
				n.LeftChild =  removerec(o,n.LeftChild);
			}
			
			else if (n.RightChild==null)
			{
				n= n.LeftChild;
			}
			else if (n.LeftChild==null)
			{
				n= n.RightChild;
			}
			else if (n.LeftChild==null && n.RightChild==null)
			{
				n=null;
			}
			else if (n.LeftChild!=null && n.RightChild!=null)
			{
				n.data = (Comparable) findMin(n.RightChild);
				n.RightChild = removerec(n.data, n.RightChild);
			} 
			if (n != null)
			{ 
				n.updateHeight();
				n = balance(n);
			}
			return n;
		}
		public BinaryTreeNodeImpl nullCheck(BinaryTreeNodeImpl n)
		{
			if (n==null)
			{
				return null;
			}
			else return n;
		}
		public boolean checkChildrenForNull(BinaryTreeNodeImpl n)
		{
			 if (n.RightChild==null)
			{
				n= n.LeftChild;
			}
			else if (n.LeftChild==null)
			{
			 	n= n.RightChild;
			}
			 return true;
		}
		public void traverse (Object o, BinaryTreeNodeImpl n)
		{
			
			if (n!=null && n.data.compareTo(o)<0)
			{
				n.RightChild =  removerec(o,n.RightChild);
			}
			else if (n!=null && n.data.compareTo(o)>0)
			{
				n.LeftChild =  removerec(o,n.LeftChild);
			}
		}
		public Comparable findMin(BinaryTreeNodeImpl n)
		{
			//BinaryTreeNodeImpl tempNode = n;
			while(n.LeftChild != null)
			{
				//System.out.println(n.data);
				n = n.LeftChild;
			}
			return n.data;
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
			BinaryTreeNodeImpl n = root;
			IteratorImpl iter = new IteratorImpl(n);
			while (iter.hasNext())
			{
				array[i]=iter.next();
				i++;
			}
			return array;
			
		}
		public BinaryTreeNodeImpl[] toArray(BinaryTreeNodeImpl n)
		{
			Object[] array = new Object[n.height];
			int i=0;
			Iterator iter;
			Collection collect = new ArrayList (n.height);
			iter = collect.iterator();
			while (iter.hasNext())
			{
				array[i]=n.data;
				i++;
			}
			return (BinaryTreeNodeImpl[]) array;
		}
		/*
		 * balance (Node n)
		 * current = rotate (current)
		 * 
		 */
		public Object[] toArray(Object[] a) {
			
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	
		public class BinaryTreeNodeImpl implements BinaryTreeNode{
		private Comparable data;
		private BinaryTreeNodeImpl LeftChild;
		private BinaryTreeNodeImpl RightChild;
		private int height;
			/**
			 * NOTE: This is an optional interface that you will only need to
			 * implement if you want to use the AvlGUI.
			 */
			 /**
			     * Returns the data that is stored in this node
			     * 
			     * @return the data that is stored in this node.
			     */
		public BinaryTreeNodeImpl(Object o)
		{
			data = (Comparable)o;
		}
			    public Object getData()
			    {
			    	return data;
			    }

			    /**
			     * Returns the left child of this node or null if it doesn't have one.
			     * 
			     * @return the left child of this node or null if it doesn't have one.
			     */
			    public BinaryTreeNode getLeftChild()
			    {
			    	return LeftChild;
			    }

			    /**
			     * Returns the right child of this node or null if it doesn't have one.
			     * 
			     * @return the right child of this node or null if it doesn't have one.
			     */
			    public BinaryTreeNode getRightChild()
			    {
			    	return RightChild;
			    }

			    /**
			     * Returns the height of this node. The height is the number of edges
			     * from this node to this nodes farthest child.
			     */
			    public int getHeight()
			    {	    	return height;
			    }
			    public void updateHeight()
			    {

			    	if (this.RightChild == null && this.LeftChild== null)
			    	{
			    		this.height = 0;
			    	}
			    	else if (this.RightChild==null)
			    	{
			    		//current.RightChild.height = -1;
			    		this.height = this.LeftChild.height +1;
			    	}
			    	else if (this.LeftChild == null)
			    	{
			    		//current.LeftChild.height = -1;
			    		this.height = RightChild.height +1;
			    	}
			    	else if (this.RightChild != null || this.LeftChild!= null)
			    	{
			    		this.height = Math.max(this.RightChild.height, this.LeftChild.height)+1;
			    	}
			    }
		}
		public class IteratorImpl implements Iterator{
			private Stack stack;
			public IteratorImpl(BinaryTreeNodeImpl n)
			{
					stack = new Stack();
				    if (n!=null)
				    {
				    	stack.push(n);
				    }
			}
			public boolean hasNext() {
				return !stack.empty();
			}
			public Object next() {
				//if (!stack.empty())
				//{
				BinaryTreeNodeImpl n;
					n = (BinaryTreeNodeImpl) stack.pop();
					
					if ( n.RightChild != null)
					{
						stack.push(n.RightChild);
					}
					if (n.LeftChild != null)
					{
						stack.push(n.LeftChild);
					}
					
					return n.getData();
				//}
				//return "Error from IteratorImpl next() Method";
			}
			public void remove() {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException();
			}
}
}
