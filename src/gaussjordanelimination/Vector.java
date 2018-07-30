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
        System.out.println("\nPrinter: ");
        for(int i=0; i<dimension; i++)
            System.out.print(array[i]+" ");
        
    }
    Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants){
        System.out.println();
        List<Vector> fVectors = vectors;
                                    //System.out.println("G_J:");;
                                    //System.out.println("G_J:");
        for(int i=0; i<vectors.size(); i++){
                                    //System.out.println("i=====: "+vectors.get(i).array[0]);
                                    vectors.get(i).printer();
            for(int j = i+1; j<vectors.size(); j++){
                                    //System.out.println("j: "+j);
                for(int k=i; k<dimension; k++){
                    if(vectors.get(j).array[k]!=0){
                                    //System.out.println("vec.("+i+").arr["+k+"]: "+vectors.get(i).array[k]
                                    //        +" * vec.("+j+").arr["+i+"]: "+vectors.get(j).array[i]);
                                    System.out.println("\ntemp  | ("+i+","+k+"): "+vectors.get(i).array[k]
                                            +" * ("+j+","+i+"): "+vectors.get(j).array[i]);
                                    System.out.println("temp2 | ("+j+","+k+"): "+vectors.get(j).array[k]
                                            +" * ("+i+","+i+"): "+vectors.get(i).array[i]);
                        double temp = vectors.get(i).array[k]*vectors.get(j).array[i];
                        double temp2 = vectors.get(j).array[k]*vectors.get(i).array[i];
                                    System.out.print(k+": "+temp);
                                    System.out.println("_"+temp2);
                        if(vectors.get(i).array[i]>0){
                            if (vectors.get(j).array[i] > 0) 
                                fVectors.get(j).array[k] = temp - temp2;
                            if (vectors.get(j).array[i] < 0) 
                                fVectors.get(j).array[k] = temp + temp2;
                        }
                            
                        if(vectors.get(i).array[i]<0){
                            if (vectors.get(j).array[i] > 0) 
                                fVectors.get(j).array[k] = temp + temp2;
                            if (vectors.get(j).array[i] < 0) 
                                fVectors.get(j).array[k] = temp - temp2;
                        }
                        
                        if(vectors.get(j).array[k]!=0){
                            double conTemp = constants.array[i]*vectors.get(j).array[i];
                            double conTemp2 = constants.array[j]*vectors.get(i).array[i];
                            if(vectors.get(i).array[i]>0){
                                if (vectors.get(j).array[i] > 0) 
                                    constants.array[k] = conTemp - conTemp2;
                                if (vectors.get(j).array[i] < 0) 
                                    constants.array[k] = conTemp + conTemp2;
                            }

                            if(vectors.get(i).array[i]<0){
                                if (vectors.get(j).array[i] > 0) 
                                    constants.array[k] = temp + temp2;
                                if (vectors.get(j).array[i] < 0) 
                                    constants.array[k] = temp - temp2;
                            }
                            System.out.println("con");
                        }
                    }
                }
            }
        }
        Vector finalV = new Vector(constants.array, dimension);
        System.out.println("ret");
        return finalV;
    }
    
//    int span (List<Vector> vectors, int dimension){
//        
//    }
    
}
