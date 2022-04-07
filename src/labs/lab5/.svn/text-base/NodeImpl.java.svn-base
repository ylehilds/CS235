package cs235.xml;
class NodeImpl implements Node {
	
	private NodeImpl parent;
	private NodeImpl prevSibling;
	private NodeImpl nextSibling;
	private NodeImpl firstChild;
	private NodeImpl lastChild;
	public NodeImpl() {
	}
	public int getType() {
	      // this should be overridden in DocumentImpl, ElementImpl,
	      // and TextImpl to return the appropriate type
	      throw new UnsupportedOperationException();
	   }
	   public Node getParent() {
	      return parent;
	   }
	   public Node getFirstChild() {
	      return firstChild;
	   }
	   public Node getLastChild() {
	      return lastChild;
	   }
	   public Node getNextSibling() {
	      return nextSibling;
	   }
	   public Node getPreviousSibling() {
	      return prevSibling;
	   }
	   public void insertChildBefore(Node newChild, Node child) {
		   NodeImpl AnewChild = (NodeImpl)newChild;
		   NodeImpl Achild = (NodeImpl)child;
		    
		   if (AnewChild ==null)
		   {
			   throw new IllegalArgumentException();
		   }
		   if (Achild ==null)
		   {
			   throw new IllegalArgumentException();
		   }
		   if (Achild.getParent()!= this)
		   {
			   throw new IllegalArgumentException();
		   } 
	
		   
		  // if (Achild.getParent().getFirstChild()==Achild.getParent().getLastChild())
		   if (firstChild == Achild)
		   {
			   firstChild = AnewChild;
			 //  lastChild = Achild;
			   AnewChild.parent = this; //(NodeImpl) Achild.getParent();
			   Achild.prevSibling=AnewChild;
			   AnewChild.nextSibling=Achild;
		   }
		  
		   //if(Achild.getFirstChild()!=AnewChild && Achild.getLastChild()!= AnewChild)
		   else
		   {
			   AnewChild.nextSibling= Achild;
			   AnewChild.prevSibling= Achild.prevSibling;
			   Achild.prevSibling.nextSibling = AnewChild;
			   Achild.prevSibling=AnewChild;
			   AnewChild.parent=this;
			   
			 //   Achild.prevSibling=AnewChild;
		   	//	AnewChild.prevSibling=Achild.prevSibling;
		   	//	AnewChild.nextSibling=Achild;
		   	//	Achild.prevSibling=AnewChild;
		   }
		   		//Achild.prevSibling.nextSibling=AnewChild;
		   		//AnewChild.prevSibling=Achild.prevSibling;
		   		//AnewChild.nextSibling=Achild;
		   		//Achild.prevSibling=AnewChild;
		  
		  
	   }
	   public void appendChild(Node newChild) {
		   NodeImpl aNewChild = (NodeImpl) newChild;
		   if (aNewChild==null)
			   throw new IllegalArgumentException();
		   if (this.firstChild==null)
		   {
			   firstChild = aNewChild;
			   lastChild = aNewChild;
			   aNewChild.parent = this;
		   }
		   else
		   {
			   lastChild.nextSibling=aNewChild;
			   aNewChild.prevSibling = lastChild;
			   lastChild = aNewChild;
			   aNewChild.parent = this;
		   }
	   }
	   public void removeChild(Node child) {
		   NodeImpl Achild = (NodeImpl)child;
		   if (Achild==lastChild && Achild==firstChild)
		   {
			   lastChild=null;
			   firstChild=null;
		   }
		   else if (Achild == firstChild)
		   {
			   firstChild = Achild.nextSibling;
			   firstChild.prevSibling=null;
		   }
		   else if (Achild==lastChild)
		   {
			   lastChild = Achild.prevSibling;
			   lastChild.nextSibling=null;
		   }
		   else
		   {
			   Achild.prevSibling.nextSibling=Achild.nextSibling;
			   Achild.nextSibling.prevSibling=Achild.prevSibling;
			   Achild.nextSibling=null;
			   Achild.prevSibling=null;
			   Achild.parent=null;
		   }
	   }
	   public void replaceChild(Node newChild, Node oldChild) {
		   insertChildBefore(newChild, oldChild);
		   removeChild(oldChild);   
	   }
	}

