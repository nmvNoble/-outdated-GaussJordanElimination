package gaussjordanelimination;

import gaussjordanelimination.Vector;
import static gaussjordanelimination.Vector.printVector;
import static gaussjordanelimination.Vector.transposeList;
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

public class Matrix {
    private List<Vector> arrayList;
    private int dimension;

    public Matrix(int dimension)
	{
            this.dimension = dimension;
	}
	
    public Matrix(List<Vector> arrayList, int dimension)//Vector[] arrayList, int dimension)
    {
        this.arrayList = arrayList;//Arrays.stream(arrayList).collect(Collectors.toList());
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
        List<Vector> tempList = new ArrayList<Vector>();
        for (int a = 0; a < dimension; a++) {
            double[] temp = new double[dimension];
            for (int b = 0; b < dimension; b++) {
                    temp[b]=0;
            }
            Vector v = new Vector(temp, dimension);
            tempList.add(v);
        }
        
        Matrix C = new Matrix(tempList, dimension);

        for(int i = 0; i < this.dimension; i++){
            for (int j = 0; j < this.dimension; j++) {
                double summation = 0;
                for(int s = 0; s < dimension; s++){
                    //summation += A.arrayList.get(j).getArrayList().get(i).scale(B.arrayList.get(j).getArrayList().get(j));
                }
                C.arrayList.get(j).getArrayList().set(i, summation);

            }
        }

    }

    public double det ( ) {
        List<Vector> vectors = this.arrayList;
        int dimension = vectors.get(0).getDimension();
        
        if(dimension == 2){
            return vectors.get(0).getArrayList().get(0) * vectors.get(1).getArrayList().get(1)
                    - vectors.get(0).getArrayList().get(1)*vectors.get(1).getArrayList().get(0);
        }
        
        List<Vector> GJ = new ArrayList<Vector>();
        double[] storeDet = new double[dimension+1];
        GJ = transposeList(vectors, dimension);
        //System.out.println("Dimension: "+dimension);
        
        int down=0;
        for(int i=0;i<dimension;i++){
            down = i;
            //System.out.println("Down: "+down+"----------------------------------------------------");
            for (int j = i; j < dimension; j++) {
                //System.out.println("\ni: "+i+", j: "+j+" ==================================================");
                //System.out.println("GJ.get("+j+").arrayList.get("+i+") = "+GJ.get(j).getArrayList().get(i)+" ");
                //printVector(GJ.get(j));
                if(i == j && GJ.get(j).getArrayList().get(i)==1){
                    storeDet[j] = GJ.get(j).getArrayList().get(i);
                    
                }
                if(i == j && GJ.get(j).getArrayList().get(i)==0){
                    storeDet[j] = GJ.get(j).getArrayList().get(i);
                    if (i<dimension-1){
                        GJ.get(i).swap(GJ.get(i+1));
                        //printVector(GJ.get(i+1));
                    } 
                }
                if(i == j && GJ.get(j).getArrayList().get(i)!=1){
                    storeDet[j] = GJ.get(j).getArrayList().get(i);
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                    //printVector(GJ.get(j));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).getArrayList().get(i)!=0){
                    //storeDet[j] = GJ.get(j).arrayList.get(i);
                    //System.out.println(""+i+" < "+j+" && "+GJ.get(j).arrayList.get(i)+"!=0");
                    tempScalar = -(GJ.get(j).getArrayList().get(i)*GJ.get(i).getArrayList().get(i));
                    //System.out.println("tempScalar: "+tempScalar+" = -("+GJ.get(j).getArrayList().get(i)+"*"+GJ.get(i).getArrayList().get(i)+")");
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    //System.out.println(GJ.get(i) + " = " + GJ.get(i) + "*" + tempScalar);
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    //printVector(GJ.get(j));
                }
                //System.out.println("\n - = < A F T E R  up > = - ");
                //System.out.println("GJ.get("+j+").arrayList.get("+i+") = "+GJ.get(j).getArrayList().get(i)+" ");
                    for (int x = 0; x < dimension; x++) {
                        //System.out.print("\nGJ Final GJ.get{" + x + ")");
                        //printVector(GJ.get(x));
                    }
            }
        }
        
        //System.out.println("\n\nyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeet");
        
        for(int i=0;i<dimension;i++){
            //System.out.print("\nGJ Final GJ.get{"+i+")");
            //printVector(GJ.get(i));
        }
        
        
        System.out.println();
        double finDet = 1;
        for(int d = 0;d < dimension; d++){
            finDet = finDet * storeDet[d];
            //System.out.print(storeDet[d] + "*");
        }
        //System.out.print("\nDeterminant: "+finDet+"");
        return finDet;
    }
	
