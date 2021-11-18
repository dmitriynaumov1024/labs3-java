package iw4;

/**
 * Square matrix of doubles.
 * @author Dmitriy Naumov
 */
public class Matrix {
    
    private int size;
    private double[][] data;
    
    /**
     * Create new square matrix of given size. If given size is 0 or negative,
     * IllegalArgumentException is thrown.
     * @param size size of matrix.
     */
    public Matrix (int size) {
        if (size <= 0) {
            throw new IllegalArgumentException
                ("Size must be greater than 0, but found " + size);
        }
        this.data = new double[size][size];
        this.size = size;
    }
    
    /**
     * Set value at given row and column in the matrix. If row or column are 
     * out of range, IllegalArgumentException is thrown.
     * @param row row index
     * @param col column index
     * @param value 
     */
    public void set (int row, int col, double value) {
        if (row >= size || row < 0) {
            throw new IndexOutOfBoundsException (
                String.format("Row index must be in [0..%d] range, but found %d", this.size, row)
            );
        }
        if (col >= size || col < 0) {
            throw new IndexOutOfBoundsException (
                String.format("Column index must be in [0..%d] range, but found %d", this.size, col)
            );
        }
        this.data[row][col] = value;
    }
    
    /**
     * Get value at given row and column in the matrix. If row or column are 
     * out of range, IllegalArgumentException is thrown.
     * @param row row index
     * @param col column index
     * @return value at given row and column
     */
    public double get (int row, int col) {
        if (row >= size || row < 0) {
            throw new IndexOutOfBoundsException (
                String.format("Row index must be in [0..%d] range, but found %d", this.size, row)
            );
        }
        if (col >= size || col < 0) {
            throw new IndexOutOfBoundsException (
                String.format("Column index must be in [0..%d] range, but found %d", this.size, col)
            );
        }
        return this.data[row][col];
    }
    
    /**
     * Multiply this matrix by itself and return a new matrix.
     * @return squared matrix (multiplied by itself)
     */
    public Matrix squared () {
        Matrix result = new Matrix(this.size);
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                for (int k = 0; k < this.size; k++) {
                    result.data[row][col] += this.data[row][k] * this.data[k][col];
                }
            }
        }
        return result;
    }
    
    /**
     * Matrix addition, can be performed on equally sized matrices only.
     * @param other other matrix
     * @return sum of this and other matrix.
     */
    public Matrix plus (Matrix other) {
        if (this.size != other.size) {
            throw new IllegalArgumentException (
                String.format("Matrix sizes must be equal, but got %d and %d", this.size, other.size)
            );
        }
        Matrix result = new Matrix(this.size);
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                result.data[row][col] = this.data[row][col] + other.data[row][col];
            }
        }
        return result;
    }
    
    /**
     * String representation of the matrix.  
     */
    @Override public String toString () {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < this.size; row++) {
            if (row > 0) sb.append("\n");
            for (int col = 0; col < this.size; col++) {
                sb.append (String.format(" %+7g", this.data[row][col]));
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
