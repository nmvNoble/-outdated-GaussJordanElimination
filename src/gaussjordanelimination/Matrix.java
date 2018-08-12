/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordanelimination;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix {
    public List<Vector> arrayList;
    public int dimension;

    public Matrix(int dimension) {
        this.dimension = dimension;
    }

    public Matrix(Vector[] arrayList, int dimension){
        this.arrayList = Arrays.stream(arrayList).collect(Collectors.toList());
        this.dimension = dimension;
    }
	
    public static double[][] ListTo2DArray(List<Vector> vectors, int dimension){
        double[][] matrix = new double [dimension][dimension];
        for(int i = 0; i < dimension - 1; i++)
            for(int j = 0; j < dimension; j++)
                matrix[i][j] = vectors.get(j).getArrayList().get(i);

        return matrix;
    }
	
    public static void times(Matrix A, Matrix B)
    {
        double b[][] = new double[B.dimension][B.dimension];
        b = ListTo2DArray(B.arrayList,B.dimension);
        double a[][] = new double[A.dimension][A.dimension];
        a = ListTo2DArray(A.arrayList, B.dimension);

        int aColSize = a[0].length; //matrix A columns length
        int bRowSize = b.length; //matrix B row length

        if(aColSize != bRowSize)
        {
            System.out.println("Incompatible matrix size.");
        }

        int matrixRowSize = a.length; //final matrix row length
        int matrixColSize = b[0].length; //final matrix col length

        double[][] matrixResult = new double[matrixRowSize][matrixColSize];

        for(int i = 0; i < matrixRowSize; i++)
        {
            for(int j = 0; j < matrixColSize; j++)
            {
                for(int k = 0; k < aColSize; k++)
                {
                    matrixResult[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        for(int i = 0; i < matrixRowSize; i++)
        {
            for(int j = 0; j < matrixColSize; j++)
            {
                System.out.println(matrixResult[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
