package gaussjordanelimination;

import java.util.ArrayList;
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

    public int getDimension()
    {
        return this.dimension;
    }

    public List<Double> getArrayList()
    {
        return this.arrayList;
    }

    public Vector add(Vector addend)
    {
        double[] arr =  new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            arr[i] = arrayList.get(i) + addend.getArrayList().get(i);
        return new Vector(arr, dimension);
    }

    public Vector scale(double scalar)
    {
        //System.out.println("\nscale by "+scalar);
        //printVector(this);
        double[] arr =  new double[arrayList.size()];
        //System.out.print("Matrix: ");
        for (int i = 0; i < arrayList.size(); i++){
            arr[i] = arrayList.get(i) * scalar;
            if(arr[i]==-0){
                arr[i]=0;
            }
            //System.out.print(arr[i]+" = "+arrayList.get(i)+"*"+scalar+",||");
        }
        return new Vector(arr, dimension);
    }

    public Vector swap(Vector toSwap)
    {
        double[] arr =  new double[arrayList.size()];
        double temp = 0;
        for (int i = 0; i < arrayList.size(); i++){
            temp = toSwap.getArrayList().get(i);
            toSwap.getArrayList().set(i, arrayList.get(i));
            arr[i] = temp;
            //System.out.print(arr[i]+", ");
            arrayList.set(i, temp);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Vector{" + "arrayList=" + arrayList + '}';
    }

    public static void printVector(Vector V) {
        List<Double> vectorList = V.getArrayList();
        System.out.print("Vector: ");
        for (int i = 0; i < vectorList.size(); i++)
            System.out.print("vL[" + i + "]: " + vectorList.get(i)+" ");
        System.out.println();
    }
    
    public static List<Vector> transposeList(List<Vector> vectors, int dimension){
        //System.out.print(dimension);
        List<Vector> transpose = new ArrayList<>();
        
        for(int i=0;i<dimension;i++){
            double[] tmp = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                tmp[j] = vectors.get(j).arrayList.get(i);
            }
            Vector e = new Vector(tmp, dimension);
                transpose.add(e);
            //System.out.println();
            //System.out.print("Result of transpose.get{"+i+")");
            //printVector(transpose.get(i));
        }
        for(int i=0;i<dimension;i++){
            //System.out.print("\noboiFinal transpose.get{"+i+")");
            //printVector(transpose.get(i));
        }
        return transpose;
    }
    
    public static Vector Gauss_Jordan(List<Vector> vectors, int dimension, Vector constants) {
        
        List<Vector> GJ = new ArrayList<Vector>();
        GJ = transposeList(vectors, dimension);
        System.out.println("Dimension: "+dimension);
        
        int down=0;
        for(int i=0;i<dimension;i++){
            down = i;
            for (int j = i; j < dimension; j++) {
                if(i == j && GJ.get(j).arrayList.get(i)==0){
                    if(i<dimension-1){
                        GJ.get(i).swap(GJ.get(i+1));
                        
                        double tempCon;
                        tempCon = constants.arrayList.get(j+1);
                        constants.arrayList.set(j+1, constants.arrayList.get(j));
                        constants.arrayList.set(j, tempCon);
                        
                    }
                }
                if(i == j && GJ.get(j).arrayList.get(i)!=1){
                    constants.arrayList.set(j, constants.arrayList.get(j)*(1/GJ.get(j).arrayList.get(i)));
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).arrayList.get(i)));
                }
                double tempScalar=0, tempConstantScalar=0;
                if(i != j && GJ.get(j).arrayList.get(i)!=0){
                    tempScalar = -(GJ.get(j).arrayList.get(i)*GJ.get(i).arrayList.get(i));
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    constants.arrayList.set(i, (constants.arrayList.get(i) * tempScalar));
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    constants.arrayList.set(j, constants.arrayList.get(j) + constants.arrayList.get(i));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    constants.arrayList.set(i, (constants.arrayList.get(i) * (1/tempScalar)));
                }
            }
        }
        for(int i=down;i>=0;i--){
            for (int j = i; j >= 0; j--) {
                if(i == j && GJ.get(j).arrayList.get(i)!=1){
                    constants.arrayList.set(j, constants.arrayList.get(j)*(1/GJ.get(j).arrayList.get(i)));
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).arrayList.get(i)));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).arrayList.get(i)!=0){
                    tempScalar = -(GJ.get(j).arrayList.get(i)*GJ.get(i).arrayList.get(i));
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    constants.arrayList.set(i, (constants.arrayList.get(i) * tempScalar));
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    constants.arrayList.set(j, constants.arrayList.get(j) + constants.arrayList.get(i));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    constants.arrayList.set(i, (constants.arrayList.get(i) * (1/tempScalar)));
                }
            }
        }
        return constants;
    }
    
    public static int span(List<Vector> vectors, int dimension)
    {
        double[][] A = new double [dimension][dimension];
        int span = 0;
        
  	for(int i = 0; i < dimension - 1; i++)
            for(int j = 0; j < dimension; j++)
            	if (j < dimension - 1) 
                    A[i][j] = vectors.get(j).getArrayList().get(i);
        
        for(int j = 0; j < dimension - 1; j++)
            for(int i = 0; i < dimension; i++)
                if(i != j && A[j][j]!=0)
                {
                    double c = A[i][j]/A[j][j];
                    for(int k = 0; k < dimension; k++)
                        A[i][k] = A[i][k] - c*A[j][k];
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

