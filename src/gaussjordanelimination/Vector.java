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
    Vector (int dimension){
        this.dimension = dimension;
        double[] zeroVector = new double[dimension];
        
    }
    
    Vector (double[ ] array, int dimension){
        this.dimension = dimension;
        double[] normVector = new double[dimension];
        for (int i = 0;i<dimension;i++)
            normVector[i]=array[i];
    }
    
    Vector scale (int scalar){
        return Vector(scalar);
    }
    
    Vector add (Vector addend){
        
    }
    
    Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants){
        
    }
    
    int span (List<Vector> vectors, int dimension){
        
    }
    
}
