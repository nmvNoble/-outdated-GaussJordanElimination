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
        printVector(this);
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

    @Override
    public String toString() {
        return "Vector{" + "arrayList=" + arrayList + '}';
    }

    public static void printVector(Vector V) {
        List<Double> vectorList = V.getArrayList();
        System.out.println("\nVector: ");
        for (int i = 0; i < vectorList.size(); i++)
            System.out.print("vL[" + i + "]: " + vectorList.get(i)+" ");
        System.out.println();
    }
    
    public static List<Vector> transposeList(List<Vector> vectors, int dimension){
        double[] tmp = new double[dimension];
        Vector e = new Vector(tmp, dimension);
        List<Vector> transpose = new ArrayList<>();
        double val;
        for (int t=0; t<tmp.length; t++)
            transpose.add(e);
        for(int i=0;i<dimension;i++){
            for (int j = 0; j < dimension; j++) {
                List<Vector> temp = new ArrayList<>();
                for (int t = 0; t < tmp.length; t++) {
                    temp.add(e);
                }
                //System.out.print("\nplacing value in transpose " +i + ")");
                val = vectors.get(j).arrayList.get(i);
                temp.get(i).arrayList.set(j, val);
                //val2 = temp.get(i).arrayList.get(j);
                //transpose.get(i).arrayList.set(j, val2);
                System.out.print(temp.get(i).arrayList.get(j)+" ");
            }
            System.out.println();
            //System.out.print("\nResult of transpose.get{"+i+")");
            printVector(transpose.get(i));//This one works fine
        }
        for(int i=0;i<dimension;i++){
            System.out.print("\nFinal transpose.get{"+i+")");
            printVector(transpose.get(i));//This one prints only the last
        }
        return transpose;
    }
    
    public static Vector Gauss_Jordan(List<Vector> vectors, int dimension, Vector constants) {
        
        
        List<Vector> GJ = new ArrayList<Vector>();
        GJ = vectors;
        //GJ = transposeList(vectors, dimension);
        System.out.println("Dimension: "+dimension);
        for(int i=0;i<dimension;i++){
            //System.out.print("\nGJ Final transpose.get{"+i+")");
            //printVector(GJ.get(i));
        }
        
        int down=0;
        for(int i=0;i<dimension;i++){
            down = i;
            System.out.println("Down: "+down+"----------------------------------------------------");
            for (int j = i; j < dimension; j++) {
                System.out.println("\ni: "+i+", j: "+j+" ==================================================");
                //printVector(GJ.get(j));
                if(i == j && GJ.get(j).arrayList.get(i)!=1){
                    constants.arrayList.set(j, constants.arrayList.get(j)*(1/GJ.get(j).arrayList.get(i)));
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).arrayList.get(i)));
                    System.out.println("Constant: "+constants.arrayList.get(j)+" = "+constants.arrayList.get(j)+"*"+(1/GJ.get(j).arrayList.get(i)));
                    //printVector(GJ.get(j));
                }
                double tempScalar=0, tempConstantScalar=0;
                if(i != j && GJ.get(j).arrayList.get(i)!=0){
                    //System.out.println(""+i+" < "+j+" && "+GJ.get(j).arrayList.get(i)+"!=0");
                    tempScalar = -(GJ.get(j).arrayList.get(i)*GJ.get(i).arrayList.get(i));
                    System.out.println(tempScalar+" = -("+GJ.get(j).arrayList.get(i)+"*"+GJ.get(i).arrayList.get(i)+")");
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    //System.out.println(GJ.get(i) + " = " + GJ.get(i) + "*" + tempScalar);
                    constants.arrayList.set(i, (constants.arrayList.get(i) * tempScalar));
                    System.out.println(constants.arrayList.get(i) + " = " + constants.arrayList.get(i) + "*" + tempScalar);
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    constants.arrayList.set(j, constants.arrayList.get(j) + constants.arrayList.get(i));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    constants.arrayList.set(i, (constants.arrayList.get(i) * (1/tempScalar)));
                    printVector(GJ.get(j));
                }
                    System.out.println("\n - = < A F T E R > = - ");
                    for (int x = 0; x < dimension; x++) {
                        System.out.print("\nGJ Final GJ.get{" + x + ")");
                        printVector(GJ.get(x));
                    }
                    System.out.print("\nConstants: ");
                    printVector(constants);
            }
        }
            System.out.println("Down: "+down+"----------------------------------=====================================------------------");
        for(int i=down;i>=0;i--){
            for (int j = i; j >= 0; j--) {
                System.out.println("\ni: "+i+", j: "+j+" ============================================Upwards");
                //printVector(GJ.get(j));
                if(i == j && GJ.get(j).arrayList.get(i)!=1){
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).arrayList.get(i)));
                    //printVector(GJ.get(j));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).arrayList.get(i)!=0){
                    //System.out.println(""+i+" < "+j+" && "+GJ.get(j).arrayList.get(i)+"!=0");
                    tempScalar = -(GJ.get(j).arrayList.get(i)*GJ.get(i).arrayList.get(i));
                    System.out.println(tempScalar+" = -("+GJ.get(j).arrayList.get(i)+"*"+GJ.get(i).arrayList.get(i)+")");
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    //System.out.println(GJ.get(i) + " = " + GJ.get(i) + "*" + tempScalar);
                    constants.arrayList.set(i, (constants.arrayList.get(i) * tempScalar));
                    System.out.println(constants.arrayList.get(i) + " = " + constants.arrayList.get(i) + "*" + tempScalar);
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    constants.arrayList.set(j, constants.arrayList.get(j) + constants.arrayList.get(i));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    constants.arrayList.set(i, (constants.arrayList.get(i) * (1/tempScalar)));
                    
                    printVector(GJ.get(dimension-1-j));
                }
                    System.out.println("\n - = < A F T E R > = - ");
                    for (int x = 0; x < dimension; x++) {
                        System.out.print("\nGJ Final GJ.get{" + x + ")");
                        printVector(GJ.get(x));
                    }
                    System.out.print("\nConstants: ");
                    printVector(constants);
            }
        }
        
        
            System.out.println("\n\nyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeet");
        
        for(int i=0;i<dimension;i++){
            System.out.print("\nGJ Final GJ.get{"+i+")");
            printVector(GJ.get(i));
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

