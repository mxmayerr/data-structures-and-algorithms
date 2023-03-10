// a client program to test out
// the Scoreboard class on some data
//
// author: (your name goes here)
// date: October 2022

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class ScoreboardClient
{
  public static void main(String[] args)
  {
    // 1. get the data into an array-list
    ArrayList<String> rawData = getData("scores.data");

    // rawData is an ArrayList of Strings, where each
    // element is of the form "name scored N", N being the
    // integer representing a score and name being a first
    // name only of some game player. The word "scored" appears
    // between the two with a single space on either side of it.

    // We will treat the first 20 elements of rawData as one set of scores,
    // and the remaining 35 as a second set of scores. So you will create
    // 2 Scoreboard objects of appropriate capacity

    // 2. create the first Scoreboard, capacity 5 (will hold 5 high scores)
    Scoreboard one = new Scoreboard(5);

    // 3. create the second Scoreboard, capacity 10 (will hold 10 high scores)
    Scoreboard two = new Scoreboard(10);

    // 4. create an array, or an ArrayList (up to you) of
    // GameEntry objects and fill it with all 55 inputs
    // (you must process the String data from rawData appropriately.
    // Complete the static method getGameEntry below for the purpose
    // of processing a String entry into a GameEntry)

    
    ArrayList<String> dataS = getData("scores.data");

    // loop through allthe string values and turn them into game entries
    ArrayList<GameEntry> data = new ArrayList<>();

    for (String s: dataS)
      data.add(getGameEntry(s));
    

    // 5. process the first 20 GameEntry objects into the first Scoreboard,
    // printing out the Scoreboard after each attempt to add another GameEntry
    // Your output should look like this:
    // "Adding (Edward,82): high scores are [(Edward,82)]
    // "Adding (Paula,87): high scores are [(Paula,87),(Edward,82)]"
    // etc.

    for (int i = 0; i < 20; i++)
    {
      one.add(data.get(i));
      System.out.println("Adding " + data.get(i).toString() + ": high scores are " + one.toString());
    }

    // some vertical whitespace between the two boards
    System.out.println();
    System.out.println();

    // 6. process the remaining 35 GameEntry objects into the second Scoreboard,
    // only printing out the high scores at the very end, after all have entered.

    for (int i= 20; i < 55; i++)
    {
      two.add(data.get(i));
      System.out.println("Adding " + data.get(i).toString() + ": high scores are " + two.toString());
    }

    // 7. use a loop to remove the score at index 0 (the high score) from
    // the second Scoreboard 12 times, printing out the Scoreboard after
    // each removal.

    for (int i = 0; i < 12; i++)
    {
      two.remove(0);
      System.out.println(two.toString());
      
    }

    System.out.println("Done!");
    


  }

  // method for turning a String of the form "name scored N"
  // into a GameEntry object
  private static GameEntry getGameEntry(String rawEntry)
  {
    GameEntry e = new GameEntry(rawEntry.substring(0,rawEntry.indexOf(" ")), Integer.parseInt(rawEntry.substring(rawEntry.indexOf(" ", rawEntry.indexOf(" ")+1)+1)));
    return e;
  }

  private static ArrayList<String> getData(String fileName)
  {
    // result will be the list of strings we get from the file
    ArrayList<String> result = new ArrayList<>();

    try
    {
      //constructor of file class having file as argument
      File file = new File(fileName);

      //file to be scanned
      Scanner sc = new Scanner(file);

      //returns true IFF scanner has another token
      while (sc.hasNextLine())
        result.add( sc.nextLine() );

      // close the scanner
      sc.close();
    }
    catch(Exception e)
    { e.printStackTrace(); }

    // return the result
    return result;
  }
}
