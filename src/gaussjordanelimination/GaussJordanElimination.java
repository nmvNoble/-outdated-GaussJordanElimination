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
        Vector v1 = new Vector(new double[]{5, 3, -6}, 3);
        Vector v2 = new Vector(new double[]{6, -4, 1}, 3);
        Vector v3 = new Vector(new double[]{3, 8, -2}, 3);
        Vector constants = new Vector(new double[]{23, 25, 19}, 3);
        
        /*Vector v2 = new Vector(new double[]{1, -2}, 2);
        Vector v3 = new Vector(new double[]{5, -7}, 2);
        Vector constants = new Vector(new double[]{7, -5}, 2);*/
        
      
        List<Vector> vectors = new ArrayList<Vector>();

        vectors.add(v1);
        vectors.add(v2);
        vectors.add(v3);

        int dimension = vectors.size()+1;
        int span = 0;

        constants = v.Gauss_Jordan(vectors, dimension, constants);
        v.printConstants(constants);
        //v.matrixPrinter(vectors, dimension, constants);
        
        span = v.span(vectors, dimension);
        v.printSpan(span);
    }
    
}
