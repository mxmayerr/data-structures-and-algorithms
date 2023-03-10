


public class RulerTester {

    public static void main(String[] args) {
        
        // create a new EnglishRuler object
        EnglishRuler ruler = new EnglishRuler(1, 5, '_');
        
        // print the ruler
        ruler.drawRuler();
        System.out.println(ruler.toString());
    }
    
}
