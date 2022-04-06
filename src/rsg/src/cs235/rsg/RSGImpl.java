package cs235.rsg;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class RSGImpl implements RSG{
private	List  terminals = new LinkedList();
private	List  termString = new LinkedList();
private Map map = new TreeMap();
private String word="";
private List list = new LinkedList();
//Iterator goThroughFile = map.iterator();


	    /**
	     * Generate a random sentence using the loaded grammar
	     * 
	     * @return a sentence generated randomly from the grammar.
	     */
	    public String generateSentence()
	    {
	    	//System.out.println(rec("<start>"));
	    	word="";
	    	return rec("<start>");
	    }

	    public String rec(String key)
	    {
	    	
	    	List rule = new LinkedList();
	    	rule= (List)map.get(key); 
	    	Random gen = new Random();
	    	int num = gen.nextInt(rule.size());
	    	List random = new LinkedList();
	    	random = (List)rule.get(num);
	    	Iterator i = random.iterator();
	    	//while loop based on i.hasnext(), alternative
	    	while (i.hasNext())
	    	//for (int m=0;m<random.size();m++)
	    	{
	    		String variable = (String)i.next();
	    		if (variable.startsWith("<"))
	    				{
	    					word=" "+rec(variable);
	    				}
	    		else
	    			word+=" " + variable;
	    	}	
	    	return word;
	    }
	    /**
	     * Load a grammar into the random sentence generator
	     * 
	     * @param filename the name of the file to be loaded
	     * @return true if the grammar was successfully loaded from the file,
	     *         or false if an error occurred
	     * @throws IllegalArgumentException if filename is null
	     */
	    public boolean loadGrammar(String filename)
	    {
	    	if (filename.equals(null))
	    	{
	    		throw new IllegalArgumentException();
	    	}
	    	try 
	    	{
	    	Scanner read = new Scanner(new FileReader(filename));
	    	while (read.hasNext())
	    	{
	    			String item=read.next();
	    	if (item.equals("{"))
	    		{
	    			setUpElements(read);
	    		}
	    	}
	    	read.close();
	    	} catch (FileNotFoundException e)
	    	{
	    		return false;
	    	}
	    	return true;
	    }
	   
	    public void setUpElements(Scanner read)
	    {
	    	terminals = new LinkedList();
	    	String temp=read.next();
	    	String key = temp;
	    	temp=read.next();
	    	
	    	while (!temp.equals("}"))
	    	{
	    		termString = new LinkedList();
	    		while (!temp.equals(";"))
	    		{
	    			termString.add(temp);
	    			temp=read.next();
	    		}
	    		terminals.add(new LinkedList(termString));
	    		temp=read.next();
	    	}
	    	map.put(key, new LinkedList(terminals));
	    	//System.out.println(map);
	    	//System.out.println(map.size());
	    
	    }
	    /**
	     * Save the current grammar to a file.
	     * 
	     * @param filename the name of the file in which to store the grammar
	     * @return true if the grammar was successfully written to the file,
	     *         or false if an error occurred
	     * @throws IllegalArgumentException if filename is null
	     */
	    public Map getMap()
	    {
	    	return map;
	    }
	   // public String toString() {
	    //	
	    	//return "student" + "\n"+ getID() +"\n"+getName() + "\n"+ major + "\n" +gpa + "\n";
	   // }
	    public boolean saveGrammar(String filename)
	    {
	    	if (filename.equals(null))
	    	{
	    		throw new IllegalArgumentException();
	    	}
	    	Set s = getMap().keySet();
	    	Iterator i = s.iterator();
	        PrintWriter printer = null;
	    	try 
	    	{
	    		printer = new PrintWriter(new FileWriter(filename));
	           // printer.println(getMap().size()); 
	            while (i.hasNext())
	            {
	            	String temp = (String)i.next();
	            	printer.println("{");
	            	printer.println(temp);
	            	printer.println(printNested(temp));
	            	printer.println("}");
	            	printer.println("");
	            	//System.out.println(map.get(i));
	            	//printer.println(map.get(i));
	            }
	            printer.close();
	    	} catch (IOException e)
	    	{
	    		return false;
	    	}
	    	return true;
	    }
//iterate through map. need a keyset variable and iterator, go through each rule and print it out.

	private String printNested(String temp) {
		List topList = new LinkedList();
		topList = (LinkedList)map.get(temp);
		return printSetOfRules(topList);
	}
	public String printSetOfRules(List outerList)
	{
		String rule="";
		Iterator i = outerList.iterator();
		while (i.hasNext())
		{
			List innerList = new LinkedList();
			innerList=(LinkedList)i.next();
			Iterator j = innerList.iterator();
			while (j.hasNext())
			{
				String current = (String)j.next();
				rule+=" "+current;
			}
			if (i.hasNext())
			{
				rule=rule+" ;\n"; 
			}
			else
				rule=rule+" ;";
		}
		return rule;
	}
	



	
	
}
