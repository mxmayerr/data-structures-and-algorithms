import java.util.ArrayList;

// visualize for towwers of hanoi

public class Visualize {

    // color constants
    public static final String RESET = "\033[0m";  

    // background for red, yellow, green, blue, magenta, cyan, white
    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    public static final String MAGENTA_BACKGROUND = "\033[45m";
    public static final String CYAN_BACKGROUND = "\033[46m";
    public static final String WHITE_BACKGROUND = "\033[47m";

    public static void main(String[] args) {
        
        // the discs
        ArrayList<ArrayList<Integer>> pegs = new ArrayList<ArrayList<Integer>>();

        // the pegs
        for (int i = 0; i < 3; i++) {
            pegs.add(new ArrayList<Integer>());
        }

        // add the discs
        for (int i = 0; i < 7; i++) {
            pegs.get(2).add(i);
        }

        int totalDiscs = pegs.get(0).size() + pegs.get(1).size() + pegs.get(2).size();

        // visualize
        // we want to show the dics on top of each other with different colors for each one

        // first step is to add the availible colors to an array
        String[] colors = {RED_BACKGROUND, GREEN_BACKGROUND, YELLOW_BACKGROUND, BLUE_BACKGROUND, MAGENTA_BACKGROUND, CYAN_BACKGROUND, WHITE_BACKGROUND};

        // then, we can calculate the width of each displayed peg based on the number of total discs
        int width = 2 * totalDiscs + 1;

        // for each layer of the pegs
        for (int i = totalDiscs - 1; i >= 0; i--) {

            // print starting space
            System.out.print("  ");

            // for each peg in the layer
            for (int j = 0; j < pegs.size(); j++) {

                // if the peg has a disc at this layer
                if (pegs.get(j).size() > i) 
                {

                    
                    
                    // get the disc
                    int disc = pegs.get(j).get(pegs.get(j).size()-1-i);

                    // the size of the disc
                    int size = 2 * disc + 1;

                    // the spaces before and after the disc
                    int spaces = (width - size) / 2;

                    // print the spaces before the disc
                    for (int k = 0; k < spaces; k++) {
                        System.out.print(" ");
                    }

                    // print the disc
                    for (int k = 0; k < size; k++) {
                        System.out.print(colors[disc] + " " + RESET);
                    }

                    // print the spaces after the disc
                    for (int k = 0; k < spaces; k++) {
                        System.out.print(" ");
                    }

                } else {

                    // if the peg doesn't have a disc at this layer, print the width of the peg
                    for (int k = 0; k < width; k++) {
                        System.out.print(" ");
                    }

                }

            }

            // print a new line
            System.out.println();

            // for some reason, it will only show the discs if in the first coloum or less than the first coloum
            // what can i do to fix this?

        }

        // print the arraylist
        System.out.println(pegs);





    }

    
}