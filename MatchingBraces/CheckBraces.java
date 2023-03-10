/**
  * This program reads a text file (specified in a
  * command-line argument) into a string named inString
  * and checks the braces in that file.
  *
  * To execute, assuming an input file is named 'filename'
  * and exists in the same directory as CheckBraces.class, type
  *
  *   java CheckBraces filename
  *  
  */

import java.io.*;

public class CheckBraces {

  // Reads a file into a String and returns that String.
  public static String readFile(String fileName) throws IOException
  {
    File f = new File(fileName);
    InputStreamReader inStream = new InputStreamReader(new FileInputStream(f));
    int length = (int) f.length();
    char input[] = new char[length];
    inStream.read(input);
    return (new String(input));
  }

  // Prints out a String character by character.
  public static void print(String inString)
  {
    for (int k = 0; k < inString.length(); k++)
      System.out.print(inString.charAt(k));
    System.out.println();
  }

  public static void main(String args[])
  {
    String inString = null;

    // if no input file named, then prints warning and ends
    if (args.length < 1)
    {
      System.out.println("Usage: java CheckBraces sourcefile");
      return;
    }

    // Read the file provided as the command-line argument
    try
    { inString = readFile(args[0]); }
    catch (FileNotFoundException e)
    {
      // file not present in same directory
      System.err.println("Error: File " + args[0] + " not found");
      e.printStackTrace();
    }
    catch (IOException e)
    {
      System.err.println("Error: I/O exception");
      e.printStackTrace();
    }

    // print out the file for viewing purposes
    print(inString);

    // Check braces
    checkBraces(inString);
  }

  /**
   * Check Braces method:
   * checks if all braces are balanced,
   * terminates upon finding an error, 
   * and prints to the screen where the error is, 
   * otherwise prints "Braces are balanced"
   * @param inputFile the string to be checked
   * @return void
   */
  public static void checkBraces(String inputFile)
  {
    // create an empty stack
    LinkedStack<String> stack = new LinkedStack<String>();
    // create a counter variable to keep track of character number
    // (start at -1 as we increment at beggining of loop)
    int counter = -1;
    // parse the file into an array of lines and create line counter
    String[] lines = inputFile.split("\r\n|\r|\n");
    int lineCounter = 0;

    // loop through the file to add braces to the stack
    // for each line in lines
    for (String line : lines)
    {
      // increment line counter
      lineCounter++;

      // for each character in the line
      for (int i = 0; i < line.length(); i++)
      {
        // increment the character counter
        counter++;

        // get current character
        String s = line.substring(i, i + 1);

        // if the current character is an opening brace, push it onto the stack
        if (s.equals("{") || s.equals("[") || s.equals("("))
        {
          stack.push(s);
        }

        // else if the current character is a closing brace, we need to check what type of error it is
        else if (s.equals("}") || s.equals("]") || s.equals(")"))
        {
          // MISSING BRACE ERROR
          // if the stack is empty, then there is a missing brace error, and terminate the method
          if (stack.isEmpty())
          {
            // (we need to add lineCounter to counter as we need to count for new lines, also
            // we need to subtract 1 from the counter as we incremented it at the beginning of the loop)
            System.out.println("Error: brace " + s + " at character " + (counter + lineCounter - 1) + ", line " + lineCounter + " does not match anything in the file");
            return;
          }
          // MATCHING BRACE (Not Error)
          // if the stack is not empty, then check if the top of the stack matches the current character
          else
          {
            // get the top of the stack
            String top = stack.top();

            // if the top of the stack matches the current character, pop it off the stack
            if ((top.equals("{") && s.equals("}")) || (top.equals("[") && s.equals("]")) || (top.equals("(") && s.equals(")")))
            {
              stack.pop();
            }

            // UNMATCHED BRACE ERROR
            // if the top of the stack does not match the current character, then there is a unmatched brace error, and terminate the method
            else
            {
              System.out.println("Error: unmatched brace " + s + " at character " + (counter + lineCounter - 1) + ", line " + lineCounter + ": Found " + s + " expected " + top);
              return;
            }
          }
        }
      }
    }

    // if we reach the end and the stack is empty, then the braces are balanced
    if (stack.isEmpty())
    {
      System.out.println("This file has balanced braces");
    }
  }

}
