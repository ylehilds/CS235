

package cs235.xml;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;


public class TestNode implements Serializable {
    private int type;
    private String data;
    private String tagName;
    private List attrNames;
    private List attrVals;
    private List children;

    public TestNode(Node node) {
	type = node.getType();

	switch (type) {
	case Node.DOCUMENT_NODE:
	    // do nothing
	    break;
	case Node.ELEMENT_NODE:
	    {
		Element elem = (Element)node;
		tagName = elem.getTagName();
		int attrCount = elem.getAttributeCount();
		attrNames = new ArrayList(attrCount);
		attrVals = new ArrayList(attrCount);
		for (int i=0; i < attrCount; ++i) {
		    attrNames.add(elem.getAttributeName(i));
		    attrVals.add(elem.getAttributeValue(i));
		}
	    }
	    break;
	case Node.TEXT_NODE:
	    data = ((Text)node).getData();
	    break;
	default:
	    break;
	}

	if (node.getFirstChild() != null) {
	    children = new ArrayList();
	    Node c = node.getFirstChild();
	    while (c != null) {
		children.add(new TestNode(c));
		c = c.getNextSibling();
	    }
	}
    }

    public String toString() {
	StringBuffer buf = new StringBuffer();
	print(buf, 0);
	return buf.toString();
    }

    private void print(StringBuffer buf, int indentLevel) {
	for (int i=0; i < indentLevel; ++i) {
	    buf.append("   ");
	}

	switch (type) {
	case Node.DOCUMENT_NODE:
	    buf.append("Document:");
	    break;
	case Node.ELEMENT_NODE:
	    buf.append("Element:  ");
	    buf.append(tagName);
	    buf.append("  ");
	    buf.append(attrNames.toString());
	    buf.append("  ");
	    buf.append(attrVals.toString());
	    break;
	case Node.TEXT_NODE:
	    buf.append("Text:  ");
	    encode(data, buf);
	    break;
	default:
	    break;
	}

	buf.append('\n');        

	if (children != null) {
	    for (int i=0; i < children.size(); ++i) {
		TestNode c = (TestNode)children.get(i);
		c.print(buf, indentLevel+1);
	    }
	}
    }

    private void encode(String str, StringBuffer buf) {
	for (int i=0; i < str.length(); ++i) {
	    char c = str.charAt(i);
	    switch (c) {
	    case '\r':
		buf.append("\\r");
		break;
	    case '\n':
		buf.append("\\n");
		break;
	    case '\t':
		buf.append("\\t");
		break;
	    default:
		buf.append(c);
		break;
	    }
	}
    }

    private String encode(String str) {
	String answer = "";
	if (str == null)	return null;
	for (int i=0; i < str.length(); ++i) {
	    char c = str.charAt(i);
	    switch (c) {
	    case '\r':
		answer += "\\r";
		break;
	    case '\n':
		answer += "\\n";
		break;
	    case '\t':
		answer += "\\t";
		break;
	    default:
		answer += c;
		break;
	    }
	}
	return answer;
    }


    private int findIndex(List list, Object val) {
	for (int i=0; i < list.size(); ++i) {
	    if (compare(list.get(i), val)) {
		return i;
	    }
	}
	return -1;
    }

    private boolean compareAttrs(List names1, List vals1,
				 List names2, List vals2) {
	if (names1.size() != names2.size()) {
	    System.out.println("[TN] Differing number of attributes");
	    System.out.println("  ACTUAL: " + names1.size() +
			       "  EXPECTED: " + names2.size());
	    return false;
	}
	for (int i=0; i < names1.size(); ++i) {
	    int idx = findIndex(names2, names1.get(i));
	    if (idx < 0) {
		System.out.println("[TN] Could not find attribute: " +
				   (String)names1.get(i));
		return false;
	    }
	    if (!compare(vals1.get(i), vals2.get(idx))) {
		System.out.println("[TN] Differing attribute values.");
		System.out.println("  ACTUAL: " + encode((String)vals1.get(i)) +
				   "  EXPECTED: " + encode((String)vals2.get(i)));
		return false;
	    }
	}
	return true;
    }

    public boolean compare(TestNode node, boolean strict) {
	if (type != node.type) {
	    System.out.println("[TN] Wrong node type, ACTUAL: " + type +
			       "  EXPECTED: " + node.type);
	    return false;
	}
		
	switch (type) {
	case Node.DOCUMENT_NODE:
	    // do nothing
	    break;
	case Node.ELEMENT_NODE:
	    if (!compareElementNode(node, strict)) {
		return false;
	    }
	    break;
	case Node.TEXT_NODE:
	    if (!compare(data, node.data)) {
		System.out.println("[TN] Wrong Text Data.");
		System.out.println("  ACTUAL: " + encode(data) +
				   "\n  EXPECTED: " + encode(node.data));
		return false;
	    }
	    break;
	default:
	    break;
	}

	if (!compare(children, node.children)) {
	    return false;
	}

	return true;
    }

    public boolean compareElementNode(TestNode node, boolean strict) {
	//System.out.println("  tagname: " + tagName);
	if (!compare(tagName, node.tagName)) {
	    System.out.println("[TN] Wrong Tag name.");
	    System.out.println("  ACTUAL: " + tagName +
			       "  EXPECTED: " + node.tagName);
	    return false;
	}
	if (strict) {
	    if (!compare(attrNames, node.attrNames)) {
		return false;
	    }
	    if (!compare(attrVals, node.attrVals)) {
		return false;
	    }
	}
	else {
	    if (!compareAttrs(attrNames, attrVals, node.attrNames, node.attrVals)) {
		return false;
	    }
	}
	return true;
    }

    public boolean equals(Object obj) {
	if (!(obj instanceof TestNode)) {
	    return false;
	}     
	return compare((TestNode)obj, true);
    }

    private boolean compare(Object x, Object y) {
	if (x == null || y == null) {
	    return x == y;
	}
	else {
	    return x.equals(y);
	}
    }
}

