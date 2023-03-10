

public class EnglishRuler
{
  // the overall length of the ruler
  private int totalLength;

  // the length of the major tick-marks (inches?)
  // sub-ticks decrease in length by one as the width drops by half
  private int majorTickLength;

  // the character used to create the ruler
  private char tickMark;

  // sets overall ruler length (inches?), and the length of
  // the major tick-marks, which also determines how many
  // subdivisions there will be. The char mark allows the user to
  // set how they want the ruler to look.
  public EnglishRuler(int rulerLength, int tickLength, char mark)
  {
    totalLength = rulerLength;
    majorTickLength = tickLength;
    tickMark = mark;
  }

  // default constructor:
  // ticklength is 3, and the mark is a single dash.
  // This results in a ruler in quarter inches
  public EnglishRuler(int rulerLength)
  { this(rulerLength, 3, '-'); }

  // sets overall ruler length
  public void setRulerLength(int L)
  {
    totalLength = L;
  }

  // sets the major tick length
  public void setMajorLength(int L)
  {
    majorTickLength = L;
  }

  // returns overall ruler length
  public int getRulerLength()
  {
    return totalLength;
  }

  // draws the ruler
  public void drawRuler()
  {
    // 1. draw inch zero line and label it
    drawLine(majorTickLength, 0);

    // 2. for every inch from 1 to the totalLength...
    for (int i = 1; i <= totalLength; i++)
    {
      // 2a. draw interior interval ticks
      drawInterval(majorTickLength - 1);
      // 2b. draw inch j line and label it
      drawLine(majorTickLength, i);
    }
      
  }

  // draws an interval
  // i.e. one sample interval shown below:
  //
  // -
  // --
  // -
  public void drawInterval(int centralLength)
  {
    // if the centralLength is at least 1
    if (centralLength > 1)
    {
      //   a. draw top interval that is one shorter than centralLength
      //   b. draw a label-less central tick line of length centralLength
      //   c. draw bottom interval that is one shorter than centralLength
      drawInterval(centralLength - 1);
      drawLine(centralLength);
      drawInterval(centralLength - 1);
    }
    // else central length is 1, print a single tick mark
    else
    {
      System.out.println(tickMark);
    }
    
  }

  // draws a line consisting of tickLength marks.
  // Also labels the line if tickLabel is non-negative
  public void drawLine(int tickLength, int tickLabel)
  {
    // a. draw tickLength tickMarks on the same line
    String s = "";
    for (int i = 0; i < tickLength; i++)
    {
      s += tickMark;
    }
    // b. if tickLabel >= 0, draw it after a space
    if (tickLabel >= 0)
    {
      s += " " + tickLabel;
    }
    // c. Draw a newline character
    System.out.println(s);
  }

  // overloaded to draw label-less line
  public void drawLine(int tickLength)
  {
    // same as above, but without the label
    drawLine(tickLength, -1);
  }

  @Override
  public String toString()
  {
    // should return a String that says
    // "ruler of length 3 subdivided into 1/4ths",
    // replacing the 3 and 4 as appropriate
    return "ruler of length " + totalLength + " subdivided into " + Math.round(Math.pow(2, majorTickLength-1)) + "ths";
    
    
  }
}
