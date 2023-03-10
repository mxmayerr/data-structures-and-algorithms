

public class HanoiRunner
{
  public static void main(String[] args)
  {
    int numberOfDiscs = Integer.parseInt(args[0]);
    String fromPeg = args[1], toPeg = args[2];
    System.out.print("Moving the tower of " + numberOfDiscs);
    System.out.println(" from peg " + fromPeg + " to peg " + toPeg);
    TowersOfHanoi.runTowersOfHanoi(numberOfDiscs, fromPeg, toPeg);
  }
}
