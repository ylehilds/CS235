

package cs235.rsg;


public interface RSG {

    /**
     * Generate a random sentence using the loaded grammar
     * 
     * @return a sentence generated randomly from the grammar.
     */
    String generateSentence();

    /**
     * Load a grammar into the random sentence generator
     * 
     * @param filename the name of the file to be loaded
     * @return true if the grammar was successfully loaded from the file,
     *         or false if an error occurred
     * @throws IllegalArgumentException if filename is null
     */
    boolean loadGrammar(String filename);

    /**
     * Save the current grammar to a file.
     * 
     * @param filename the name of the file in which to store the grammar
     * @return true if the grammar was successfully written to the file,
     *         or false if an error occurred
     * @throws IllegalArgumentException if filename is null
     */
    boolean saveGrammar(String filename);

}


