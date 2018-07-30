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
public class Vector {
    int dimension;
    double[] array;
    
    Vector (int dimension){
        this.dimension = dimension;
    }
    
    Vector (double[] array, int dimension){
        this.dimension = dimension;
        this.array = array;
//        double[] normVector = new double[array.length];
//        for (int i = 0;i<array.length;i++)
//            normVector[i]=array[i];
    }
    
    Vector scale (double scalar){
        Vector agik = new Vector( this.array, this.dimension);
        for(int i=0; i<dimension;i++)
            agik.array[i]*=scalar;
        return agik;
    }
    
    Vector add (Vector addend){
        Vector ooof = new Vector( this.array, this.dimension);
            ooof.array=addend.array;
            return ooof;
    }
    
    Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants){
        
    }
    
    int span (List<Vector> vectors, int dimension){
        
    }
    
}
