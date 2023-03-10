/*
 * SquareMatrix class that inherits from Matrix class
 * Data Structures & Algorithms 2022
 * MCM '23
 */

import java.util.Arrays;

public class SquareMatrix extends Matrix
{   
    // -------- CONSTRUCTORS --------
    // creates n by n identity matrix
    public SquareMatrix(int n)
    {
        super(n);
    }

    // creates n by n matrix with random values in range [min, max)
    public SquareMatrix(int n, int min, int max)
    {
        super(n, n, min, max);
    }

    // creates square matrix from double array
    public SquareMatrix(double[][] m) throws IllegalArgumentException
    {
        super(m);
        if (m.length != m[0].length)
        {
            throw new IllegalArgumentException("Array Not Square");
        }
    }

    // creates a copy of other matrix
    public SquareMatrix(SquareMatrix other)
    {
        super(other.getElements());
    }

    // creates n by n matrix filled with val
    public SquareMatrix(int n, double val)
    {
        super(n);
        double[][] elements = getElements();
        for (int r = 0; r < elements.length; r++)
        {
            for (int c = 0; c < elements[r].length; c++)
            {
                elements[r][c] = val;
            }
        }
    }

    // -------- METHODS --------
    // get size of matrix
    public int size()
    {
        return getRows();
    }

    // get minor of this matrix 
    // (matrix with row r and column c removed)
    public SquareMatrix minor(int r, int c)
    {
        // -------- !! NOTE !! ------
        // this method relies on the continue keyword built into java
        // this keyword basically skips the current iteration and 
        // continues onto the next iteration
        // this is NOT the same as the break keyword, which
        // exits/breaks the ENTIRE loop, not just one iteration

        // load the current matrix into an array
        double[][] elements = getElements();
        // create the new array with one less row and col
        int len = elements.length;
        double[][] result = new double[len-1][len-1];

        // then, we need to loop through values, and if they equal the r or c value entered by the user
        // we need to skip
        // to do this, we must keep count variables (xInd and yInd) so we place elements in correct spot in new array

        // create xInd at 0 (this will keep track of proper x index)
        int xInd = 0;
        // loop through all x (row) values in matrix
        for(int x = 0; x < len; x++)
        {
            // if we are at the forbidden row, skip to next iteration
            // *it is ESSENTIAL that this continue statement is BEFORE the adding and incrementation of the element and variable
            if (x == r)
                continue;
            // define the correct y placement (yInd)
            int yInd = 0;
            // then, loop through the cols in the matrix
            for(int y = 0; y < len; y++)
            {
                // again, if we are at the forbidden col, skip to next iteration
                // same as above, its essential continue is placed here
                if (y == c)
                    continue;
                
                // at this point, we can place the current element in the result array (make sure to
                // place in correct position using variables xInd and yInd)
                result[xInd][yInd] = elements[x][y];
                // increment yInd as we are at the end of the loop
                yInd++;
            }
            // and the same for x
            xInd++;
        }
        // lastly, return the new square matrix
        return new SquareMatrix(result);
    }

    // get determinant of this matrix
    public double determinant()
    {
        // accumulator variable
        double total = 0;
        // sign variable
        int sign = 1;
        // base care of size of matrix is one, just return the value inside
        if (size() == 1)
            return getElement(0, 0);
        

        // consider only the top row
        for (int c = 0; c < size(); c++)
        {
            // else, we need to multiply by the minor determinant
            total += sign * getElement(0,c) * minor(0,c).determinant();
            sign = sign * -1;
        }
        return total;
    }

    // get cofactor matrix of this matrix
    public SquareMatrix coFactor()
    {
        // import elements and create result array
        double[][] result = new double[size()][size()];
        int sign = 1;

        for (int r = 0; r < size(); r++)
        {
            for (int c = 0; c < size(); c++)
            {
                result[r][c] = sign * minor(r,c).determinant();
                sign *= -1;
            }
            sign *= -1;
        }
        return new SquareMatrix(result);
    }

    // get adjoint matrix of this matrix
    public SquareMatrix adjoint()
    {
        Matrix temp = new Matrix(coFactor().getElements());
        temp = temp.transpose();
        return new SquareMatrix(temp.getElements());
    }

    // get inverse of this matrix
    // throws exception if matrix is not invertible
    public SquareMatrix inverse()
    {
        // first, find the determinant
        double det = determinant(); 
        // if the determinant is 0, then the matrix is not invertible
        if (det == 0)
            throw new IllegalArgumentException("Matrix is not invertible");
        // else, we need to find the adjoint
        SquareMatrix adj = adjoint();
        // then, we need to divide each element by the determinant
        double[][] elements = adj.getElements();
        for (int r = 0; r < elements.length; r++)
        {
            for (int c = 0; c < elements[r].length; c++)
            {
                elements[r][c] /= det;
            }
        }
        // then, return the new matrix
        return new SquareMatrix(elements);
    }


}
