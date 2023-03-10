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
    /*
     * elements: 2d array of doubles
     * numRows: number of rows in matrix
     * numCols: number of columns in matrix
     * precision: number of decimal places to display
     * fieldWidth: width of each field in matrix    
     */
    private double[][] elements;
    private int numRows, numCols;
    private int precision, fieldWidth;

    // ------------------ CONTRUCTORS -------------------
    /*
     * DEFAULT CONSTRUCTOR for Matrix
     * Takes 2d array of doubles and creates a matrix
     */
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

    /*
     * Constructor for Matrix
     * Takes number of rows and columns, and creates a matrix with random values between min and max
     */
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

    /*
     * Constructor for (Identity) Matrix
     * Takes an integer representing the size of the matrix, and creates an identity matrix
     */
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

    /*
     * Sets the precision of the matrix
     * Takes an integer (p) representing the number of decimal places to display
     * Returns nothing
     */
    public void setPrecision(int p) {precision = p;}

    /*
     * Gets the precision of the matrix
     * Takes nothing
     * Returns an integer representing the number of decimal places to display
     */
    public int getPrecision() {return precision;}

    /*
     * Sets the field width of the matrix
     * Takes an integer (w) representing the width of each field in the matrix
     * Returns nothing
     */
    public void setFieldWidth(int w) {precision = w;}

    /*
     * Gets the field width of the matrix
     * Takes nothing
     * Returns an integer representing the width of each field in the matrix
     */
    public int getFieldWidth() {return fieldWidth;}

    /*
     * Gets the elements of the matrix
     * Takes nothing
     * Returns a 2d array of doubles representing the elements of the matrix
     */
    public double[][] getElements() {return elements;}

    /*
     * Gets the element at a given row and column
     * Takes an integer (r) representing the row and an integer (c) representing the column
     * Returns a double representing the element at the given row and column
     */
    public double getElement(int r, int c) {return elements[r][c];}

    /*
     * Gets the number of rows in the matrix
     * Takes nothing
     * Returns an integer representing the number of rows in the matrix
     */
    public int getRows()
    {
        return numRows;
    }

    /*
     * Gets the number of columns in the matrix
     * Takes nothing
     * Returns an integer representing the number of columns in the matrix
     */
    public int getCols()
    {
        return numCols;
    }

    /*
     * Sets the element at a given row and column
     * Takes an integer (r) representing the row, an integer (c) representing the column, and a double (val) representing the value to set the element to
     * Returns nothing
     */
    public void setElement(int r, int c, double val) {elements[r][c] = val;}

    /*
     * Determines if the matrix is square
     * Takes nothing
     * Returns a boolean representing whether the matrix is square
     */
    public boolean isSquare() {return numCols == numRows;}

    /*
     * Shows the string representation of the matrix
     * Takes nothing
     * Returns a string representing the matrix
     */
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

    /*
     * Adds a matrix to this matrix
     * Takes a matrix (M) to add to this matrix
     * Returns a new matrix that is the result of adding M to this matrix
     * Throws an IllegalArgumentException if the dimensions of the two matrices are not the same
     * Precondition: M has the same dimensions as this matrix
     */
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

    /*
     * Subtracts a matrix from this matrix
     * Takes a matrix (M) to subtract from this matrix
     * Returns a new matrix that is the result of subtracting M from this matrix
     * Throws an IllegalArgumentException if the dimensions of the two matrices are not the same
     * Precondition: M has the same dimensions as this matrix
     */
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

    /*
     * Gets the transpose of this matrix
     * Takes nothing
     * Returns a new matrix that is the transpose of this matrix
     */
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

    /*
     * Calculates the dot product of a row in this matrix and a column in another matrix
     * Takes an integer (r) representing the row in this matrix and an integer (c) representing the column in another matrix
     * Returns a double representing the dot product of the row and column
     * Throws an IllegalArgumentException if the number of columns in this matrix is not the same as the number of rows in the other matrix
     * Precondition: the number of columns in this matrix is the same as the number of rows in the other matrix
     */
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

    /*
     * Multiplies this matrix by a scalar
     * Takes a double (s) representing the scalar to multiply this matrix by
     * Returns a new matrix that is the result of multiplying this matrix by the scalar
     */
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

    /*
     * Multiplies this matrix by another matrix
     * Takes a matrix (other) to multiply this matrix by
     * Returns a new matrix that is the result of multiplying this matrix by the other matrix
     * Throws an IllegalArgumentException if the number of columns in this matrix is not the same as the number of rows in the other matrix
     * Precondition: the number of columns in this matrix is the same as the number of rows in the other matrix
     */
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

    /*
     * Determines if this matrix is equal to another matrix with parameter epsilon
     * Takes a matrix (other) to compare to this matrix and a double (epsilon) representing the maximum difference between two elements to be considered equal
     * Returns a boolean representing whether or not this matrix is equal to the other matrix
     */
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

    /*
     * Determines if this matrix is equal to another matrix
     * Takes a matrix (other) to compare to this matrix
     * Returns a boolean representing whether or not this matrix is equal to the other matrix
     * Uses a default epsilon of 0.000001
     */
    public boolean equals(Matrix other)
    {
        double eps = 0.000001;
        return equals(other, eps);
    }

}