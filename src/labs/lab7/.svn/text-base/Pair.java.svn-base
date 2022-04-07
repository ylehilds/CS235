package cs235.hash;


public class Pair
{
    private Object key;
    private Object value;

public Pair(Object aKey, Object aValue)
{
	key = aKey;
	value = aValue;
}

public Object keyGetter()
{
	return key;
}
public Object valueGetter()
{
	return value;
}

public boolean equals(Object key) {
	if (key instanceof Pair)
	{
		return ((Pair)key).key.equals(this.key);
	}
	else return false;
    }
public int hashCode() {
	return key.hashCode();
    }
}
