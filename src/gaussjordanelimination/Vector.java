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

    public Vector add(Vector addend)
    {
        double[] arr =  new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            arr[i] = arrayList.get(i) + addend.getArrayList().get(i);
        return new Vector(arr, dimension);
    }

    public Vector scale(double scalar)
    {
        double[] arr =  new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            arr[i] = arrayList.get(i) * scalar;
        return new Vector(arr, dimension);
    }

    public List<Double> getArrayList()
    {
        return this.arrayList;
    }

    @Override
    public String toString() {
        return "Vector{" + "arrayList=" + arrayList + '}';
    }

    public static void printVector(Vector V) {
        List<Double> vectorList = V.getArrayList();
        System.out.println("\nVector: ");
        for (int i = 0; i < vectorList.size()-1; i++)
            System.out.print("vL[" + i + "]: " + vectorList.get(i)+" ");
        System.out.println();
    }
    
    public static List<Vector> transposeList(List<Vector> vectors, int dimension){
        double[] tmp = new double[dimension+1];
        List<Vector> transpose = new ArrayList<>();
        Vector e = new Vector(tmp, dimension);
        for (int t=0; t<tmp.length; t++){
            transpose.add(e);
        }
        for(int i=0;i<dimension;i++){
            for (int j = 0; j < dimension; j++) {
                System.out.print("\nplacing value in transpose " +i + ")");
                transpose.get(i).arrayList.set(j, vectors.get(j).arrayList.get(i));
            }
            System.out.print("\nResult of transpose.get{"+i+")");
            printVector(transpose.get(i));
        }
            System.out.print("\nFinal transpose.get{"+0+")");
        printVector(transpose.get(0));
            System.out.print("\nFinal transpose.get{"+1+")");
        printVector(transpose.get(1));
            System.out.print("\nFinal transpose.get{"+2+")");
        printVector(transpose.get(2));
        return transpose;
    }
    
    public static Vector Gauss_Jordan(List<Vector> vectors, int dimension, Vector constants) {
        
        List<Vector> GJ = new ArrayList<Vector>();
        GJ = transposeList(vectors, dimension);
        System.out.println("dfq");
        printVector(GJ.get(0));
        printVector(GJ.get(1));
        printVector(GJ.get(2));
        
        for(int i=0;i<dimension;i++){
            for (int j = 0; j < dimension; j++) {
                if(GJ.get(i).arrayList.get(j)!=0){
                    GJ.get(i).scale(1/GJ.get(i).arrayList.get(j));
                    //printVector(GJ.get(i));
                    //System.out.print(GJ.get(i).arrayList.get(j));
                }
            }
            //System.out.println();
        }
        return constants;
    }
    
    public static int span(List<Vector> vectors, int dimension)
    {
        double[][] A = new double [dimension][dimension];
        int span = 0;
        
  	
        return span;
    }
}
