
package cs235.hash;

import java.util.Set;

public class SetFactory {
    /**
     * Creates and returns an object that implements the java.util.Set interface
     *
     * @return A new object that implements the java.util.Set interface
     */
    public static java.util.Set createSet() {
    	Set set = new SetImpl();
	return set;
    }
}

