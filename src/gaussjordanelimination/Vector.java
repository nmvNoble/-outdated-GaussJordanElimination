package gaussjordanelimination;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 *
 * Klaudia Gaia Borromeo
 * Neil Michael Noble
 * Iris Libay
 * 
 * ADVDISC X22
 */

public class Vector {

    private List<Double> arrayList;
    private int dimension;

    public Vector(int dimension)
    {
        this.dimension = dimension;
    }

    public Vector(double[] array, int dimension)
    {
        this.arrayList = Arrays.stream(array).boxed().collect(Collectors.toList());
        this.dimension = dimension;
    }

    public Vector add(Vector addend)
    {
        double[] arr =  new double[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++)
        {
            arr[i] = arrayList.get(i) + addend.getArrayList().get(i);
        }

        return new Vector(arr, dimension);
    }

    public Vector scale(double scalar)
    {
        double[] arr =  new double[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++)
        {
            arr[i] = arrayList.get(i) * scalar;
        }

        return new Vector(arr, dimension);
    }

    public List<Double> getArrayList()
    {
        return this.arrayList;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "arrayList=" + arrayList +
                '}';
    }

    public static double[][] ListTo2DArray(List<Vector> vectors, int dimension, Vector constants){
        double[][] A = new double [dimension][dimension];
        for(int i = 0; i < dimension - 1; i++)
            for(int j = 0; j < dimension; j++)
                if (j < dimension - 1) 
                    A[i][j] = vectors.get(j).getArrayList().get(i);
                else 
                    A[i][j] = constants.getArrayList().get(i);
        return A;
    }

    public static void printConstants(Vector constants) {
        List<Double> constantsList = constants.getArrayList();
        System.out.println("\nConstants: ");
        for (int i = 0; i < constantsList.size(); i++)
            System.out.println("C[" + i + "]: " + constantsList.get(i));
    }
    
    public static void printMatrix(double[][] A, int dimension)
    {
    	   for(int z = 0; z < dimension - 1; z++)
           {
               for(int y = 0; y < dimension; y++){
                   //System.out.print("("+z+", "+y+") ");
                   System.out.printf("%.2f ", A[z][y]);
               }
               System.out.println();
           }
    }
    
    public static void printSpan(int span)
    {
    	System.out.println("\nSpan: "+ span);
    }

    public static Vector Gauss_Jordan(List<Vector> vectors, int dimension, Vector constants) {
        double[][] A = new double [dimension][dimension];
        double[] cnst = new double[dimension - 1];
        
        System.out.println("Initial: ");
        A = ListTo2DArray(vectors,dimension,constants);
        printMatrix(A,A.length);
        System.out.println();
     

        for(int j = 0; j < dimension - 1; j++)
        {
            for(int i = 0; i < dimension; i++)
            {
                if(i != j && A[j][j]!=0)
                {
                    double c = A[i][j]/A[j][j];
                    for(int k = 0; k < dimension; k++)
                        A[i][k] = A[i][k] - c*A[j][k];
                }
                //printMatrix(A, dimension);
                //System.out.println();
            }
            //System.out.println();
        }
        //printMatrix(A, dimension);
        double[] swap = new double[dimension];
        for(int j = 0; j < dimension-1; j++)
            for(int i = j+1; i < dimension-1; i++)
                if(A[j][j]==0&&A[i][j]==1)
                    for (int s = 0; s < dimension; s++){
                        swap[s]=A[j][s];
                        A[j][s]=A[i][s];
                        A[i][s]=swap[s];
                    }
        
        //System.out.println("Swapped:  ");        
        //printMatrix(A, dimension);
        for(int i = 0; i < (dimension-1); i++){
                //System.out.println("("+i+", "+i+") "+A[i][i]+" cnst: "+cnst[i]);
            if(A[i][i]!=0)
                cnst[i] = A[i][dimension - 1]/A[i][i];
            else if(A[i][i]==0 &&A[i][dimension-1]!=0){
                cnst[i] = A[i][i]/A[i][i]; //to represent that solution is wrong and make output NaN
                System.out.println("Solution NULL");
            }
        }
        
        constants = new Vector(cnst, dimension);
        for(int j = 0; j < dimension -1; j++)
            for(int i = 0; i < dimension; i++)
                if(i == j && A[i][j]!=0)
                    A[i][j] /= A[i][j];
        
        System.out.println("\nPost-GJE: ");
        printMatrix(A, dimension);
        return constants;
    }
    
    

    public static int span(List<Vector> vectors, int dimension)
    {
        double[][] A = new double [dimension][dimension];
  	for(int i = 0; i < dimension - 1; i++)
            for(int j = 0; j < dimension; j++)
            	if (j < dimension - 1) 
                    A[i][j] = vectors.get(j).getArrayList().get(i);
        int span = 0;
        for(int j = 0; j < dimension - 1; j++)
        {
            for(int i = 0; i < dimension; i++)
            {
                if(i != j && A[j][j]!=0)
                {
                    double c = A[i][j]/A[j][j];
                    for(int k = 0; k < dimension; k++)
                        A[i][k] = A[i][k] - c*A[j][k];
                }
            }
        }
        for(int j = 0; j < dimension -1; j++)
            for(int i = 0; i < dimension; i++)
                if(i == j && A[i][j]!=0)
                    A[i][j] /= A[i][j];
        for(int j = 0; j < dimension -1; j++)
            for(int i = 0; i < dimension; i++)
                if(A[i][j] == 1)
                    span++;
        return span;
    }
}
