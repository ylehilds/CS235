

package cs235.mindreader;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;


// compile with javac -cp junit.jar *.java
// run with java -cp junit.jar:. cs235.mindreader.TestDriver


public class TestDriver extends junit.framework.TestCase {

    public static void main(String[] args) {
	junit.textui.TestRunner.run(TestDriver.class);
    }

    private MindReader game;
    private int readerScore;
    private int playerScore;

    private void makeMindReader() {
	game = Factory.createMindReader();
	readerScore = 0;
	playerScore = 0;
    }

    private void makeChoice(String choice) {
	String predict = game.getPrediction();
	game.makeChoice(choice);
	if (predict.equals(choice))
	    readerScore++;
	else
	    playerScore++;
    }

    private void assertPrediction(String predict) {
	assertEquals(predict, game.getPrediction());
    }

    private void assertScores() {
	assertEquals(readerScore, game.getMindReaderScore());
	assertEquals(playerScore, game.getPlayerScore());
    }

    private void assertSave(String filename) {
	game.savePlayerProfile("save.txt");
	Set expect = loadSet(filename);
	Set actual = loadSet("save.txt");
	assertEquals(expect, actual);
    }

    private Set<String> loadSet(String filename) {
	Set<String> set = new HashSet<String>();
	try {
	    Scanner in = new Scanner(new File(filename));
	    while (in.hasNextLine())
		set.add(in.nextLine());
	    in.close();
	}
	catch (IOException e) {
	    System.out.println("FILE ERROR: did you get the test files?");
	}
	return set;
    }

    private String predictText = 
	"Testing: getPrediction with no learning";
    private String scoringText = 
	"Testing: getMindReaderScore and getPlayerScore";
    private String learningText = 
	"Testing: getPrediction with learning";
    private String saveText = 
	"Testing: savePlayerProfile";
    private String loadText = 
	"Testing: loadPlayerProfile";

    private void printStart(String text) {
	System.out.println();
	System.out.println(text);
    }

    private void printPassed() {
	System.out.println(".\t.\t.\t.\t.\t.\t.\tPASSED");
	System.out.println();
    }

    private final String[][] choices = {
	{"heads", "heads", "heads", "heads"},
	{"tails", "tails", "tails", "tails"},
	{"tails", "heads", "tails", "heads"},
	{"tails", "tails", "heads", "heads"}
    };

    public void testDefaultPrediction() {
	printStart(predictText);
	for (int t = 0; t < choices.length; t++) {
	    makeMindReader();
	    for (int i = 0; i < choices[t].length; i++) {
		makeChoice(choices[t][i]);
		assertPrediction("heads");
	    }
	}
	printPassed();
    }

    public void testSimpleScoring() {
	printStart(scoringText);
	for (int t = 0; t < choices.length; t++) {
	    makeMindReader();
	    for (int i = 0; i < choices[t].length; i++) {
		makeChoice(choices[t][i]);
		assertScores();
	    }
	}
	printPassed();
    }

    private final String[][] learned = {
	{"heads", "heads", "heads", "heads"},
	{"tails", "tails", "tails", "tails"},
	{"heads", "tails", "heads", "tails"},
	{"heads", "heads", "heads", "tails"}
    };

    public void testSimpleLearning() {
	printStart(learningText);
	for (int t = 0; t < choices.length; t++) {
	    makeMindReader();
	    for (int i = 0; i < choices[t].length; i++) {
		makeChoice(choices[t][i]);
		assertPrediction("heads");
	    }
	    for (int i = 0; i < choices[t].length; i++) {
		makeChoice(choices[t][i]);
		assertPrediction(learned[t][i]);
	    }
	}
	printPassed();
    }

    public void testSave() {
	printStart(saveText);
	for (int t = 0; t < choices.length; t++) {
	    makeMindReader();
	    for (int i = 0; i < choices[t].length; i++)
		makeChoice(choices[t][i]);
	    for (int i = 0; i < choices[t].length; i++)
		makeChoice(choices[t][i]);
	    assertSave("profile" + t + ".txt");
	}
	printPassed();
    } 

    private final String[][] loaded = {
	{"heads", "heads", "heads", "heads"},
	{"heads", "heads", "heads", "tails"},
	{"heads", "heads", "heads", "tails"},
	{"heads", "heads", "heads", "tails"}
    };

    public void testLoad() {
	printStart(loadText);
	for (int t = 0; t < choices.length; t++) {
	    makeMindReader();
	    game.loadPlayerProfile("profile" + t + ".txt");
	    for (int i = 0; i < choices[t].length; i++) {
		makeChoice(choices[t][i]);
		assertPrediction(loaded[t][i]);
	    }
	}
	printPassed();
    }

}


