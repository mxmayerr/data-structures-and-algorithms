import java.util.Random;
import java.util.Arrays;

public class arr {

    public static void main(String[] args) {

        Integer[] arr = {1,2,3,4,5,6};
        System.out.println(Arrays.toString(arr));

        removeRandomElement(arr);
    
        

        System.out.println(Arrays.toString(arr));
        
        
        
    }


    public static Integer removeRandomElement(Integer[] arr)
    {
        if (arr.length == 0)
        {
            return null;
        }
        Integer[] result = new Integer[arr.length-1];
        Random r = new Random();
        int index = (int) (r.nextInt(0,arr.length));
        int removed = arr[index];

        for (int i = 0; i < index; i++)
        {
            result[i] = arr[i];
        }

        for (int x = index + 1; x < arr.length; x++)
        {
            result[x-1] = arr[x];
        }
        result = arr;
        return removed;
    }
    
}
