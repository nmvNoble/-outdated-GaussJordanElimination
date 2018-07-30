/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordanelimination;

import java.util.List;

/**
 *
 * @author Neil Michael Noble
 */
public class Vector {
    int dimension;
    double[] array;
    
    Vector (int dimension){
        this.array = new double [dimension];
        this.dimension = dimension;
    }
    
    Vector (double[] array, int dimension){
        this.dimension = dimension;
        this.array = new double [dimension];
        this.array = array;
    }
    
    Vector scale (double scalar){
        for(int i=0; i<dimension;i++)
            array[i]*=scalar;
        return this;
    }
    
    Vector add (Vector addend){
        for(int i=0; i<dimension;i++)
            array[i]+=addend.array[i];
        return this;
    }
    
    void printer (){
        for(int i=0; i<dimension; i++)
            System.out.println(array[i]);
        
    }
    Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants){
        for(int i=0; i<vectors.size(); i++){
            for (int j = i+1; j < vectors.size()-i; j++) {
                if (vectors.get(j).array[j]!=0) {
                    for(int k=0; k<dimension; k++){
                        double[] temp = vectors.get(i).array;
                    }
                }
            }
        }
    }
//    
//    int span (List<Vector> vectors, int dimension){
//        
//    }
    
}
