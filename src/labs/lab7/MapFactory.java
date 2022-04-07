
package cs235.hash;

public class MapFactory {
    /**
     * Creates and returns an object that implements the java.util.Map interface
     *
     * @return A new object that implements the java.util.Map interface
     */
    public static java.util.Map createMap() {
    	MapImpl map = new MapImpl();
	return map;
    }
}
