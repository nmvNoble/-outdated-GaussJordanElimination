package gaussjordanelimination;

import gaussjordanelimination.Vector;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix {
    private List<Vector> arrayList;
    private int dimension;

    public Matrix(int dimension)
	{
            this.dimension = dimension;
	}
	
    public Matrix(Vector[] arrayList, int dimension)
    {
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
	
    public void times(Matrix B)
    {
        Matrix A = this;

        double b[][] = new double[B.dimension][B.dimension];
        b = ListTo2DArray(B.arrayList,B.dimension);
        double a[][] = new double[A.dimension][A.dimension];
        a = ListTo2DArray(A.arrayList, A.dimension);

        int aColSize = a[0].length; //matrix A columns length
        int bRowSize = b.length; //matrix B row length

        if(aColSize != bRowSize)
        {
            System.out.println("Incompatible matrix size.");
        }

        int matrixRowSize = a.length; //final matrix row length
        int matrixColSize = b[0].length; //final matrix column length

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
	
	//inverse function doesnt work properly yet due to the return value "x" not being a Vector still trying to fix and still needs GJ
//	public Matrix inverse()
//	{
//		
//		Matrix A = this;
//		
//		int N = A.dimension;
//		
//		double x[][] = new double[N][N];
//		double a[][] = new double[N][N];
//		a = ListTo2DArray(A.arrayList,N);
//		double b[][] = new double[N][N];
//		
//		int it[] = new int[N];
//		
//		for(int i = 0; i < N; i++)
//		{
//			b[i][i] = 1; 
//		}
//		
//		//needs Gauss_jordan for the upper triangle of the matrix
//		//Gauss_Jordan(A.getArrayList, A.dimension, constants)
//		
//		for(int i = 0; i < N; i++)
//		{
//			for(int j = i+1; j < N; j++)
//			{
//				for(int k = 0; k < N; k++)
//				{
//					b[it[j]][k] -= a[it[j]][i]*b[it[i]][k];
//					
//				}
//			}
//		}
//		
//		for(int i = 0; i < N; i++)
//		{
//			x[N-1][i] = b[it[N-1]][i]/a[it[N-1]][N-1];
//			for(int j = N-2; j >= 0; j--)
//			{
//				x[j][i] = b[it[j]][i];
//				for(int k = j+1; k < N; k++)
//				{
//					x[j][i] -= a[it[j]][k]* x[k][i];
//				}
//				x[j][i] /= a[it[j]][j];
//			}
//			
//		}
//		
//		
//		
//		return x; //helb how to transfer x into a Vector variable ooOf
//
//	}

	
	
	
	
}
