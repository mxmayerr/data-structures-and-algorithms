// Implements classic solution to the famous
// Towers of Hanoi puzzle, which involves moving
// a stack of N discs of various sizes from one of
// 3 pegs to another one of the pegs, in accordance with the
// following rules:
//   1. only 1 disc can be moved at a time
//   2. only the TOP disc on any peg can be moved
//   3. discs can only be placed on larger discs
//
// YOUR NAME: Max Mayer

import java.util.ArrayList;

public class TowersOfHanoi1
{
  // color constants for the visualize function
  public static final String RESET = "\033[0m";  
  // background for red, yellow, green, blue, magenta, cyan, white
  public static final String RED_BACKGROUND = "\033[41m";
  public static final String GREEN_BACKGROUND = "\033[42m";
  public static final String YELLOW_BACKGROUND = "\033[43m";
  public static final String BLUE_BACKGROUND = "\033[44m";
  public static final String MAGENTA_BACKGROUND = "\033[45m";
  public static final String CYAN_BACKGROUND = "\033[46m";
  public static final String WHITE_BACKGROUND = "\033[47m";

  // the pegs for the Towers of Hanoi problem
  // are named A, B, and C, so in String HANOI_PEGS,
  // peg A is index 0, peg B is index 1, and peg C is index 2
  private static final String HANOI_PEGS = "ABC";

  // the game state for Towers of Hanoi puzzle:
  //   a. each peg is an ArrayList of Integers
  //   b. each disc is an Integer.
  //   c. the smallest disc will have a value of 0, and each disc size
  //      will increase by 1 above that. In other words, if we have 3 discs,
  //      they will be numbered 0, 1 and 2, for example.
  private static ArrayList<ArrayList<Integer>> gameState = new ArrayList<>();

  // runs the simulation.
  // numDiscs is the total number of discs, which are numbered from
  // smallest to largest 0, 1, ... , (numDiscs - 1)
  // This method will initially stack those discs on the "from" peg
  // in ascending order (largest at index 0, smallest at index (numDiscs-1))
  // on "fromPeg". The method will then assign "toPeg" and determine which one
  // is therefore the "via" peg.
  // precondition: numDiscs > 0
  //               fromPeg and toPeg are one of "A", "B", or "C"
  public static void runTowersOfHanoi(int numDiscs, String fromPeg, String toPeg)
  {
    if(numDiscs < 1)
    {
      throw new IllegalArgumentException("the number of discs must be positive");
    }
    else if(!(HANOI_PEGS.contains(fromPeg) && HANOI_PEGS.contains(toPeg)) )
    {
      throw new IllegalArgumentException("pegs must be A, B, or C");
    }

    // populate gameState with 3 lists by
    // adding ArrayList<Integer> to gameState 3 times
    for(int i = 0; i < 3; i++)
    {
      /* I. your code goes here */
      gameState.add(new ArrayList<Integer>());
    }

    // determine int equivalents of from, to, via
    int from = HANOI_PEGS.indexOf(fromPeg);
    int to   = HANOI_PEGS.indexOf(toPeg);
    int via = 0;
    /* II. add code to properly assign via based on values of from and to */
    // using the fact that via is the only peg that is not from or to
    if(from == 0 && to == 1)
    {
      via = 2;
    }
    else if(from == 0 && to == 2)
    {
      via = 1;
    }
    else if(from == 1 && to == 0)
    {
      via = 2;
    }
    else if(from == 1 && to == 2)
    {
      via = 0;
    }
    else if(from == 2 && to == 0)
    {
      via = 1;
    }
    else if(from == 2 && to == 1)
    {
      via = 0;
    }
    else
    {
      via = -1;
    }


    // load up the from-peg with discs numbered from down to 0
    for(int disk = numDiscs - 1; disk >= 0; disk--)
    {
      /* III. code goes here to add discs to proper peg */
      gameState.get(from).add(disk);
    }

    // call showHanoiPegs or visualizeHanoiPegs to show puzzle state
    showHanoiPegs();

    // call moveTower to move the bottom (largest disc) from
    // peg "from" to peg "to", by way of peg "via"
    moveTower(numDiscs-1, from, to, via);

    showHanoiPegs();
    

    System.out.println("Puzzle complete!");

  }

