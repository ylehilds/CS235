
package cs235.xml;


import java.io.OutputStream;
import java.io.IOException;

/**
	 * The Document interface represents the root of an XML document tree.
	 */
public class DocumentImpl extends NodeImpl implements Document {
	/**
	     * Writes the document in XML format to the specified output stream.
	     * 
	     * @param output the output stream to which the document should be written
	     * @throws IllegalArgumentException if output is null
	     * @throws IOException if an output error occurs while writing to the stream
	     */
	    public void write(OutputStream output) throws IOException
	    {
	    	if (output == null)
	    	{
	    		throw new IllegalArgumentException();
	    	}
	    	String tree = this.getFirstChild().toString();
	    	output.write(tree.getBytes());
	    	output.close();
	    }
	    public int getType() {
			return DOCUMENT_NODE;
		      // this should be overridden in DocumentImpl, ElementImpl,
		      // and TextImpl to return the appropriate type  
		   }
	   // public String toString()
	    //{
	    	//return  (String) this.getFirstChild().toString();
	    //}
}
// String getbytes    -- char[]
//	OUtputStream.write (getbytes)    
//	output.close

