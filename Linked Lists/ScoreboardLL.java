// ScoreboardLL class to hold a record
// of GameEntry high scores
// 
// Author: MCM 2022


public class ScoreboardLL {

    // -------------------------- INSTANCE VARIABLES ------------------------------
    // our instance variables are
    // 1. an array of game entries
    // 2. an int numEntries
    SinglyLinkedList<GameEntry>[] board;
    int numEntries;

    // ------------------------------ CONSTRUCTOR ---------------------------------
    // our constructor takes a value from the user which
    // will be the size of the array
    // we also need to initialize numEntries to 0
    public ScoreboardLL(int size) 
    {
        board = new SinglyLinkedList[size];
        // initialize linked lists inside array
        for (int i = 0; i < size; i++)
        {
            board[i] = new SinglyLinkedList<GameEntry>();
        }
        numEntries = 0;
    }

    // -------------------------------- METHODS --------------------------------------

    // add method which attempts to add GameEntry e to the board
    // it will only add e if it qualifies for a high score
    public void add(GameEntry e)
    {
        // first we check if e qualifies to be a high score
        // either: the board isnt full yet OR e is a high score (higher then the last element)
        // (we need to use "numEntries -1" because we dont know where the last element is specifically)
        if (numEntries < board.length || e.compareTo(board[numEntries-1].first()) > 1) 
        {
            // at this point, we know e will be a high score, just dont know where to put it
            // if the board isnt full yet, we need to increment numEntries
            if (numEntries < board.length)
                numEntries++;    
        
            // now, we know numEntries is accurate and we just have to determine
            // where e should be placed in the board
            // we do this by:
            // 1. creating a locaiton (loc) variable for where we will insert e
            // 2. while e is bigger then the current score
            //      2.1 move the current score to the right (lower position)
            //      2.2 decrement the locaiton variable
            int loc = numEntries - 1; // location variable, numEntries - 1 so we arent out of bounds
            // in other words, it will go something like this
            // 1. while loc is more then 0 (we need to check this first so we dont get outOfBoundsExeption) 
            //          AND e's score is more then the current score
            //      1.1 move all the values to the right (a lower position)
            //      2.2 then we decrement loc since we are moving down the array
            while (loc > 0 && board[loc-1].first().compareTo(e) < 1) 
            // (we need to do loc-1 since we are comparing e (loc) to the element to the left (loc-1))
            {
                // move elements down
                board[loc] = board[loc-1];     // [a,b,c] -> [a,_,b] -> [_,a,b]
                // decrement loc     
                loc--;                                
            }

            // finnaly, add to the board
            board[loc].addLast(e);                      
        }      
    }

    
    // method to remove the game entry at given index
    // return that game entry to the user
    // public GameEntry remove(int index) throws IndexOutOfBoundsException
    // {
    //     // first, need to check if the given index is larger then the amount of entries in the board
    //     // if so, throw the exeption
    //     if (index > numEntries-1)
    //         throw new IndexOutOfBoundsException("For this ScoreboardLL, index is invalid at: " + index);
    //     // otherwise
    //     else
    //     {
    //         // create a gameEntry object that holds the value we removed
    //         GameEntry e = board[index];

    //         // for every value after the index the user gave
    //         for (int i = index; i < numEntries-1; i++)
    //         {
    //             // take the current element and move it one to the left
    //             board[i] = board[i+1];
    //         }
    //         // set the last elemenet to null since it is now empty
    //         // we MUST do this because if we dont, the last value will just be a copy of the value before it
    //         board[numEntries-1] = null;
    //         // decrement numEntriess since we removed an element
    //         numEntries--;
    //         // return the removed game entry to the user
    //         return e;
    //     }
    // }


    // method to print the ScoreboardLL
    // in JSI format
    public String toString()
    {
        // create counter variable
        int loc = 0;
        // create string to hold data
        String s = "[";
        // while we arent out of bounds AND the current value isnt null
        while (loc < board.length && board[loc].first() != null)
        {
            // add the entry information to the string
            s += board[loc].toString() + ", ";
            loc++;
        }
        // finish the string format
        s = s.substring(0,s.length()-1) + "]";
        // return the string
        return s;
        
    }


}
