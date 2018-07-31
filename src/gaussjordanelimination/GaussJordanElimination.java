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
        /*Vector v1 = new Vector(new double[]{1, 2, 3}, 3);
        Vector v2 = new Vector(new double[]{3, 2, 3}, 3);
        v1.add(v2);
        v1.printer();*/
        
        System.out.println("\n");
        
        List<Vector> vectors = new ArrayList<Vector>();
        Vector v1 = new Vector(new double[]{1, 1, 1}, 3);
        Vector v2 = new Vector(new double[]{2, 3, 5}, 3);
        Vector v3 = new Vector(new double[]{4, 0, 5}, 3);
        Vector vR = new Vector(new double[]{5, 8, 2}, 3);
        v1.printer();
        v2.printer();
        v3.printer();
        vR.printer();
        vectors.add(v1);
        vectors.add(v2);
        vectors.add(v3);
        v.Gauss_Jordan(vectors, 3, vR).printer();
    }
    
}
