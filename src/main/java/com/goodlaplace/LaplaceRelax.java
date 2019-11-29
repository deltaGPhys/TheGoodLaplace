package com.goodlaplace;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class LaplaceRelax {

    private int dimension;
    private double[][] world;
    private SimpleWindow displayWindow;
    private double maxHeight = 100;

    public LaplaceRelax() {

    }

    public LaplaceRelax(int dimension, int boxSize, double topLeft, double topRight, double bottomLeft, double bottomRight) {
        this.displayWindow = new SimpleWindow(dimension, boxSize);
        this.dimension = dimension;
        this.world = createStartBorders(this.dimension, topLeft, topRight, bottomLeft, bottomRight);
     }

    public double getMaxHeight() {
        return maxHeight;
    }

    public double[][] createRandomStart(Integer dimension) {
        double[][] array = new double[dimension][dimension];
        IntStream.range(0,dimension)
            .forEach(row -> IntStream.range(0,dimension)
            .forEach(col -> array[col][row] = new Random().nextDouble()*this.maxHeight));
        return array;
    }

    public double[][] createStartBorders(Integer dimension, double topLeft, double topRight, double bottomLeft, double bottomRight) {
        double[][] array = new double[dimension][dimension];
        IntStream.range(0,dimension).forEach(row -> Arrays.fill(array[row],0.0));
        array[0][0] = topLeft;
        array[0][dimension-1] = topRight;
        array[dimension-1][0] = bottomLeft;
        array[dimension-1][dimension-1] = bottomRight;
        //top and bottom sides
        for (int i = 1; i < dimension-1; i++) {
            array[0][i] = topLeft + (topRight-topLeft)*i/dimension;
            array[dimension-1][i] = bottomLeft + (bottomRight-bottomLeft)*i/dimension;
        }
        //left and right sides
        for (int i = 1; i < dimension-1; i++) {
            array[i][0] = topLeft + (bottomLeft-topLeft)*i/dimension;
            array[i][dimension-1] = topRight + (bottomRight-topRight)*i/dimension;
        }
        return array;
    }

    public double[][] simulate(Integer maxGenerations) {
        double[][] current = this.world;
        double[][] next = new double[this.dimension][this.dimension];
        for (int gen = 1; gen < maxGenerations; gen++) {
            this.displayWindow.display(current, gen,this.maxHeight);

            for (int i = 0; i < this.dimension; i++) {
                for (int j = 0; j < this.dimension; j++) {
                    next[i][j] = determineCellState(i,j,current);
                }
            }

            copyAndZeroOut(next, current);
            this.displayWindow.sleep(125);
        }
        return current;
    }

    public void copyAndZeroOut(double[][] next, double[][] current) {
        for (int i = 1; i < next[0].length-1; i++) {
            current[i] = Arrays.copyOf(next[i],next[i].length);
            for (int j = 1; j < next[0].length-1; j++) {
                next[i][j] = 0.0;
            }

        }
    }

    public double determineCellState(int row, int col, double[][] world) {
        int dim = this.dimension; // just to control the length of the expressions below :)
        if (row*col == 0 || row == dim-1 || col == dim-1) { // don't change the borders
            return world[row][col];
        }

        double neighborSum =
                world[row][(col+1)%dim] + world[row][(dim+col-1)%dim] +
                world[(row+1)%dim][col] + world[(dim+row-1)%dim][col] +
                world[(row+1)%dim][(col+1)%dim] + world[(dim+row-1)%dim][(col+1)%dim] +
                world[(row+1)%dim][(dim+col-1)%dim] + world[(dim+row-1)%dim][(dim+col-1)%dim];
        double neighborAvg = neighborSum/8;

        return neighborAvg;
    }


    public void printMatrix(double[][] array) {
        for (double[] row : array) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println("");
        }
    }
}
