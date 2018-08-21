/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordanelimination;

import java.util.ArrayList;
import java.util.List;

/**
 * Klaudia Gaia Borromeo
 * Neil Michael Noble
 * Iris Libay
 * ADVDISC X22
 */
public class GaussJordanElimination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vector v = new Vector(0);
//        Vector v1 = new Vector(new double[]{1, 2, 3, 4, 5}, 5);
//        Vector v2 = new Vector(new double[]{6, 7, 8, 9, 2}, 5);
//        Vector v3 = new Vector(new double[]{5, 2, 3, 4, 5}, 5);
//        Vector v4 = new Vector(new double[]{7, 8, 4, 2, 1}, 5);
//        Vector v5 = new Vector(new double[]{1, 2, 5, 4, 3}, 5);
//        Vector constants = new Vector(new double[]{5, 8, 2, 5, 5}, 5);

        /*Tracing NOT TRANSPOSED. Link: http://pages.pacificcoast.net/~cazelais/251/gauss-jordan.pdf*/
//        Vector v1 = new Vector(new double[]{1, 1, 1}, 3);
//        Vector v2 = new Vector(new double[]{2, 3, 5}, 3);
//        Vector v3 = new Vector(new double[]{4, 0, 5}, 3);
//        Vector constants = new Vector(new double[]{5, 8, 2}, 3);

        /*Force-Swap test NOT TRANSPOSED*/
//        Vector v1 = new Vector(new double[]{1, 1, 0}, 3);
//        Vector v2 = new Vector(new double[]{2, 2, 2}, 3);
//        Vector v3 = new Vector(new double[]{4, 6, 3}, 3);
//        Vector constants = new Vector(new double[]{2, 6, 13}, 3);

        /*Transpose Test. gje([[1, 2, 3], [3, 4, 6], [1, 0, 1]], 3, [5, 6, 10]), expected answer is [1, 1, 1]*/
//        Vector v1 = new Vector(new double[]{1, 2, 3}, 3);
//        Vector v2 = new Vector(new double[]{3, 4, 6}, 3);
//        Vector v3 = new Vector(new double[]{1, 0, 1}, 3);
//        Vector constants = new Vector(new double[]{5, 6, 10}, 3);

        /*Transpose Test. http://pages.pacificcoast.net/~cazelais/251/gauss-jordan.pdf*/
//        Vector v1 = new Vector(new double[]{1, 2, 4}, 3);
//        Vector v2 = new Vector(new double[]{1, 3, 0}, 3);
//        Vector v3 = new Vector(new double[]{1, 5, 5}, 3);
//        Vector constants = new Vector(new double[]{5, 8, 2}, 3);

//        Vector v1 = new Vector(new double[]{2, 2}, 2);
//        Vector v2 = new Vector(new double[]{3, 2}, 2);
//        Vector constants = new Vector(new double[]{1, 2}, 2);

        /*Inverse. Link: https://www.wikihow.com/Find-the-Inverse-of-a-3x3-Matrix#Using_Linear_Row_Reduction_to_Find_the_Inverse_Matrix_sub*/
        Vector v1 = new Vector(new double[]{3, 2, 0}, 3);
        Vector v2 = new Vector(new double[]{0, 0, 1}, 3);
        Vector v3 = new Vector(new double[]{2, -2, 1}, 3);
        Vector constants = new Vector(new double[]{5, 8, 2}, 3);
        
        List<Vector> vectors = new ArrayList<Vector>();
        vectors.add(v1);
        vectors.add(v2);
        vectors.add(v3);
        //vectors.add(v4);
        //vectors.add(v5);
        int dimension = vectors.get(0).getDimension();

//        constants = v.Gauss_Jordan(vectors, dimension, constants);
//        System.out.print("\n\nConstants: ");
//        v.printVector(constants);
        
        //System.out.println("\nDeterminants: "+v.det(vectors, dimension));
        
        //System.out.println("\nInverse: "+v.inverse(vectors, dimension));
        
        
        
        //v.ListTo2DArray(vectors, dimension, constants);
        
        int span = 0;
        span = v.span(vectors, dimension);
        //v.printSpan(span);
        
        
        
        
        Vector vm0 = new Vector(new double[]{1, 3}, 2);
        Vector vm1 = new Vector(new double[]{2, 4}, 2);
        Vector vm2 = new Vector(new double[]{2, 1}, 2);
        Vector vm3 = new Vector(new double[]{0, 2}, 2);
      
        List<Vector> vecM1 = new ArrayList<Vector>();
        List<Vector> vecM2 = new ArrayList<Vector>();

        vecM1.add(vm0);
        vecM1.add(vm1);
        vecM2.add(vm2);
        vecM2.add(vm3);
        
        //Matrix m1 = new Matrix(0);
        Matrix m1 = new Matrix(vecM1, 2);
        //Matrix m2 = new Matrix(0);
        Matrix m2 = new Matrix(vecM2, 2);
        m1.times(m2);
        
        
        Vector vm8 = new Vector(new double[]{0, 2}, 2);
        Vector vm9 = new Vector(new double[]{4, 7}, 2);
        
//        Vector vm8 = new Vector(new double[]{6, 4, 2}, 3);
//        Vector vm9 = new Vector(new double[]{1, -2, 8}, 3);
//        Vector vm10 = new Vector(new double[]{1, 5, 7}, 3);
        List<Vector> vecM5 = new ArrayList<Vector>();
        vecM5.add(vm8);
        vecM5.add(vm9);
        //vecM5.add(vm10);
        Matrix m = new Matrix(vecM5, vm9.getDimension());
        m.det();
        //System.out.println("\n\nDet: "+m.det());
        
        Matrix mInv = new Matrix(vectors, dimension);
        mInv.inverse();
        
        
        
        
        
        
        
    }
    
}
