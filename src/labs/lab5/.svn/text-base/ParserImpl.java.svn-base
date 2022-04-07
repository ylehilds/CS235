

package cs235.xml;

import java.io.InputStream;
import java.io.IOException;
import java.util.Stack;

/**
 * The Parser interface represents an XML parser.
 */
public class ParserImpl implements Parser {
    private final int BOF = 0;
    private  final int START_TAG = 1;
    private final int END_TAG = 2;
    private final int TEXT = 3;
    private final int EOF = 4;
    private Stack stack = new Stack();

    /**
     * Parses the XML document contained in the specified input stream
     * and returns a document tree that represents the document.
     * 
     * @param input an input stream containing an XML document
     * @return the root node of document tree
     * @throws IllegalArgumentException if input is null
     * @throws IOException if an input error occurs while parsing the XML document
     * @throws XMLException if the XML document being parsed is not in valid XML format
     */
    public Document parse(InputStream input) throws IOException, XMLException
    {
	
    	 String tagname; 
    	Tokenizer token = new TokenizerImpl(input);
    	if (input == null) 
    	{
    		throw new IllegalArgumentException();
    	}
    	Document doc = ParserFactory.createDocument();
    	NodeImpl currentPointer = new NodeImpl();
    	currentPointer = (NodeImpl) doc;
    	while (token.getTokenType()!=EOF )
    	{
    		token.nextToken();
    		if(token.getTokenType()==START_TAG)
    		{
    			tagname = token.getTagName();
    			stack.push(tagname);
        		Element currentElement = ParserFactory.createElement(tagname);
        		for (int i=0; i<token.getAttributeCount();i++)
        		{
        			currentElement.setAttribute(token.getAttributeName(i), 
        					token.getAttributeValue(i));
        		}		
        		currentPointer.appendChild(currentElement);
        		currentPointer = (NodeImpl) currentElement;
    		}
    		if(token.getTokenType()==TEXT )
    		{
    			Text text = ParserFactory.createText(token.getText());
    			currentPointer.appendChild(text);
    		}
    		if(token.getTokenType()==END_TAG)
    		{
    			currentPointer = (NodeImpl) currentPointer.getParent();
    			if (stack.size()==0)
    			{
    				throw new XMLException();
    			}
    			tagname = token.getTagName();
    			String check = (String) stack.pop();
    			if (!check.equals(tagname ))
    			{
    				throw new XMLException();
    			} 
    		}
    		
    	}
    	if (stack.size()!=0)
    	{
    		throw new XMLException();
    	}
    	return doc;
    }
}

