// oublic class widget implements comparable<widget>

public class GameEntry implements Comparable<GameEntry>{

    private String name;
    private int score;

    public GameEntry(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    // get name
    public String getName()
    {
        return name;
    }

    // get score
    public int getScore()
    {
        return score;
    }

    // to string
    public String toString()
    {
        return "(" + name + ", " + score + ")";
    }

    @Override
    public int compareTo(GameEntry other)
    {
        if (score < other.getScore())
        {
            return score - other.getScore();
        }
        else if (score > other.getScore())
        {
            return score - other.getScore();
        }
        return 0;
    }

    // 3.compareTo(5)
    //      
}