/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordanelimination;

/**
 *
 * @author Neil Michael Noble
 */
public class GaussJordanElimination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vector v = new Vector(0);
        Vector v1 = new Vector(new double[]{1, 2, 3}, 3);
        Vector v2 = new Vector(new double[]{3, 2, 3}, 3);
        v1.add(v2);
        v1.printer();
    }
    
}
