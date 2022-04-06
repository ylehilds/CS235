
package cs235.boggle;

import java.io.File;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Iterator;

public class TestDriver
{
    private static String dictionaryFileName = "dictionary.txt";
    private static final String[][] boards = {
	{ "H","E","B","E","Z","K","T","S","T" },
	{ "I","O","N","T","E","V","R","C","V" },
	{ "P","R","D","E","O","T","L","J","E","I","E","A","C","O","R","N" },
	{ "U","G","I","A","O","H","S","S","T","U","E","T","Y","N","T","W" },
	{ "H","A","I","X","R","E","O","O","Y","Qu","E","O","H","R","F","O" }, 
	{ "E","S","P","O","L","I","W","A","D","O","M","O","D","L","V",
	  "A","S","T","L","P","H","E","I","R","E" }
    };
	
    private static final String[][] answers = {
	{ "bee","bees","beet","beets","behest","hes","hest","het","hets",
	  "keet","keets","see","seek","set","skee","skeet","steek","tee",
	  "test","tsk","zee","zees","zek","zeks","zest" },
	{ "envoi","eon","ion","net","not","note","noter","one","oven",
	  "over","overt","rec","recti","recto","rei","renvoi","ret",
	  "rev","ten","tie","tier","toe","ton","tone","toner","vert",
	  "vet","veto","voe","vote","voter" },
	{ "aerie","aero","alien","alit","alter","alto","anele","aneled",
	  "areic","arete","ariel","aril","ariled","ceil","ceiled","ceiler",
	  "ceorl","cete","cire","cite","citer","coeternal","coil","coiled",
	  "coiler","coir","coral","core","corn","cornea","corneal","cornel",
	  "cornet","dele","delete","deli","deltic","droit","drop","dropt",
	  "earn","elan","elite","eternal","etic","etoile","ilea","jane",
	  "jean","jete","lane","lari","lean","lear","learn","lice","lien",
	  "lier","lira","lire","lite","liter","naled","naric","near",
	  "netop","oiled","oiler","optic","oral","oriel","orle","otic",
	  "poet","poetic","port","porter","portico","portlier","protea",
	  "protean","protei","rale","real","relic","relit","renal","reoil",
	  "reoiled","rete","retie","retile","retiled","retro","rice","riel",
	  "rile","riled","riot","rite","roil","roiled","rote","roti","rotl",
	  "teal","tear","tela","tele","telic","tern","tier","tilde","tile",
	  "tiled","tiler","tire","tiro","toil","toile","toiled","toiler",
	  "trop" },
	{ "ashen","ashes","assent","asset","enthusiast","ghis","gist",
	  "gists","gout","gouty","hent","hest","hests","hets","hiss",
	  "hist","hists","hogs","hotness","house","houses","hues","hugs",
	  "hunt","issue","ness","nest","nests","nets","nett","netts",
	  "newt","newts","ohia","ohias","ought","oust","ousts","outwent",
	  "sash","sent","sets","sett","setts","shent","shes","shew",
	  "shist","shog","shot","shout","shun","shunt","shut","shute",
	  "shutes","sigh","sighs","sight","stet","stew","sues","suet",
	  "suets","tent","tenth","tenths","tenty","tenuto","test","tests",
	  "tets","then","thesis","thew","this","thou","thous","thug",
	  "thugs","thus","togs","tough","toughen","toughest","toughs","touse",
	  "touses","tout","tune","tunes","tush","tushes","tusseh","twenty",
	  "tyne","tynes","ughs","unto","uses","went","west","wests","wets" },
	{ "aery","eery","eyra","eyre","free","freer","fryer","hare","hear",
	  "queer","query","quey","reef","rhea","yeah","year" },
	{ "addle","adds","ados","aldol","aldose","allies","alto","altos",
	  "alts","amie","amies","amis","amosite","apod","apse","astir",
	  "awes","dado","dados","dads","dallies","daps","dawdle","dawdler",
	  "daws","doll","dollies","dollish","dolt","doltish","dolts","doms",
	  "dopa","dopas","dose","dost","dote","dotes","dots","dowie","dows",
	  "dowse","east","elds","elite","elites","haes","haet","haets","hams",
	  "hast","haste","heil","heir","heist","hest","hets","istle","ladle",
	  "ladler","lads","laps","lapse","lase","laws","lies","lire","list",
	  "lite","litre","lits","load","loads","loll","loller","lollies","loop",
	  "loops","lops","maes","maestri","mash","mast","misadd","misadds",
	  "misallies","mise","moas","modal","mods","most","moste","mote",
	  "motes","motile","mots","mows","oast","odds","olds","oops","opal",
	  "ostler","owes","owse","paddle","paddler","padle","pads","pall",
	  "palliest","palp","pase","paws","pelite","pelites","pelt","peltries",
	  "pelts","peri","peril","perilla","perillas","peris","perish",
	  "perlite","perlites","pert","plie","plies","plod","polo","pood",
	  "pool","pries","priest","prill","prise","prism","psaltries","relies",
	  "relish","relist","relit","replies","rile","rill","rise","rite",
	  "rites","saddle","saddler","saddleries","sall","sallies","salol",
	  "saloop","salp","salt","saltie","salties","saltire","saltish",
	  "salts","salvo","seam","sham","shamois","shea","sild","sill","silt",
	  "sima","simas","sims","sire","site","smote","soap","soaps","soda",
	  "sodas","soma","sows","spado","spall","spaller","spool","steam",
	  "stile","still","stir","stirp","stoa","stoae","stoas","stoma",
	  "stow","stowp","stowps","stows","strep","swaddle","swap","swim",
	  "swims","swot","swots","team","teams","teas","ties","tile","tiler",
	  "till","tire","tirl","toad","toads","tods","toms","tosh","towie",
	  "towies","tows","tries","trill","trisomies","volt","volte","voltes",
	  "volti","volts","waddle","waddler","wads","wadset","wall","wallie",
	  "wallies","waps","wasp","wise","wisp","woad","woads","woald",
	  "woalds","wost","wots" } 
	};
			
