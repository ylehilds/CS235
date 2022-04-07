package cs235.boggle;

import java.util.Comparator;

public class PrefixComparator implements Comparator<String>{

	public int compare(String one, String two)
	{
	if (one.startsWith(two))
	{
	return 0;
	}
	return one.compareTo(two);
	}
}
