


public class tester{  
    public static void main(String[] args) {
        
        GameEntry e2 = new GameEntry("Leo", 3);
        GameEntry e3 = new GameEntry("Jason", 7);
        GameEntry e4 = new GameEntry("high", 10);
        GameEntry e1 = new GameEntry("max", 8);

        
        Scoreboard board = new Scoreboard(3);
        board.add(e2);
        board.add(e3);
        board.add(e4);
        board.add(e1);

        System.out.println(board.toString());

        board.remove (1);

        System.out.println(board.toString());

    
        
        




        
        

        

    }

}
