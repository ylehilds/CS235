

package cs235.rsg;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Iterator;

import java.io.File;
import java.io.IOException;


// compile with javac -cp junit.jar *.java
// run with java -cp junit.jar:. cs235.rsg.TestDriver


public class TestDriver extends junit.framework.TestCase {

    private String path = "";

    public static void main(String[] args) {

	junit.framework.TestSuite suite =
	    new junit.framework.TestSuite(TestDriver.class);
	junit.framework.TestResult result = junit.textui.TestRunner.run(suite);

	if (result.wasSuccessful())
	    System.out.println("All tests PASSED.");
	else
	    System.out.println("FAILED.");

    }


    public void testLoadSavePoem() {
	assertLoadSave("Poem");
    }
    public void testLoadSaveHaiku() {
	assertLoadSave("Haiku");
    }
    public void testLoadSaveDear() {
	assertLoadSave("Dear-John-letter");
    }
    public void testLoadSaveBond() {
	assertLoadSave("Bond-movie");
    }

    public void testLoadGenerateUniquePoem() {
	assertLoadGenerateUnique("Poem");
    }
    public void testLoadGenerateUniqueHaiku() {
	assertLoadGenerateUnique("Haiku");
    }
    public void testLoadGenerateUniqueDear() {
	assertLoadGenerateUnique("Dear-John-letter");
    }
    public void testLoadGenerateUniqueBond() {
	assertLoadGenerateUnique("Bond-movie");
    }

    public void testLoadGenerateInSetPoem() {
	assertLoadGenerateInSet("Poem");
    }


    private String loadSaveText =
	"Testing: loadGrammar/saveGrammar";
    private String generateInSetText =
	"Testing: generateSentence gives valid sentences";
    private String generateUniqueText =
	"Testing: generateSentence gives different sentences";


    public void assertLoadSave(String name) {
	printStart(loadSaveText);
	makeRSG();
	assertLoad(name + ".g");
	assertSave("save-" + name + ".g");
	printPassed();
    }

    public void assertLoadGenerateInSet(String name) {
	printStart(generateInSetText);
	makeRSG();
	assertLoad(name + ".g");
	assertGenerateInSet(name + ".txt");
	printPassed();
    }

    public void assertLoadGenerateUnique(String name) {
	printStart(generateUniqueText);
	makeRSG();
	assertLoad(name + ".g");
	assertGenerateUnique();
	printPassed();
    }


    private RSG rsg;

    private void makeRSG() {
	rsg = Factory.createRSG();
    }


    private void assertLoad(String file) {
	System.out.println("loadGrammar from file: " + file);
	assertTrue(rsg.loadGrammar(path + file));
    }

    private void assertSave(String file) {

	System.out.println("saveGrammar must match file: " + file);
	assertTrue(rsg.saveGrammar("save.txt"));

	String expect = loadString(path + file);
	String actual = loadString("save.txt");
	assertSameTokens(expect, actual);

    }

    final private int genCount = 10;
    final private int genLength = 80;

    public void assertGenerateInSet(String file) {

	System.out.println("generateSentence must be found in file: " + file);
	Set<String> set = loadSet(path + file);

	for (int i = 0; i < genCount; i++) {
	    String sentence = rsg.generateSentence();
	    if (sentence.length() < genLength)
		System.out.println("generated: " + sentence);
	    assertInSet(set, sentence);
	}

    }

    public void assertGenerateUnique() {
	System.out.println("generateSentence must produce unique strings");
	Set<String> strings = new HashSet<String>();
	for (int i = 0; i < genCount; i++) {
	    String sentence = rsg.generateSentence();
	    if (sentence.length() < genLength)
		System.out.println("generated: " + sentence);
	    strings.add(sentence);
	}
	System.out.println("How many unique strings?: " +
			   strings.size() + " out of " + genCount);
	assertTrue(strings.size() > genCount / 2);
    }


    private void stringToTokens(Collection<String> collection, String s) {
	Scanner in = new Scanner(s);
	while (in.hasNext())
	    collection.add(in.next());
    }

    private List<String> stringToTokenList(String s) {
	List<String> list = new LinkedList<String>();
	stringToTokens(list, s);
	return list;
    }

    private String listToString(List<String> list) {
	String s = "";
	Iterator<String> i = list.iterator();
	while (i.hasNext())
	    s += i.next() + "\n";
	return s;
    }

    private String loadString(String filename) {
	List<String> list = loadList(filename);
	return listToString(list);
    }

    private void assertSameTokens(String expect, String actual) {

	List<String> expectList = stringToTokenList(expect);
	List<String> actualList = stringToTokenList(actual);

	if (!expectList.equals(actualList)) {
	    System.out.println("FAILED");
	    System.out.println("Expected: " + expect);
	    System.out.println("Actual: " + actual);
	}

	assertEquals(expectList, actualList);

    }

    private void assertInSet(Set<String> set, String actual) {

	Iterator<String> i = set.iterator();
	while (i.hasNext()) {
	    String expect = i.next();
	    List<String> expectList = stringToTokenList(expect);
	    List<String> actualList = stringToTokenList(actual);
	    if (expectList.equals(actualList))
		return;
	}

	System.out.println("FAILED");
	System.out.println("string not found in file: " + actual);
	fail();

    }


    private void printStart(String text) {
	System.out.println();
	System.out.println(text);
    }

    private void printPassed() {
	System.out.println(".\t.\t.\t.\t.\t.\t.\tPASSED");
	System.out.println();
    }


    private Set<String> loadSet(String filename) {
	Set<String> set = new HashSet<String>();
	loadCollection(set, filename);
	return set;
    }

    private List<String> loadList(String filename) {
	List<String> list = new LinkedList<String>();
	loadCollection(list, filename);
	return list;
    }

    private void loadCollection(Collection<String> collection, String filename) {
	try {
	    Scanner in = new Scanner(new File(filename));
	    while (in.hasNextLine())
		collection.add(in.nextLine());
	    in.close();
	}
	catch (IOException e) {
	    System.out.println("FILE ERROR: did you get the test files?");
	}
    }



}


