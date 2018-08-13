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

//        Vector v1 = new Vector(new double[]{1, 1, 1}, 3);
//        Vector v2 = new Vector(new double[]{2, 3, 5}, 3);
//        Vector v3 = new Vector(new double[]{4, 0, 5}, 3);
//        Vector constants = new Vector(new double[]{5, 8, 2}, 3);

        Vector v1 = new Vector(new double[]{1, 1, 0}, 3);
        Vector v2 = new Vector(new double[]{2, 2, 2}, 3);
        Vector v3 = new Vector(new double[]{4, 6, 3}, 3);
        Vector constants = new Vector(new double[]{2, 6, 13}, 3);

//        Vector v1 = new Vector(new double[]{2, 2}, 2);
//        Vector v2 = new Vector(new double[]{3, 2}, 2);
//        Vector constants = new Vector(new double[]{1, 2}, 2);
        
        List<Vector> vectors = new ArrayList<Vector>();
        vectors.add(v1);
        vectors.add(v2);
        vectors.add(v3);
        //vectors.add(v4);
        //vectors.add(v5);
        int dimension = vectors.get(0).getDimension();

        constants = v.Gauss_Jordan(vectors, dimension, constants);
        System.out.print("\n\nConstants: ");
        v.printVector(constants);
        //System.out.println("\nDeterminants: "+v.det(vectors, dimension));
        
//        System.out.println("Should be:");
//        System.out.println("Vector: vL[0]: 1.00 vL[1]:  1.00 vL[2]: 1.0");
//        System.out.print("gje([[1, 2, 4], [1, 2, 6], [0, 2, 3]], 3, [2, 6, 13]), expected answer is [1, 1, 1]");
        
        
        
        //v.ListTo2DArray(vectors, dimension, constants);
        
        int span = 0;
        span = v.span(vectors, dimension);
        //v.printSpan(span);
        
        
        
        
//        Vector vm0 = new Vector(new double[]{1, 3}, 2);
//        Vector vm1 = new Vector(new double[]{2, 4}, 2);
//        Vector vm2 = new Vector(new double[]{2, 1}, 2);
//        Vector vm3 = new Vector(new double[]{0, 2}, 2);
//      
//        List<Vector> vecM1 = new ArrayList<Vector>();
//        List<Vector> vecM2 = new ArrayList<Vector>();
//
//        vecM1.add(vm0);
//        vecM1.add(vm1);
//        vecM2.add(vm2);
//        vecM2.add(vm3);
//        
//        Matrix m = new Matrix(0);
//        Matrix m1 = new Matrix(0);
//        Matrix m1 = new Matrix(vecM1, 2);
//        Matrix m2 = new Matrix(0);
//        Matrix m2 = new Matrix(vecM2, 2);
//        m.times(m1, m2);
        
        
        
        
        
    }
    
}
