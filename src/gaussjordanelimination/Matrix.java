package gaussjordanelimination;

import static gaussjordanelimination.Vector.transposeList;
import java.util.ArrayList;
import java.util.List;

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

    public Matrix(int dimension) {
        this.dimension = dimension;
    }
	
    public Matrix(List<Vector> arrayList, int dimension) {
        this.arrayList = arrayList;
        this.dimension = dimension;
    }
	
    public static double[][] ListTo2DArray(List<Vector> vectors, int dimension){
        double[][] matrix = new double [dimension][dimension];
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++)
                matrix[i][j] = vectors.get(j).getArrayList().get(i);
        }    
        return matrix;
    }
	
    public Matrix times(Matrix B)    {
        Matrix A = this;
        double a[][] = new double[A.dimension][A.dimension];
        a = ListTo2DArray(A.arrayList, A.dimension);
        double b[][] = new double[B.dimension][B.dimension];
        b = ListTo2DArray(B.arrayList,B.dimension);

        int aColSize = a[0].length; //matrix A columns length
        int bRowSize = b.length; //matrix B row length

        if(aColSize != bRowSize)
            System.out.println("Incompatible matrix size.");
        
        int matrixRowSize = a.length; //final matrix row length
        int matrixColSize = b[0].length; //final matrix column length

        double[][] matrixResult = new double[matrixRowSize][matrixColSize];
        
        for(int i = 0; i < matrixRowSize; i++)
            for(int j = 0; j < matrixColSize; j++)
                matrixResult[i][j]= 0.0000;

        for(int i = 0; i < matrixRowSize; i++)
            for(int j = 0; j < matrixColSize; j++)
                for(int k = 0; k < aColSize; k++)
                    matrixResult[i][j] += a[i][k] * b[k][j];
        
        System.out.println("Matrix Multiplication Result: ");
        for(int i = 0; i < matrixRowSize; i++)
        {
            for(int j = 0; j < matrixColSize; j++)
            {
                A.arrayList.get(j).getArrayList().set(i, matrixResult[i][j]);
                System.out.print(A.arrayList.get(j).getArrayList().get(i)+ ", ");
            }
            System.out.println();
        }
        
        return A;
    }

    public double det ( ) {
        List<Vector> vectors = this.arrayList;
        int dimension = vectors.get(0).getDimension();
        for(int q = 0; q<vectors.size();q++){
            if(vectors.get(q).getArrayList().size() != dimension){
                System.out.print("\nDeterminant does not exist (will return det as 0)");
                return 0;
            }
        }
        
        if(dimension == 2){
            double det = vectors.get(0).getArrayList().get(0) * vectors.get(1).getArrayList().get(1)
                    - vectors.get(0).getArrayList().get(1)*vectors.get(1).getArrayList().get(0);
            System.out.println("\nDeterminant: "+det);
            return det;
        }
        
        List<Vector> GJ = new ArrayList<Vector>();
        double[] storeDet = new double[dimension+1];
        GJ = transposeList(vectors, dimension);
        
        int down=0;
        for(int i=0;i<dimension;i++){
            down = i;
            for (int j = i; j < dimension; j++) {
                if(i == j && GJ.get(j).getArrayList().get(i)==1)
                    storeDet[j] = GJ.get(j).getArrayList().get(i);
                if(i == j && GJ.get(j).getArrayList().get(i)==0){
                    storeDet[j] = GJ.get(j).getArrayList().get(i);
                    if (i<dimension-1)
                        GJ.get(i).swap(GJ.get(i+1));
                }
                if(i == j && GJ.get(j).getArrayList().get(i)!=1){
                    storeDet[j] = GJ.get(j).getArrayList().get(i);
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).getArrayList().get(i)!=0){
                    tempScalar = -(GJ.get(j).getArrayList().get(i)*GJ.get(i).getArrayList().get(i));
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                }
            }
        }
        System.out.println();
        double finDet = 1;
        for(int d = 0;d < dimension; d++)
            finDet = finDet * storeDet[d];
        System.out.println("\nDeterminant: "+finDet);
        
        return finDet;
    }
	
    public Matrix inverse() {
        List<Vector> vectors = this.arrayList;
        int dimension = vectors.get(0).getDimension();
        for(int q = 0; q<vectors.size();q++){
            if(vectors.get(q).getArrayList().size() != dimension){
                System.out.print("\nInverse does not exist");
                return null;
            }
        }
        List<Vector> GJ = new ArrayList<Vector>();
        List<Vector> inv = new ArrayList<Vector>();
        GJ = transposeList(vectors, dimension);
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
            for (int j = i; j < dimension; j++) {
                if(i == j && GJ.get(j).getArrayList().get(i)==0){
                    if(i<dimension-1){
                        GJ.get(i).swap(GJ.get(i+1));
                        inv.get(i).swap(inv.get(i+1));                  
                    }
                }
                if(i == j && GJ.get(j).getArrayList().get(i)!=1){
                    inv.set(i, inv.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).getArrayList().get(i)!=0){
                    tempScalar = -(GJ.get(j).getArrayList().get(i)*GJ.get(i).getArrayList().get(i));
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    inv.set(i, inv.get(i).scale(tempScalar));
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    inv.set(j, inv.get(j).add(inv.get(i)));
                    inv.set(i, inv.get(i).scale(1/tempScalar));
                }
            }
        }
        for(int i=down;i>=0;i--){
            for (int j = i; j >= 0; j--) {
                if(i == j && GJ.get(j).getArrayList().get(i)!=1){
                    inv.set(i, inv.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                    GJ.set(i, GJ.get(j).scale(1/GJ.get(j).getArrayList().get(i)));
                }
                double tempScalar=0;
                if(i != j && GJ.get(j).getArrayList().get(i)!=0){
                    tempScalar = -(GJ.get(j).getArrayList().get(i)*GJ.get(i).getArrayList().get(i));
                    GJ.set(i, GJ.get(i).scale(tempScalar));
                    inv.set(i, inv.get(i).scale(tempScalar));
                    GJ.set(j, GJ.get(j).add(GJ.get(i)));
                    GJ.set(i, GJ.get(i).scale(1/tempScalar));
                    inv.set(j, inv.get(j).add(inv.get(i)));
                    inv.set(i, inv.get(i).scale(1/tempScalar));
                }
            }
        }
        inv = transposeList(inv, dimension);
        System.out.println("\nInverse: ");
        for(int i=0;i<dimension;i++){
            List<Double> vectorList = inv.get(i).getArrayList();
            System.out.print("Vector " + i + ": ");
            for (int j = 0; j < vectorList.size(); j++) {
                System.out.print(vectorList.get(j) + ", ");
            }
            System.out.println();
        }
        Matrix inverse = new Matrix(inv, inv.get(0).getDimension());
        
        return inverse;
    }
    
    public Matrix transpose(){
        List<Vector> transpose = new ArrayList<>();
        
        for(int i=0;i<dimension;i++){
            double[] tmp = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                //tmp[j] = vectors.get(j).arrayList.get(i);
                tmp[j] = this.arrayList.get(j).getArrayList().get(i);//vectors.get(j).arrayList.get(i);
            }
            Vector e = new Vector(tmp, dimension);
                transpose.add(e);
        }
        Matrix T = new Matrix(transpose, dimension); 
        return T;
    }
	
}
