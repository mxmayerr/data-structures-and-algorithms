
public class MatrixTester {

    public static void main(String[] args) {
        

        double[][] arr1 = {{-2,3,4,5}, {-5,4,6,4}, {2,-3,8,2}, {1,2,3,4}};
        // double[][] arr1 = {{5,2,3,4,5}, {1,2,3,4,5}, {1,2,100,4,5}, {1,2,3,4,5}, {1,2,3,4,5}};
        double[][] arr2 = {{5,6}, {7,8}};
        double[][] arr3 = {{5,6}, {7,8}};

        SquareMatrix A = new SquareMatrix(arr3);
        SquareMatrix B = new SquareMatrix(arr2);
        SquareMatrix I = new SquareMatrix(3);
        SquareMatrix R = new SquareMatrix(3,0,3);
        SquareMatrix M = A.minor(1,1);

        // System.out.println(A);
        // System.out.println(B);
        // System.out.println(I);
        // System.out.println(R);
        // System.out.println(M);

        System.out.println(B.multiply(A));
        
        
    }
    
}
