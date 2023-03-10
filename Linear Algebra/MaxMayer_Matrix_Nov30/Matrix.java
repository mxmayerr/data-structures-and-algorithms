/*
* Matrix Class
* Data Structures & Algorithms, November 2022
* By MCM '23
*/


import java.util.Arrays;
import java.util.Random;

public class Matrix 
{

    // --------------- INSTANCE FIELDS ----------------
    // matrix info itself
    private double[][] elements;
    private int numRows, numCols;
    // parameters used for displaying matrix:
    private int precision, fieldWidth;

    // ------------------ CONTRUCTORS -------------------

    // DEFAULT, takes 2d array matrix and creates matrix
    public Matrix(double[][] m)
    {
        precision = 1;
        fieldWidth = 6;
        numRows = m.length;
        numCols = m[0].length;
        elements = new double[numRows][numCols];
        // we need to iterate through every row of the 2d array
        for (int r = 0; r < m.length; r++)
            elements[r] = Arrays.copyOf(m[r], m[r].length);
    }

    // matrix but takes number of rows and cols, with random values between min and max
    public Matrix(int rows, int cols, int min, int max)
    {
        // create a new random
        Random rand = new Random();
        // create new matrix with given rows and cols
        elements = new double[rows][cols];
        // RMT to fill with values
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                elements[r][c] = rand.nextInt(min,max);
            }
        }
        // initialize the other values
        precision = 1;
        fieldWidth = 6;
        numRows = rows;
        numCols = cols;

    }

    // matrix but creates identity matrix of given size
    public Matrix(int n)
    {
        precision = 1;
        fieldWidth = 6;
        numRows = n;
        numCols = n;
        elements = new double[numRows][numCols];
        // we need to iterate through every row of the 2d array
        int i = 0;
        while (i < n)
        {
            elements[i][i] = 1;
            i++;
        }
    }

    // ---------------- METHODS ----------------

    public void setPrecision(int p) {precision = p;}

    public int getPrecision() {return precision;}

    public void setFieldWidth(int w) {precision = w;}

    public int getFieldWidth() {return fieldWidth;}

    public double[][] getElements() {return elements;}

    public double getElement(int r, int c) {return elements[r][c];}

    public int getRows()
    {
        return numRows;
    }

    public int getCols()
    {
        return numCols;
    }

    public void setElement(int r, int c, double val) {elements[r][c] = val;}

    public boolean isSquare() {return numCols == numRows;}

    public String toString()
    {
        String format = "%" + fieldWidth + "." + precision + "f";
        String result = "";
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                // result += elements[r][c] + " ";
                result += String.format(format, elements[r][c]);
            }
            result += "\n";
        }
        return result;
    }

    // returns new matrix that is result of adding matrix to M
    // precondition: M has the same dimensions as this matrix
    public Matrix add(Matrix M) throws IllegalArgumentException
    {
        if (numRows != M.numRows || numCols != M.numCols)
        {
            throw new IllegalArgumentException("Matricies incompatible for addition");
        }
        // create a new 2d array that has the same dimensions 
        double[][] sum = new double[numRows][numCols];
        // traverse the matrix and add corresponding elements to store in result array
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                sum[r][c] = elements[r][c] + M.elements[r][c];
            }
        }
        return new Matrix(sum);
    }

    // returns a new matrix that is the result of subtracting M from this matrix
    public Matrix subtract(Matrix M) throws IllegalArgumentException
    {
        if (numRows != M.numRows || numCols != M.numCols)
        {
            throw new IllegalArgumentException("Matricies incompatible for addition");
        }
        // create a new 2d array that has the same dimensions 
        double[][] diff = new double[numRows][numCols];
        // traverse the matrix and add corresponding elements to store in result array
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                diff[r][c] = elements[r][c] - M.elements[r][c];
            }
        }
        return new Matrix(diff);
    }

    // returns a new matrix that is the transpose of this matrix
    public Matrix transpose()
    {
        // create new array
        double[][] trans = new double[numCols][numRows];
        // RMT 
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                trans[c][r] = elements[r][c];
            }
        }
        return new Matrix(trans);
    }

    // calculates the dot product from row r in this matrix and c in other matrix
    private double dotproduct(int r, int c, Matrix other) throws IllegalArgumentException
    {
        // throw exeption if r and c arent same length 
        // if this row isnt same length as col of other
        if (this.numCols != other.numRows)
            throw new IllegalArgumentException("Row and Coloum not same length - not valid for dot product");
        // otherwise, create a sum variable and calculate product
        double sum = 0;
        // for each element in the rows
        for (int i = 0; i < elements[r].length; i++)
        {
            sum += elements[r][i] * other.elements[i][c];
        }
        return sum;
    }


    // multiplies matrixs by a scalar, returns a new matrix
    public Matrix multiply(double s)
    {
        // create the new array
        double[][] result = new double[this.numRows][this.numCols];
        // for every value in thie elements, multiply it and add it to the result one
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                result[r][c] = s * elements[r][c];
            }
        }
        return new Matrix(result);
    }

    // multiplies matrixs by another matrix
    // num of cols in this equals num of rows on other
    public Matrix multiply(Matrix other) throws IllegalArgumentException
    {
        // check if the two matricies can multiply
        if (this.numCols != other.numRows)
            throw new IllegalArgumentException("Matricies cannot be multiplied");
        // create the new array with rows of this and cols of that
        double[][] result = new double[this.numRows][other.numCols];
        // we need to go through every element of this new result array
        for (int r = 0; r < this.numRows; r++)
        {
            for (int c = 0; c < other.numCols; c++)
            {
                result[r][c] = dotproduct(r, c, other);
            }
        }
        return new Matrix(result);
    }

    // determines if matricies are equal if values
    // are within epsilon of each other
    public boolean equals(Matrix other, double epsilon)
    {
        // first check if matricies are same size
        if (other.numRows != numRows || other.numCols != numCols)
            return false;

        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                if (Math.abs(elements[r][c] - other.getElement(r,c)) <= epsilon)
                    return false;
            }
        }
        return true;
    }

    // same as above but epsilon is default value
    public boolean equals(Matrix other)
    {
        double eps = 0.000001;
        return equals(other, eps);
    }

    


}