    public static void main(String[] args)
    {
	System.out.println("Boggle TestDriver 2, v1.0 6/3/2004 ==============================");
	BogglePlayer p = BoggleFactory.createBogglePlayer();
	if (p==null)	{
	    System.out.println("Could not create class that implements Boggle Player. "
			       + "Did you implement BoggleFactory?");
	    System.exit(0);
	}

	testDictionary(p);

	for (int j=0; j<boards.length; j++) {
	    SortedSet s = testBoard(p, boards[j], answers[j]);
	    testIsOnBoard(p, boards[j], s);
	}

	System.out.println("All Tests Completed Successfully.");
    }

    private static void testDictionary(BogglePlayer p) {
	System.out.print("\tLoading non-existant dictionary ... \t");
	try	{
	    p.loadDictionary("totally-bogus.nope.txt");
	    System.out.println("FAILED\n\nloadDictionary did not throw "
			       + "IllegalArgumentException for invalid file.");
	    System.exit(0);
	}	catch (IllegalArgumentException e){}
	try	{
	    p.loadDictionary(null);
	    System.out.println("FAILED\n\nloadDictionary did not throw "
			       + "IllegalArgumentException for null file.");
	    System.exit(0);
	}	catch (IllegalArgumentException e)	{}
	System.out.print("PASSED\n\tLoading " + dictionaryFileName + " ... \t\t");
	try	{
	    File testPath = new File(dictionaryFileName);
	    if (!testPath.exists())
	    // look for it in the parent directory
		dictionaryFileName = "../" + dictionaryFileName;
	    p.loadDictionary(dictionaryFileName);
	}	catch (IllegalArgumentException e)	{
	    System.out.println("FAILED\n\n" + dictionaryFileName + " did not open!");
	    System.exit(0);
	}
	System.out.println("PASSED");
    }

    private static SortedSet testBoard(BogglePlayer p, String[] board, String[] answer) {
	final int MinWordLength = 4;
				
	int len = (int)Math.sqrt(board.length);
	int wordlen = (len > MinWordLength) ? MinWordLength : len;
	System.out.print("Testing a " + len + "x" + len
			 + " board, min word length: " + wordlen);
	System.out.println(" ------------------------");
	p.setBoard(board);

	System.out.print("\tCalling getAllValidWords ... \t\t");	
	SortedSet s = p.getAllValidWords(wordlen);
	System.out.println("done.");

	System.out.print("\tValidating returned words ...\t\t");
	validateWords(s,answer);
	System.out.println("PASSED");

	return s;
    }

    private static void testIsOnBoard(BogglePlayer p, String[] board, SortedSet s) {
	System.out.print("\tChecking isOnBoard() solutions ... \t");
	Iterator i = s.iterator();
	while (i.hasNext()) {
	    String str = (String)i.next();
	    List wlist = p.isOnBoard(str);
	    if (wlist == null)	{
		System.out.println("FAILED\n\nisOnBoard returned null "
				   + "(no path) for " + str + ".");
		System.exit(0);
	    }
	    String result = getWordFromLocations(wlist, board);
	    if (!str.equalsIgnoreCase(result)) {
		System.out.println("FAILED\n\nisOnBoard returned a path "
				   + "that doesn't make up \'" + str + "\'.");
		System.out.println("\tWord: " + str + "   Your path: " + wlist);
		System.exit(0);
	    }
	}
	System.out.println("PASSED");
    }

    private static void validateWords(SortedSet s, String[] answer)
    {
	SortedSet addit = new TreeSet();
	SortedSet notfound = new TreeSet();
	Iterator i = s.iterator();
	while (i.hasNext()) {
	    String str = (String)i.next();
	    int result = Arrays.binarySearch(answer,str);
	    if (result < 0)
		notfound.add(str);
	}
	for (int j=0; j<answer.length; j++) {
	    if (!s.contains(answer[j]))
		addit.add(answer[j]);
	}
	boolean failed = false;
	if (!notfound.isEmpty())	{
	    System.out.print("FAILED\n\ngetAllValidWords returned the following "
			     + "additional words not on the board:\n\t");
	    System.out.println(notfound);
	    failed = true;
	}
	if (!addit.isEmpty())	{
	    System.out.print("FAILED\n\ngetAllValidWords did not return the "
			     + "following words on the board:\n\t");
	    System.out.println(addit);
	    failed = true;
	}
	if (failed)
	    System.exit(0);
    }

    private static String getWordFromLocations(java.util.List locations, String[] dice) {
	boolean[] visited = new boolean[dice.length];
	for (int i=0; i<dice.length; i++)
	    visited[i]=false;
	if (locations==null) return null;
	String result = "";
	for(int i = 0; i < locations.size(); i++)              {
	    int loc = ((Integer)locations.get(i)).intValue();
	    if (visited[loc])	{
		System.out.print("FAILED\n\nisOnBoard() returned a solution with a duplicate:\n\t");
		System.out.println(locations);
		System.exit(0);
	    }
	    visited[loc] = true;
	    result += dice[loc];
	}
	return result;
    }

}

