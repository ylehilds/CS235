

package cs235.avl;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.StringTokenizer;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TestDriver {
    public static void main (String[] args) {
	java.util.Set goodSet = new java.util.HashSet();
	java.util.Set testSet = SetFactory.createSet();
	setAddTest(goodSet, testSet);
	treeAddTest(testSet);
	setRemoveTest(goodSet, testSet);
	treeRemoveTest(testSet);
	setRandomTest(goodSet, testSet);
	treeRandomTest(testSet);
    }
    private static String[] adds = {
	"add0.txt",
	"add1.txt",
	"add2.txt",
	"add3.txt",
	"add4.txt",
	"add5.txt",
	"add6.txt"
    };
    static void setAddTest(Set goodSet, Set testSet) {
	System.out.println("*** Set Add Test ***");
	for (int i = 0; i < adds.length; i++) {
	    goodSet.clear();
	    testSet.clear();
	    System.out.println("adding items from file: " + adds[i]);
	    List addList = new LinkedList();
	    loadList(addList, adds[i]);
	    addItems(goodSet, addList);
	    addItems(testSet, addList);
	    System.out.println("comparing set contents");
	    compareContents(goodSet, testSet);
	    System.out.println("\t\tPASSED");
	}
    }
    private static String[] addsPre = {
	"add0pre.txt",
	"add1pre.txt",
	"add2pre.txt",
	"add3pre.txt",
	"add4pre.txt",
	"add5pre.txt",
	"add6pre.txt"
    };
    static void treeAddTest(Set testSet) {
	System.out.println("*** Tree Preorder Add Test ***");
	for (int i = 0; i < adds.length; i++) {
	    testSet.clear();
	    System.out.println("adding items from file: " + adds[i]);
	    List addList = new LinkedList();
	    loadList(addList, adds[i]);
	    addItems(testSet, addList);
	    System.out.println("checking tree structure");
	    List preList = new LinkedList();
	    loadList(preList, addsPre[i]);
	    checkIterator(preList, testSet);
	    checkToArray(preList, testSet);
	    System.out.println("\t\tPASSED");
	}
    }
    private static String[] removes = {
	"remove0.txt",
	"remove1.txt",
	"remove2.txt",
	"remove3.txt",
	"remove4.txt",
	"remove5.txt",
	"remove6.txt"
    };
    static void setRemoveTest(Set goodSet, Set testSet) {
	System.out.println("*** Set Remove Test ***");
	for (int i = 0; i < adds.length; i++) {
	    goodSet.clear();
	    testSet.clear();
	    System.out.println("adding items from file: " + adds[i]);
	    List addList = new LinkedList();
	    loadList(addList, adds[i]);
	    addItems(goodSet, addList);
	    addItems(testSet, addList);
	    System.out.println("removing items from file: " + removes[i]);
	    List removeList = new LinkedList();
	    loadList(removeList, removes[i]);
	    removeItems(goodSet, removeList);
	    removeItems(testSet, removeList);
	    System.out.println("comparing set contents");
	    compareContents(goodSet, testSet);
	    System.out.println("\t\tPASSED");
	    System.out.println("removing items from file: " + adds[i]);
	    removeItems(goodSet, addList);
	    removeItems(testSet, addList);
	    System.out.println("comparing set contents");
	    compareContents(goodSet, testSet);
	    System.out.println("\t\tPASSED");
	}
    }
    private static String[] removesPre = {
	"remove0pre.txt",
	"remove1pre.txt",
	"remove2pre.txt",
	"remove3pre.txt",
	"remove4pre.txt",
	"remove5pre.txt",
	"remove6pre.txt"
    };
    static void treeRemoveTest(Set testSet) {
	System.out.println("*** Tree Preorder Remove Test ***");
	for (int i = 0; i < adds.length; i++) {
	    testSet.clear();
	    System.out.println("adding items from file: " + adds[i]);
	    List addList = new LinkedList();
	    loadList(addList, adds[i]);
	    addItems(testSet, addList);
	    System.out.println("removing items from file: " + removes[i]);
	    List removeList = new LinkedList();
	    loadList(removeList, removes[i]);
	    removeItems(testSet, removeList);
	    System.out.println("checking tree structure");
	    List preList = new LinkedList();
	    loadList(preList, removesPre[i]);
	    checkIterator(preList, testSet);
	    checkToArray(preList, testSet);
	    System.out.println("\t\tPASSED");
	}
    }
    private static String[] randoms = {
	"random0.txt",
	"random1.txt",
	"random2.txt",
	"random3.txt",
	"random4.txt",
	"random5.txt",
	"random6.txt"
    };
    static void setRandomTest(Set goodSet, Set testSet) {
	System.out.println("*** Set Random Test ***");
	for (int i = 0; i < randoms.length; i++) {
	    goodSet.clear();
	    testSet.clear();
	    System.out.println("executing commands from file: " + randoms[i]);
	    List commands = new LinkedList();
	    loadList(commands, randoms[i]);
	    Iterator iter = commands.iterator();
	    while (iter.hasNext()) {
		String command = (String)iter.next();
		StringTokenizer st = new StringTokenizer(command);
		String operation = st.nextToken();
		String item = st.nextToken();
		if (operation.equals("add"))
		    checkBooleans("add", item, goodSet.add(item), testSet.add(item));
		else if (operation.equals("remove"))
		    checkBooleans("remove", item, goodSet.remove(item), testSet.remove(item));
		else if (operation.equals("contains"))
		    checkBooleans("contains", item, goodSet.contains(item), testSet.contains(item));
		checkSize(goodSet, testSet);
	    }
	    System.out.println("comparing set contents");
	    compareContents(goodSet, testSet);
	    System.out.println("\t\tPASSED");
	}
    }
    private static String[] randomsPre = {
	"random0pre.txt",
	"random1pre.txt",
	"random2pre.txt",
	"random3pre.txt",
	"random4pre.txt",
	"random5pre.txt",
	"random6pre.txt"
    };
    static void treeRandomTest(Set testSet) {
	System.out.println("*** Tree Preorder Random Test ***");
	for (int i = 0; i < randoms.length; i++) {
	    testSet.clear();
	    System.out.println("executing commands from file: " + randoms[i]);
	    List commands = new LinkedList();
	    loadList(commands, randoms[i]);
	    Iterator iter = commands.iterator();
	    while (iter.hasNext()) {
		String command = (String)iter.next();
		StringTokenizer st = new StringTokenizer(command);
		String operation = st.nextToken();
		String item = st.nextToken();
		if (operation.equals("add"))
		    testSet.add(item);
		else if (operation.equals("remove"))
		    testSet.remove(item);
		else if (operation.equals("contains"))
		    testSet.contains(item);
	    }
	    System.out.println("checking tree structure");
	    List preList = new LinkedList();
	    loadList(preList, randomsPre[i]);
	    checkIterator(preList, testSet);
	    checkToArray(preList, testSet);
	    System.out.println("\t\tPASSED");
	}
    }

    static void loadList(List list, String file) {
	try {
	    BufferedReader in = new BufferedReader(new FileReader(file));
	    String item;
	    while((item = in.readLine()) != null)
		list.add(item);
	    in.close();
	}
	catch (IOException e) {
	    System.out.println("FILE ERROR: did you get the test files?");
	}
    }
    static void addItems(Set set, List items) {
	Iterator iter = items.iterator();
	while (iter.hasNext())
	    set.add(iter.next());
    }
    static void removeItems(Set set, List items) {
	Iterator iter = items.iterator();
	while (iter.hasNext())
	    set.remove(iter.next());
    }
    static void checkIterator(List goodList, Set testSet) {
	Iterator goodIter = goodList.iterator();
	Iterator testIter = testSet.iterator();
	while (goodIter.hasNext())
	    checkObjects("iterator", goodIter.next(), testIter.next());
    }
    static void checkToArray(List goodList, Set testSet) {
	Iterator goodIter = goodList.iterator();
	Object[] testArray = testSet.toArray();
	for (int i = 0; goodIter.hasNext(); i++)
	    checkObjects("toArray", goodIter.next(), testArray[i]);
    }
    static void checkBooleans(String operation, Object item,
			      boolean expected, boolean actual) {
	if (expected != actual) {
	    System.out.println("Operation: " + operation + " Item: " + item);
	    System.out.println("expected: " + expected);
	    System.out.println("actual: " + actual);
	    failed();
	}
    }
    static void checkObjects(String operation, 
			      Object expected, Object actual) {
	if ((expected == null && expected != actual) || 
	    (expected != null && !expected.equals(actual))) {
	    System.out.println("Operation: " + operation);
	    System.out.println("expected: " + expected);
	    System.out.println("actual: " + actual);
	    failed();
	}
    }
    static void checkInts(String operation, 
			  int expected, int actual) {
	if (expected != actual) {
	    System.out.println("Operation: " + operation);
	    System.out.println("expected: " + expected);
	    System.out.println("actual: " + actual);
	    failed();
	}
    }
    static void checkSubset(Set set, Set superSet, 
			    String name, String superName) {
	Iterator iter = set.iterator();
	while (iter.hasNext()) {
	    Object item = iter.next();
	    if (!superSet.contains(item)) {
		System.out.println(name + " contains " + item);
		System.out.println(superName + " doesn't contain " + item);
		failed();
	    }
	}
    }
    static void compareContents(Set stdSet, Set testSet) {
	checkSize(stdSet, testSet);
	checkSubset(stdSet, testSet, "expected", "actual");
	checkSubset(testSet, stdSet, "actual", "expected");
    }
    static void checkSize(Set stdSet, Set testSet) {
	checkInts("size", stdSet.size(), testSet.size());
    }

    static void failed() {
	System.out.println(".\t.\t.\t.\t.\t test FAILED");
	System.exit(1);
    }
}

