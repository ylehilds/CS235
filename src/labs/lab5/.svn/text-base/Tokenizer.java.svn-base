
package cs235.xml;

import java.io.IOException;


public interface Tokenizer {

    /**
     * These constants represent the different kinds of tokens
     * that can appear in an XML file.
     * 
     * BOF          beginning of file, nextToken has never been called
     * START_TAG    start tag token
     * END_TAG      end tag token
     * TEXT         text data token
     * EOF          end of file, there are no more tokens
     */ 
    int BOF = 0;
    int START_TAG = 1;
    int END_TAG = 2;
    int TEXT = 3;
    int EOF = 4;
 
   
     /**
     * Constructs a Tokenizer object
     * 
     * Note: Constructors are not allowed in interfaces. That is why it is commented.
     * It is found here for your convenience.
     */
     //TokenizerImpl(InputStream is);
       
    /**
     * Reads the next token from the XML file
     * 
     * @return an integer indicating the type of the token that was
     *          read from the file.  If there are no more tokens in the
     *          file, EOF is returned.
     * @throws IOException if and I/O error occurs
     * @throws XMLException if the file contains invalid XML
     */
    int nextToken() throws IOException, XMLException;

    /**
     * Returns the type of the current token (the one last read by nextToken)
     * 
     * @return an integer indicating the type of the current token.
     *          If nextToken has never been called, BOF is returned.
     */
    int getTokenType();

    /**
     * Returns the element name associated with the current tag token
     * 
     * @return the element name associated with the current tag token
     * @throws IllegalStateException if the current token type is not
     *          START_TAG or END_TAG  
     */  
    String getTagName();

    /**
     * Returns the value of the named attribute.
     * If the tag doesn't have an attribute with the specified name,
     * null is returned.
     * 
     * @return Returns the value of the named attribute.
     *          If the tag doesn't have an attribute with the specified
     *          name, null is returned.
     * @throws IllegalStateException if the current token type is not
     *          START_TAG
     */  
    String getAttributeValue(String name);

    /**
     * Returns the number of attributes
     * 
     * @return Returns the number of attributes
     * @throws IllegalStateException if the current token type is not
     *          START_TAG
     */  
    int getAttributeCount();

    /**
     * Returns the name of the attribute at the specified index
     * 
     * @return Returns the name of the attribute at the specified index
     * @throws IllegalStateException if the current token type is not
     *          START_TAG
     * @throws IllegalArgumentException if there is no attribute at
     *          the specified index
     */
    String getAttributeName(int i);

    /**
     * Returns the value of the attribute at the specified index
     * 
     * @return Returns the value of the attribute at the specified index
     * @throws IllegalStateException if the current token type is not
     *          START_TAG
     * @throws IllegalArgumentException if there is no attribute at
     *          the specified index
     */
    String getAttributeValue(int i);
    
    /**
     * Returns the string associated with the current text token
     * 
     * @return Returns the string associated with the current text token
     * @throws IllegalStateException if the current token type is not TEXT
     */
    String getText();
}
