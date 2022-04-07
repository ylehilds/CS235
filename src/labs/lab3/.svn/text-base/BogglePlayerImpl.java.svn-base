package cs235.boggle;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
public class BogglePlayerImpl implements BogglePlayer{
    private boolean load = false;
    private boolean setup = false;
    private String [] dictionary;
    private SortedSet set = new TreeSet();
    private String[][] board=null;
    private String[][] boardIsOnBoard=null;
    private boolean[][] boolboard=null;
    private boolean[][] boolboardIsOnBoard=null;
    private List listToTrackPath = new LinkedList();
    private List listToTrackPath2 ;
    public BogglePlayerImpl()
    {
        dictionary = null;
    }
    public void loadDictionary(String fileName)
    {
        if (fileName==null)
        {
            throw new IllegalArgumentException();
        }
        try
        {
            List<String> dictnry = new ArrayList<String>();
            Scanner in = new Scanner(new FileReader(fileName));
            while (in.hasNext())
            {
                dictnry.add(in.next());
            }
            dictionary = new String [dictnry.size()];
            dictionary = dictnry.toArray(dictionary);
         } catch (FileNotFoundException e)
            {
                throw new IllegalArgumentException();
            }
         load = true;
    }
 
    public void setBoard(String[] letterArray)
    {
        double size = letterArray.length;
        int square=(int) Math.sqrt(letterArray.length);
        if (letterArray==null || (square*square!= size))
        {
            throw new IllegalArgumentException();
        }
        board = new String [square][square];
        int i=0;
        for (int r=0; r<board.length; r++) {
             for (int c=0; c<board[r].length; c++) {
                  board[r][c]=letterArray[i].toLowerCase();
                i++;
                System.out.print(" " + board[r][c]);
             }
            System.out.println("");
        }
        boolboard = new boolean[square][square];
        setUsedPathBooleanArrayToAllFalse(boolboard);
        setup = true;
       
        boolboardIsOnBoard = new boolean[square][square];
        setUsedPathBooleanArrayToAllFalse(boolboardIsOnBoard);
    }
  
    public boolean setUsedPathBooleanArrayToAllFalse (boolean [][] boolboard)
    {
      
         for (int r=0; r<boolboard.length; r++) {
              for (int c=0; c<boolboard[r].length; c++) {
                   boolboard[r][c]=false;
                // System.out.print(" " + boolboard[r][c]);
              }
             //System.out.println("");
         }
         return true;
    }
      
    public boolean isValidWord(String wordToCheck)
    {
        if (wordToCheck==null)
            {
                throw new IllegalArgumentException();
            }
        if (!load)
            {
                throw new IllegalStateException();
            }
        int result = Arrays.binarySearch(dictionary,wordToCheck);
        if (result < 0)
            {
                return false;
            }
       // List list = isOnBoard(wordToCheck);
        //if (list == set.contains(wordToCheck))
        //set.add(wordToCheck);
      
        return true;
  
        /**
        * Determines if the given word is in the dictionary.
        *
        * @param wordToCheck The word to validate
        * @return true if wordToCheck appears in dictionary, false otherwise.
        * @throws IllegalArgumentException if wordToCheck is null.
            * @throws IllegalStateException if loadDictionary has not been called.
        */ 
    }
  
    public boolean isValidPrefix(String prefixToCheck)
    {
      
        if (prefixToCheck==null)
        {
            throw new IllegalArgumentException();
        }
        if (!load)
        {
            throw new IllegalStateException();
        }
        if (java.util.Arrays.binarySearch(dictionary, prefixToCheck,  new PrefixComparator())>=0)
        {
            return true;
        }
            return false;
        /**
        * Determines if there is at least one word in the dictionary with the
        * given prefix.
        *
        * @param prefixToCheck The prefix to validate
        * @return true if prefixToCheck appears in dictionary, false otherwise.
        * @throws IllegalArgumentException if prefixToCheck is null.
        * @throws IllegalStateException if loadDictionary has not been called.
            */
    }
 
