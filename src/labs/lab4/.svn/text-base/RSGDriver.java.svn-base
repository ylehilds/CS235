

package cs235.rsg;


import java.util.Scanner;


public class RSGDriver {

    public static void main(String[] args) {

	if (args.length != 1) {
	    System.out.println("Usage: RSGDriver grammar");
	    System.exit(1);
	}

	String grammarFile = args[0];

	RSG rsg = Factory.createRSG();
	rsg.loadGrammar(grammarFile);

	String sentence = rsg.generateSentence();

	final int MaxLen = 40;
	sentence = formatSentence(sentence, MaxLen);

	System.out.println();
	System.out.println(sentence);

    }

    public static String formatSentence(String sentence, int lineMax) {

	String s = "";
	String line = "";

	Scanner scanner = new Scanner(sentence);
	while (scanner.hasNext()) {

	    String word = scanner.next();

	    if (isPunct(word) || line.length() + word.length() + 1 < lineMax) {
		line = append(line, word);
	    } else {
		s += line + "\n";
		line = word;
	    }

	}

	if (!line.equals(""))
	    s += line + "\n";

	return s;

    }

    public static String append(String line, String word) {

	if (line.equals("") || isPunct(word))
	    return line + word;
	else
	    return line + " " + word;

    }

    public static boolean isPunct(String s) {
	return
	    s.equals("'s") ||
	    s.equals("s") ||
	    s.equals(".") ||
	    s.equals(",") ||
	    s.equals(":") ||
	    s.equals("?") ||
	    s.equals("'");
    }

}


