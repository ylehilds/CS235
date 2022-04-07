

package cs235.xml;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class TestDriver {

    //An array containing the names of the valid XML files that will be tested
    private static String[] xmlFiles = {
	"test-1.xml",
	"test-2.xml",
	"test-3.xml",
	"test-4.xml",
	"test-5.xml",
	"test-6.xml"
    };

    //An array containing the names of the serialized files that will be used for testing
    private static String[] treeFiles = {
	"tree-1.ser",
	"tree-2.ser",
	"tree-3.ser",
	"tree-4.ser",
	"tree-5.ser",
	"tree-6.ser"
    };

    //An array containing the names of invalid XML files that will be tested
    private static String[] mismatchXmlFiles = {
	"mismatch-1.xml",
	"mismatch-2.xml",
	"mismatch-3.xml",
	"mismatch-4.xml"
    };

    private static void replaceText(Node node, Document doc) {
	List textNodes = new ArrayList();
	Node c = node.getFirstChild();
	while (c != null) {
	    if (c.getType() == Node.TEXT_NODE) {
		textNodes.add(c);
	    }
	    else {
		replaceText(c, doc);
	    }
	    c = c.getNextSibling();
	}

	for (int i=0; i < textNodes.size(); ++i) {
	    Text text = (Text)textNodes.get(i);
	    Text newText = ParserFactory.createText(text.getData());
	    Text tmp = ParserFactory.createText("fred");

	    TestNode n = new TestNode(doc);
	    //System.out.println("Before ICB: " + n);
	    node.insertChildBefore(tmp, text);
	    //n = new TestNode(doc);
	    //System.out.println("After ICB: " + n);
	    node.replaceChild(newText, tmp);
	    //n = new TestNode(doc);
	    //System.out.println("After REPLACE: " + n);
	    node.removeChild(text);
	    //n = new TestNode(doc);
	    //System.out.println("After REMOVE: " + n);

	}
    }

    //Will copy either an element node or a text node
    private static Node copyNode(Node node, Document docCopy) {
	switch (node.getType()) {
	case Node.ELEMENT_NODE:
	    {
		Element elem = (Element)node;
		Element elemCopy = ParserFactory.createElement("barney");
		if (!elemCopy.getTagName().equals("barney"))	
		    throw new IllegalStateException("Just made an element named 'barney'. " + 
						    "getTagName() should have returned barney.");
		elemCopy.setTagName(elem.getTagName());

		int attrCount = elem.getAttributeCount();
		for (int i=0; i < attrCount; ++i) {
		    elemCopy.setAttribute(elem.getAttributeName(i), elem.getAttributeValue(i));
		}

		Node child = elem.getFirstChild();
		while (child != null) {
		    elemCopy.appendChild(copyNode(child, docCopy));
		    child = child.getNextSibling();
		}

		return elemCopy;
	    }
	case Node.TEXT_NODE:
	    {
		Text text = (Text)node;
		Text textCopy = ParserFactory.createText("fred");
		if (!textCopy.getData().equals("fred"))
		    throw new IllegalStateException("Just made an text node named 'fred'. " +
						    "getData() should have returned fred.");
		textCopy.setData(text.getData());
		if (textCopy.getFirstChild() != null || textCopy.getLastChild() != null)
		    throw new IllegalStateException("For a new text node, " +
						    "the first and last child references " +
						    "should have been set to null.");
		return textCopy;
	    }
	default:
	    throw new IllegalStateException("Unknown Node type: " + node.getType());
	}
    }


    //Takes a Document object, makes an exact copy of it and returns the copy
    private static Document copyDoc(Document doc) {
	Document docCopy = ParserFactory.createDocument();
	Node child = doc.getFirstChild();
	while (child != null) {
	    docCopy.appendChild(copyNode(child, docCopy));
	    child = child.getNextSibling();
	}

	return docCopy;
    }

    private static void verifyTreeStructure(Node node, Node parent) {
	if (node.getParent() != parent)
	    throw new IllegalStateException("Parent reference was not set correctly.");
					
	if (node.getFirstChild() != null) {
	    Stack kids = new Stack();

	    Node c = node.getFirstChild();
	    while (c != null) {
		kids.push(c);
		c = c.getNextSibling();
	    }

	    c = node.getLastChild();
	    while (c != null) {
		if (kids.pop() != c)
		    throw new IllegalStateException("Iterating from last child to first " +
						    "did not yeild the same nodes as " +
						    "iterating first to last.");
		c = c.getPreviousSibling();
	    }
	    if (!kids.isEmpty())
		throw new IllegalStateException("There were more non-null references " +
						"iterating first to last " +
						"than there were last to first.");
			
	    c = node.getFirstChild();
	    while (c != null) {
		verifyTreeStructure(c, node);
		c = c.getNextSibling();
	    }
	}
	else {
	    if (node.getLastChild() != null)
		throw new IllegalStateException("If the first child is null, " +
						"then the last must be null too.");
	}
    }

    public static void main(String[] args) {

	System.out.println("XML Parser TestDriver 7/12/2004 =============================");
	balancedFileCases();
	mismatchedFileCases();
	System.out.println("Tests Completed Successfully");
    }

    static void balancedFileCases() {
	System.out.println("Balanced file cases --------------------------");	
	for (int i=0; i < xmlFiles.length; ++i) {
	    System.out.print("Performing tests on " + xmlFiles[i] + " ...\t");
	    try {
		Parser parser = ParserFactory.createParser();
		Document doc = parseDocument(parser, xmlFiles[i]);
		verifyTreeStructure(doc, null);

		TestNode expectedTree = readTree(treeFiles[i]);
		compareTrees(doc, expectedTree);

		replaceText(doc, doc);
		compareTrees(doc, expectedTree);

		Document docCopy = copyDoc(doc);
		verifyTreeStructure(docCopy, null);
		compareTrees(docCopy, expectedTree);

		replaceText(docCopy, docCopy);
		compareTrees(docCopy, expectedTree);

		writeDocument(docCopy, "write-test" + (i+1) + ".xml");

		doc = parseDocument(parser, xmlFiles[i]);
		verifyTreeStructure(doc, null);
		compareTrees(doc, expectedTree);
	    }
	    catch (IllegalStateException e) {
		failed(e);
	    }
	    System.out.println("PASSED");
	}
    }		

    static Document parseDocument(Parser parser, String filename) {
	FileInputStream xmlFile = null;
	Document doc = null;
	try {
	    xmlFile = new FileInputStream(filename);
	    doc = parser.parse(xmlFile);
	}
	catch (IOException e)	{
	    failed(e);
	}
	catch (XMLException e)	{
	    failed(e);
	}
	finally {
	    closeInputStream(xmlFile);
	}
	return doc;
    }

    static void writeDocument(Document doc, String filename) {
	FileOutputStream tmpFile = null;
	try {
	    tmpFile = new FileOutputStream(filename);
	    doc.write(tmpFile);
	}
	catch (IOException e)	{
	    failed(e);
	}
	finally {
	    closeOutputStream(tmpFile);
	}
    }

    static TestNode readTree(String filename) {
	FileInputStream treeFile = null;
	TestNode tree = null;
	try {
	    treeFile = new FileInputStream(filename);
	    tree = (TestNode)new ObjectInputStream(treeFile).readObject();
	}
	catch (IOException e)	{
	    failed(e);
	}
	catch (ClassNotFoundException e)	{
	    failed(e);
	}
	finally {
	    closeInputStream(treeFile);
	}
	return tree;
    }

    static void compareTrees(Document doc, TestNode expectedTree) {
	TestNode actualTree = new TestNode(doc);
	if (!actualTree.equals(expectedTree))	
	    throw new IllegalStateException("Actual and Expected trees were not equal.\n" + 
					    "ACTUAL: " + actualTree +
					    "\nEXPECTED: " + expectedTree);
    }

    static void mismatchedFileCases() {
	System.out.println("Mismatched file cases ------------------------");
	for (int i=0; i < mismatchXmlFiles.length; ++i) {
	    boolean xmlException = false;
	    System.out.print("Performing tests on " + mismatchXmlFiles[i] + " ...\t");
	    FileInputStream input = null;
	    try {
		input = new FileInputStream(mismatchXmlFiles[i]);
		Parser parser = ParserFactory.createParser();
		Document doc = parser.parse(input);
	    }
	    catch(XMLException e) {
		xmlException = true;
	    }
	    catch (IOException e) {
		failed(e);
	    }
	    finally {
		closeInputStream(input);
	    }
	    if (!xmlException)	{
		System.out.println("FAILED\nParser did not throw an XMLException " +
				   "for a file with unbalanced tags.");
		System.exit(-1);
	    }		
	    System.out.println("PASSED");
	}
    }

    static void closeInputStream(InputStream in) {
	if (in != null) {
	    try {
		in.close();
	    } catch (IOException e) {
	    }
	}
    }

    static void closeOutputStream(OutputStream out) {
	if (out != null) {
	    try {
		out.close();
	    } catch (IOException e) {
	    }
	}
    }

    static void failed (Exception e) {
	System.out.println("FAILED\n");
	e.printStackTrace();
	System.out.println(e.getMessage());
	System.exit(-1);
    }

}