  public static void visualizeHanoiPegs()
  {
    /* IV. prints a visualization of gameState */
    // get total number of discs
    int totalDiscs = gameState.get(0).size() + gameState.get(1).size() + gameState.get(2).size();

    // import colors into an array
    String[] colors = {RED_BACKGROUND, GREEN_BACKGROUND, YELLOW_BACKGROUND, BLUE_BACKGROUND, MAGENTA_BACKGROUND, CYAN_BACKGROUND, WHITE_BACKGROUND};

    // create the array to hold the strings for each peg
    String[] pegs = new String[3];
    
    // calculate the width of each column
    int width = 2 * totalDiscs + 1;

    // then, we can go through, starting at the bottom, and add to a string array
    // for every layer of the pegs (starting at bottom)
    for (int i = 0; i < totalDiscs; i++)
    {
      // then, for every peg in this layer
      for (int j = 0; j < 3; j++)
        {
          String s = "";

          // if the peg has a disc in this layer
          if (gameState.get(j).size() > i)
          {
            // get the disc number
            int discNum = gameState.get(j).get(gameState.get(j).size() - i - 1);
            // gameState.get(j).get(gameState.get(j).size() - i - 1);
            // gameState.get(j).get(i);

            // calculate the size of the disc
            int discSize = 2 * discNum + 1;

            // calculate the number of spaces on each side of the disc
            int spaces = (width - discSize) / 2;

            

            // print the spaces before the disc
            for (int k = 0; k < spaces; k++)
            {
              s += " ";
            }

            // print the disc
            for (int k = 0; k < discSize/2; k++)
            {
              s += colors[discNum] + " " + RESET;
            }

            System.out.print(colors[discNum] + discNum + RESET);

            for (int k = 0; k < discSize/2; k++)
            {
              s += colors[discNum] + " " + RESET;
            }
            

            // print the spaces after the disc
            for (int k = 0; k < spaces; k++)
            {
              s += " ";
            }
          }
          // else, the peg doesnt have a disc at this layer
          else 
          {
            // print the width of the peg with no color
            for (int k = 0; k < width; k++)
            {
              s += " ";
            }
          }
        }
      pegs[i] = s;
      }
      // print the pegs
      for (int i = 0; i < totalDiscs; i++)
      {
        System.out.println(pegs[i]);
      }
    }
    

  // simple visualization of the puzzle state
  public static void showHanoiPegs()
  {
    // simply print out the contents of each peg,
    // one peg to a line
    for(int i = 0; i < gameState.size(); i++)
    {
      System.out.print("peg " + HANOI_PEGS.substring(i,i+1) + ": ");
      System.out.println(gameState.get(i));
    }
    System.out.println();
  }

  // classic Towers of Hanoi recursive solution:
  // moves the disc numbered diskNum from peg numbered "from" to the
  // peg numbered "to", using the peg numbered "via" as the go-between
  public static void moveTower(int diskNum, int from, int to, int via)
  {
    // base case: can ALWAYS move the smallest disk
    if(diskNum  == 0)
    {
      // print which disc we are moving and to what peg
      System.out.println("move disk " + diskNum + " to peg " + HANOI_PEGS.substring(to, to+1));

      // remove the last disc on the from peg and add it to the end of the
      // to peg
      /* V. add code to do this */
      gameState.get(to).add(gameState.get(from).remove(gameState.get(from).size()-1));

      // show the puzzle state using show or visualize (HanoiPegs)
      visualizeHanoiPegs();
      showHanoiPegs();
    }
    else // recursive case
    {
      // move the tower on top of diskNum to the spare peg
      /* VI. your code to do this goes here */
      moveTower(diskNum-1, from, via, to);

      // then move the bigger disk to the destination peg
      System.out.println("move disk " + diskNum + " to peg " + HANOI_PEGS.substring(to, to+1));
      /* VII. Your code to do this goes here */
      gameState.get(to).add(gameState.get(from).remove(gameState.get(from).size()-1));

      // then move the tower from spare peg to target peg
      /* VIII. Your code to do this goes here */
      moveTower(diskNum-1, via, to, from);
    }
  }
}
