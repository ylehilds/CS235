package cs235.xml;

import java.util.ArrayList;
import java.util.List;
/**
 * The Element interface represents an element in an XML document tree.
 */
public class ElementImpl extends NodeImpl implements Element{
	private List <String> ArrayOfAttributeNames = new ArrayList<String>();
	private List <String> ArrayOfAttributeValues = new   ArrayList<String>();
	private String TagName;
	private String nameOfAttribute;
	private String valueOfAttribute;
	private int count;
	    /**
	     * @return the name of this element
	     */
	public int getType() {
		return ELEMENT_NODE;
	      // this should be overridden in DocumentImpl, ElementImpl,
	      // and TextImpl to return the appropriate type
	      
	   }
	    public String getTagName()
	    {
	    	return TagName;
	    }
	    
	    /**
	     * Sets the name of this element.
	     * 
	     * @param the new name of the element
	     * @throws IllegalArgumentException if tagName is null
	     */
	    public void setTagName(String tagName)
	    {
	    	if (tagName==null)
	    	{
	    		throw new IllegalArgumentException();
	    	}
	    	TagName = tagName;
	    }

	    /**
	     * Returns the value of the specified attribute.
	     * 
	     * @param name the name of the attribute whose value is being requested
	     * @return the value of the specified attribute, or null if the element
	     *          has no attribute with that name
	     * @throws IllegalArgumentException if name is null
	     */
	    public String getAttributeValue(String name)
	    {
	    	if (name==null)
	    	{
	    		throw new IllegalArgumentException();
	    	}
	    	if (ArrayOfAttributeValues.contains(name))
	    	{
	    		int i = ArrayOfAttributeNames.indexOf(name);
	    		return (String)ArrayOfAttributeValues.get(i);
	    	}
	    	else return null;
	    }
	    
	    /**
	     * @return the number of attributes that this element has
	     */
	    public int getAttributeCount()
	    {
	    	return ArrayOfAttributeNames.size();
	    }
	    
	    /**
	     * Returns the name of the ith attribute (indices start at 0)
	     * 
	     * @param i the index of the attribute whose name is being requested
	     * @return the name of the ith attribute (indices start at 0)
	     * @throws IllegalArgumentException if i is an invalid index
	     */
	    public String getAttributeName(int i)
	    {
	    	if (i<0)
	    			{
	    				throw new IllegalArgumentException();
	    			}
	    	return ArrayOfAttributeNames.get(i);
	    }
	    
	    /**
	     * Returns the value of the ith attribute (indices start at 0)
	     * 
	     * @param i the index of the attribute whose value is being requested
	     * @return the value of the ith attribute (indices start at 0)
	     * @throws IllegalArgumentException if i is an invalid index
	     */
	    public String getAttributeValue(int i)
	    {
	    	if (i<0)
			{
				throw new IllegalArgumentException();
			}
	return ArrayOfAttributeValues.get(i);
	    }
	    
	    /**
	     * Sets the value of the specified attribute on this element.
	     * 
	     * @param name the name of the attribute that is being set
	     * @param value the value of the attribute that is being set
	     * @throws IllegalArgumentException if name is null
	     * @throws IllegalArgumentException if value is null
	     */
	    public void setAttribute(String name, String value)
	    {
	    	if (name==null)
	    	{
	    		throw new IllegalArgumentException();
	    	}
	    	if (value==null)
	    	{
	    		throw new IllegalArgumentException();
	    	}
	    	nameOfAttribute = name;
	    	valueOfAttribute = value;
	    	ArrayOfAttributeNames.add(nameOfAttribute);
	    	ArrayOfAttributeValues.add(valueOfAttribute);
	    } 
	    public String toString()
	    {
	    	String tree="";
	    	tree +="<"+TagName;
	    	if (ArrayOfAttributeNames.size()>0)
	    	for (int i=0; i<ArrayOfAttributeNames.size(); i++)
	    	{
	    		tree+=" "+ArrayOfAttributeNames.get(i) + "=\""+ ArrayOfAttributeValues.get(i) +"\"";
	    	}
	    	tree+=">";
	    	//this.getFirstChild();
	    
	   // 	this.getFirstChild().toString();
	    	
	    //	while(this.getNextSibling()!=null)
	    	//{
	    	//this.getNextSibling().toString();
	    //	}
	    	NodeImpl Pointer =  new NodeImpl();
	    	Pointer = (NodeImpl) this.getFirstChild();
	    	while (Pointer!= null)
	    	{
	    		tree+=Pointer.toString();
	    		Pointer = (NodeImpl) Pointer.getNextSibling();
	    	}
	    	tree +="</"+TagName;
	    	tree+=">";
	    	return tree;
	    }
}

