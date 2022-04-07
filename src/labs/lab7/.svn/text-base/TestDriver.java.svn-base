

package cs235.hash;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.StringTokenizer;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TestDriver {
    public static void main (String[] args) {
	java.util.Set goodSet = new java.util.TreeSet();
	java.util.Set testSet = SetFactory.createSet();
	setAddTest(goodSet, testSet);
	orderAddTest(testSet);
	setRemoveTest(goodSet, testSet);
	orderRemoveTest(testSet);
	setRandomTest(goodSet, testSet);
	orderRandomTest(testSet);

	java.util.Map goodMap = new java.util.TreeMap();
	java.util.Map testMap = MapFactory.createMap();
	mapRandomTest(goodMap, testMap);
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
    private static String[] addsOrder = {
	"add0order.txt",
	"add1order.txt",
	"add2order.txt",
	"add3order.txt",
	"add4order.txt",
	"add5order.txt",
	"add6order.txt"
    };
    static void orderAddTest(Set testSet) {
	System.out.println("*** Add Order Test ***");
	for (int i = 0; i < adds.length; i++) {
	    testSet.clear();
	    System.out.println("adding items from file: " + adds[i]);
	    List addList = new LinkedList();
	    loadList(addList, adds[i]);
	    addItems(testSet, addList);
	    System.out.println("checking order");
	    List orderList = new LinkedList();
	    loadList(orderList, addsOrder[i]);
	    checkIterator(orderList, testSet);
	    checkToArray(orderList, testSet);
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
    private static String[] removesOrder = {
	"remove0order.txt",
	"remove1order.txt",
	"remove2order.txt",
	"remove3order.txt",
	"remove4order.txt",
	"remove5order.txt",
	"remove6order.txt"
    };
    static void orderRemoveTest(Set testSet) {
	System.out.println("*** Remove Order Test ***");
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
	    System.out.println("checking order");
	    List orderList = new LinkedList();
	    loadList(orderList, removesOrder[i]);
	    checkIterator(orderList, testSet);
	    checkToArray(orderList, testSet);
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
    private static String[] randomsOrder = {
	"random0order.txt",
	"random1order.txt",
	"random2order.txt",
	"random3order.txt",
	"random4order.txt",
	"random5order.txt",
	"random6order.txt"
    };
    static void orderRandomTest(Set testSet) {
	System.out.println("*** Random Order Test ***");
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
	    System.out.println("checking order");
	    List orderList = new LinkedList();
	    loadList(orderList, randomsOrder[i]);
	    checkIterator(orderList, testSet);
	    checkToArray(orderList, testSet);
	    System.out.println("\t\tPASSED");
	}
    }

    private static String[] randomsMap = {
	"random0map.txt",
	"random1map.txt",
	"random2map.txt",
	"random3map.txt",
	"random4map.txt",
	"random5map.txt",
	"random6map.txt"
    };
    static void mapRandomTest(Map goodMap, Map testMap) {
	System.out.println("*** Map Random Test ***");
	for (int i = 0; i < randomsMap.length; i++) {
	    goodMap.clear();
	    testMap.clear();
	    System.out.println("executing commands from file: " + randomsMap[i]);
	    List commands = new LinkedList();
	    loadList(commands, randomsMap[i]);
	    Iterator iter = commands.iterator();
	    while (iter.hasNext()) {
		String command = (String)iter.next();
		StringTokenizer st = new StringTokenizer(command);
		String operation = st.nextToken();
		String item = st.nextToken();
		if (operation.equals("put")) {
		    String item2 = st.nextToken();
		    checkObjects("put", goodMap.put(item, item2), testMap.put(item, item2));
		} else if (operation.equals("remove"))
		    checkObjects("remove", goodMap.remove(item), testMap.remove(item));
		else if (operation.equals("get"))
		    checkObjects("get", goodMap.get(item), testMap.get(item));
		checkSize(goodMap, testMap);
	    }
	    System.out.println("comparing map contents");
	    compareContents(goodMap, testMap);
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
	    System.out.println("\t\tFAILED");
	    System.exit(1);
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
    static void compareContents(Set goodSet, Set testSet) {
	checkSize(goodSet, testSet);
	checkSubset(goodSet, testSet, "expected", "actual");
	checkSubset(testSet, goodSet, "actual", "expected");
    }
    static void checkSize(Set goodSet, Set testSet) {
	checkInts("size", goodSet.size(), testSet.size());
    }
    static void compareContents(Map goodMap, Map testMap) {
	checkSize(goodMap, testMap);
	Set keys = goodMap.keySet();
	Iterator iter = keys.iterator();
	while(iter.hasNext()) {
	    Object key = iter.next();
	    checkObjects("get", goodMap.get(key), testMap.get(key));
	}
    }
    static void checkSize(Map goodMap, Map testMap) {
	checkInts("size", goodMap.size(), testMap.size());
    }

    static void failed() {
	System.out.println(".\t.\t.\t.\t.\t test FAILED");
	System.exit(1);
    }
}
