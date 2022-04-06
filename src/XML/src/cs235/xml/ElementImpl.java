package cs235.xml;

import java.util.ArrayList;

/**
 * The Element interface represents an element in an XML document tree.
 */
public class ElementImpl extends NodeImpl implements Element{
	ArrayList ArrayOfAttributeNames = new ArrayList();
	ArrayList ArrayOfAttributeValues = new ArrayList();
	private String TagName;
	private String nameOfAttribute;
	private String valueOfAttribute;
	private int count;
	    /**
	     * @return the name of this element
	     */
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
	    	if (name==nameOfAttribute)
	    	{
	    		return valueOfAttribute; 
	    	}
	    	else return null;
	    }
	    
	    /**
	     * @return the number of attributes that this element has
	     */
	    public int getAttributeCount()
	    {
	    	return count;
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
	    	String attributeNames="";
	    	//for (i=0; i<count; i++)
	    	//{
	    		attributeNames+=ArrayOfAttributeNames.get(i);
	    	//}
	    	return attributeNames;
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
	String attributeValues="";
	//for (i=0; i<count; i++)
	//{
		attributeValues+=ArrayOfAttributeValues.get(i);
	//}
	return attributeValues;
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
	    	count++;
	    	nameOfAttribute = name;
	    	valueOfAttribute = value;
	    	ArrayOfAttributeNames.add(nameOfAttribute);
	    	ArrayOfAttributeValues.add(ArrayOfAttributeValues);
	    }
}