

package cs235.xml;

import java.io.InputStream;
import java.io.IOException;


/**
 * The Parser interface represents an XML parser.
 */
public interface Parser {

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
    Document parse(InputStream input) throws IOException, XMLException;
}

