package cs235.xml;

/**
	 * The Text interface represents a text node in an XML document tree.
	 */
public class TextImpl extends NodeImpl implements Text{
	private String text;
	    /**
	     * @return the text string stored in this node
	     */
	   public String getData()
	    {
		   return text;
	    }
	    
	    /**
	     * Sets the text string stored in this node.
	     * 
	     * @param data the text string to be stored in this node
	     * @throws IllegalArgumentException if data is null
	     */
	    public void setData(String data)
	    {
	    	if (data==null)
	    		throw new IllegalArgumentException();
	    	text= data;
	    } 
	    public int getType() {
			return TEXT_NODE;
		      // this should be overridden in DocumentImpl, ElementImpl,
		      // and TextImpl to return the appropriate type
		      
		   }
	    public String toString()
	    {
	    	return text;
	    }
	}