    public Matrix inverse() {
        List<Vector> vectors = this.arrayList;
        int dimension = vectors.get(0).getDimension();
        
        List<Vector> GJ = new ArrayList<Vector>();
        List<Vector> inv = new ArrayList<Vector>();
        //GJ = vectors;
        GJ = transposeList(vectors, dimension);
        //System.out.println("Dimension: "+dimension);
        for (int a = 0; a < dimension; a++) {
            double[] temp = new double[dimension];
            for (int b = 0; b < dimension; b++) {
                if(a==b)
                    temp[b]=1;
                else
                    temp[b]=0;
            }
            Vector v = new Vector(temp, dimension);
            inv.add(v);
        }
        
        int down=0;
        for(int i=0;i<dimension;i++){
            down = i;
            //System.out.println("Down: "+down+"----------------------------------------------------");
            for (int j = i; j < dimension; j++) {
                //System.out.println("\ni: "+i+", j: "+j+" ==================================================");
                //System.out.println("GJ.get("+j+").arrayList.get("+i+") = "+GJ.get(j).getArrayList().get(i)+" ");
                //printVector(GJ.get(j));
                if(i == j && GJ.get(j).getArrayList().get(i)==0){
                    //System.out.println("SwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwapSwap");
                    if(i<dimension-1){
                        GJ.get(i).swap(GJ.get(i+1));
                        inv.get(i).swap(inv.get(i+1));
                        //printVector(GJ.get(i+1));                        
                    }
                }
                if(i == j && GJ.get(j).getArrayList().get(i)!=1){
                    //System.out.println("!=1");
                    inv.set(i, inv.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                    //printVector(inv.get(i));
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                    //printVector(GJ.get(i));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).getArrayList().get(i)!=0){
                    //System.out.println(""+i+" < "+j+" && "+GJ.get(j).arrayList.get(i)+"!=0");
                    tempScalar = -(GJ.get(j).getArrayList().get(i)*GJ.get(i).getArrayList().get(i));
                    //System.out.println("tempScalar: "+tempScalar+" = -("+GJ.get(j).getArrayList().get(i)+"*"+GJ.get(i).getArrayList().get(i)+")");
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    inv.set(i, inv.get(i).scale(tempScalar));
                    //System.out.println(GJ.get(i) + " = " + GJ.get(i) + "*" + tempScalar);
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    inv.set(j, inv.get(j).add(inv.get(i)));
                    inv.set(i, inv.get(i).scale(1/tempScalar));
                    //printVector(GJ.get(j));
                }
                //System.out.println("\n - = < A F T E R  downwards > = - ");
                //System.out.println("GJ.get("+j+").arrayList.get("+i+") = "+GJ.get(j).getArrayList().get(i)+" ");
                for (int x = 0; x < dimension; x++) {
                    //System.out.print("\nGJ Final GJ.get{" + x + ")");
                    //printVector(GJ.get(x));
                    //printVector(inv.get(x));
                }
            }
        }
            //System.out.println("Down: "+down+"----------------------------------=====================================------------------");
        for(int i=down;i>=0;i--){
            for (int j = i; j >= 0; j--) {
                //System.out.println("\ni: "+i+", j: "+j+" ============================================Upwards");
                //printVector(GJ.get(j));
                if(i == j && GJ.get(j).getArrayList().get(i)!=1){
                    inv.set(i, inv.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                    //printVector(inv.get(i));
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                    //printVector(GJ.get(i));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).getArrayList().get(i)!=0){
                    //System.out.println(""+i+" < "+j+" && "+GJ.get(j).arrayList.get(i)+"!=0");
                    tempScalar = -(GJ.get(j).getArrayList().get(i)*GJ.get(i).getArrayList().get(i));
                    //System.out.println(tempScalar+" = -("+GJ.get(j).getArrayList().get(i)+"*"+GJ.get(i).getArrayList().get(i)+")");
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    inv.set(i, inv.get(i).scale(tempScalar));
                    //System.out.println(GJ.get(i) + " = " + GJ.get(i) + "*" + tempScalar);
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    inv.set(j, inv.get(j).add(inv.get(i)));
                    inv.set(i, inv.get(i).scale(1/tempScalar));
                    
                    //printVector(GJ.get(dimension-1-j));
                    
                }
                    //System.out.println("\n - = < A F T E R  upwards > = - ");
                    for (int x = 0; x < dimension; x++) {
                        //System.out.print("\nGJ Final GJ.get{" + x + ")");
                        //printVector(GJ.get(x));
                        //printVector(inv.get(x));
                    }
            }
        }
        
        
            //System.out.println("\n\nyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeetyeet");
        
        for(int i=0;i<dimension;i++){
            //System.out.print("\nGJ Final GJ.get{"+i+")");
            //printVector(GJ.get(i));
        }
        inv = transposeList(inv, dimension);
        for(int i=0;i<dimension;i++){
            System.out.print("\nInverse inv.get{"+i+")");
            printVector(inv.get(i));
        }
        
        Matrix inverse = new Matrix(inv, inv.get(0).getDimension());
        
        return inverse;
    }
    
	
}