    public void rec(  int x ,int y, String word, int minimumWordLength)
    {
        if (y>=board.length || y<0||x>=board.length || x<0)
        {
            return;
        }
        if (!isValidPrefix(word))
        {
            return;
        }
        if (boolboard[x][y])//if path has been used break recursion
        {
            return;
        }
        word+=board[x][y];
        //System.out.println(word);
        boolboard[x][y]= true;//set xy true if letter is added to word I'm creating
        //System.out.println(boolboard[x][y]);
        if (isValidWord(word))
        {
            if (word.length()>=minimumWordLength)
            {
                set.add(word);
            }
        }
// recursion statements     
        rec(x,y+1,word, minimumWordLength);
        rec(x,y-1,word, minimumWordLength);
        rec(x+1,y,word, minimumWordLength);
        rec(x+1,y+1,word, minimumWordLength);
        rec(x+1,y-1,word, minimumWordLength);
        rec(x-1,y-1,word, minimumWordLength);
        rec(x-1,y,word, minimumWordLength);
        rec(x-1,y+1,word, minimumWordLength);
        boolboard[x][y]=false;//set the xy boolean 2 dimensional array to false.
       // reset(boolboard);//set the xy boolean 2 dimensional array to false.
        return;     
    }
  
    public SortedSet getAllValidWords(int minimumWordLength)
    {
         set = new TreeSet();
        if (minimumWordLength<1)
        {
            throw new IllegalArgumentException();
        }
        if (!load)
        {
            throw new IllegalStateException();
        }
        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board.length; c++) {
                 rec(r,c,"", minimumWordLength);
              // System.out.print(" " + board[r][c]);
            }
           //System.out.println("");
       }
        //System.out.println(set);
     
        return set;
     
        /**
        * Retrieves all the words in the Boggle board that appear in the
        *       dictionary.
        *
        * @param minimumWordLength The minimum allowed length for
        *    strings that will be stated as being on the board.
        * @return java.util.SortedSet which contains all the words found
        *    from the boggle board that appear in the dictionary.
        * @throws IllegalArgumentException if minimumWordLength < 1
            * @throws IllegalStateException if loadDictionary has not been called.
        */
    }
/*    public List isOnBoard(String wordToCheck)
    {
        List lehi=null;
        return lehi;
   }    */  
    /**
        * Determines if the given word is in on the Boggle board. If so,
        *    it returns the path that makes up the word.
        *
        * @param wordToCheck The word to validate
        * @return java.util.List containing java.lang.Integer objects with
        *    the path that makes up the word on the Boggle board. If word
        *    is not on the boggle board, return null.
        * @throws IllegalArgumentException if wordToCheck is null.
            * @throws IllegalStateException if loadDictionary has not been called.
        */
        public List isOnBoard(String wordToCheck) // follows the same structure of my recursive 
        //method with a few base cases different also you will need to create a global set list 
        //as to keep track of path
    {
        listToTrackPath = new LinkedList();
        listToTrackPath2 = new LinkedList();
        if (wordToCheck==null)
        {
            throw new IllegalArgumentException();
        }
        if (!setup)
        {
            throw new IllegalStateException();
        }  
       
        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board.length; c++) {
                recOnBoard(r,c, "", wordToCheck);
              // System.out.print(" " + board[r][c]);
            }
           //System.out.println("");
       }   
        //listToTrackPath = new LinkedList(listToTrackPath);
        if (listToTrackPath2.isEmpty())
        {
        	return null;
        }
        return listToTrackPath2;
}
      
 
        public void recOnBoard(int x,int y,String word, String wordToCheck)
        {
            if (y>=board.length || y<0||x>=board.length || x<0)
            {
                return;
            }
             if (!wordToCheck.startsWith(word))
            {
                return;
            }
             if (boolboardIsOnBoard[x][y])//if path has been used break recursion
             {
                 return;
             }
            word+=board[x][y];
            //System.out.println(listToTrackPath);
            Integer location = new Integer( y+x*board.length);
            //y +x*board.length
            listToTrackPath.add(location);
            listToTrackPath = new LinkedList(listToTrackPath);
            boolboardIsOnBoard[x][y]=true;
            if (wordToCheck.equals(word))
            {
                listToTrackPath2 = new LinkedList(listToTrackPath);
            }
            recOnBoard(x,y+1,word, wordToCheck);
            recOnBoard(x,y-1,word, wordToCheck);
            recOnBoard(x+1,y,word, wordToCheck);
            recOnBoard(x+1,y+1,word, wordToCheck);
            recOnBoard(x+1,y-1,word, wordToCheck);
            recOnBoard(x-1,y-1,word, wordToCheck);
            recOnBoard(x-1,y,word, wordToCheck);
            recOnBoard(x-1,y+1,word, wordToCheck);
           
            boolboardIsOnBoard[x][y]=false;
            ((LinkedList) listToTrackPath).removeLast();
            return ;
        } 
    public String[] getCustomBoard()
    {
        /**
        * An optional method that gives a user-defined boggle board to the GUI.
        *
        * @return a String array in the same form as the input to setBoard().
        */
        return getCustomBoard();
    }
}
