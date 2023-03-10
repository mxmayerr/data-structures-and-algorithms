
public class SLLTester 
{
    public static void main(String[] args) 
    {
        // ScoreboardLL s = new ScoreboardLL(5);
        // // s.add(new GameEntry("Max", 5));
        // s.add(new GameEntry("Leo", 7));
        // s.add(new GameEntry("JSI", 3));


        // System.out.println(s.toString());

        SinglyLinkedList<String> one = new SinglyLinkedList<>();

        one.addLast("Hello");
        one.addLast("hi");
        one.addLast("Bro");

        SinglyLinkedList<String> two = new SinglyLinkedList<>();

        two.addLast("Bro");
        two.addLast("help");
        two.addLast("dw");

        
        System.out.println(one.getSize());
     
        
        
        
        
    }
}
