package cs235.mindreader;

import java.util.List;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedList;
public class MindReaderImpl implements MindReader{
private String prediction;
private int playerScore=0;
private int mindReaderScore=0;
private Map map = new HashMap();

public Map getMap()
{
	return map;
}
private final int delimeter =4;
private List l = new LinkedList();

private class Pair
{
    private int heads=0;
    private int tails=0;

public Pair(int _heads, int _tails)
{
	heads = _heads;
	tails = _tails;
}

public String toString()
{
	return getHeads() + " " + getTails();
}

public Pair()
{
    heads = 0;
    tails = 0;
}

public int getHeads()
{
	return heads;
}

public void incrheads()
{
    heads++;
}

public void incrtails()
{
    tails++;
}

public int getTails()
{
	return tails;
}
}
public void checkIfIncrement(String choice)
{
	Pair pair = (Pair) getMap().get(l);
	if (choice.equals("tails"))
	{
		pair.incrtails();
	}
	if (choice.equals("heads"))
	{
		pair.incrheads();
	}  
}
public String getPrediction()
{
        Pair pair = (Pair) getMap().get(l);
        
        if (pair == null)
        {
            return "heads";   
        }
        if (pair.getHeads()< pair.getTails())
        {
            return "tails";          
        }
        else if (pair.getHeads()> pair.getTails())
        {
            return "heads";  
        }       
        return "heads";
    }

	public void makeChoice(String choice)
    {
        if ((!choice.equals("heads")) && (!choice.equals("tails")))
        {
            throw new IllegalArgumentException();
        }
        if (choice.equals(getPrediction()))
        {
            mindReaderScore++;
        }
        else
        {
            playerScore++;
        }
           
          if (l.size()==delimeter)
          {
        	if (!getMap().containsKey(l))
        	{
        		getMap().put(new LinkedList(l), new Pair());
        	}
        	//if (map.containsKey(l))
        	//{
        	checkIfIncrement(choice);
        		
        	//}
        }
         
        l.add(choice);
        if (l.size()>delimeter)
        {
        	l.remove(0);
        }
    }

    public int getPlayerScore()
    {
        return playerScore;
    }
    
    public int getMindReaderScore()
    {
        return mindReaderScore;       
    }

    public boolean savePlayerProfile(String filename)
    {
    	//System.out.println(filename +" "+ map);
    	if (filename == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	Set s = getMap().keySet();
    	Iterator i = s.iterator();
        PrintWriter printer = null;
        try
        {
        printer = new PrintWriter(new FileWriter(filename));
        printer.println(getMap().size()); 
        while (i.hasNext())
        { 
        	final int zero = 0;
        	final int one = 1;
        	final int two = 2;
        	final int three = 3;
        	l = (List) i.next();
        	Pair pair = (Pair) getMap().get(l);
        	printer.println(l.get(zero)+" "+l.get(one)+" "+l.get(two)+
        			" "+l.get(three)+" "+pair.getHeads()+" "+pair.getTails());
        }
        printer.close();
        }
        catch (IOException e)
        {
           System.out.println("invalid number");
           return false;
        }     
        return true;
    }
    
    public boolean loadPlayerProfile(String filename)
    {
    	if (filename == null)
    	{
    		throw new IllegalArgumentException();
    	}
        try {
            Scanner in = new Scanner(new FileReader(filename));
            int number = in.nextInt();
            for (int i=0; i<number;i++)
            {
            	List list = new LinkedList();
            	list.add(in.next());
            	list.add(in.next());
            	list.add(in.next());
            	list.add(in.next());
            	Pair pair = new Pair(in.nextInt(),in.nextInt());
            	getMap().put(list, pair);
            }
            in.close();
            } catch (FileNotFoundException e)
            	{
            		System.out.println("Input/output error " + e);
			return false;

            	}
        return true;
    }
}
