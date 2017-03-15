/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrices;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author galvez
 */
public class Matriz {
    private int[][]datos;
    private Random rnd = new Random();
    
    public Matriz(int filas, int columnas, boolean inicializarAleatorio){
        datos = new int[columnas][];
        for(int i=0; i<columnas; i++){
            datos[i] = new int[filas];
            if (inicializarAleatorio)
                for(int j=0; j<filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }
    public Matriz(Dimension d, boolean inicializarAleatorio){
        this(d.height, d.width, inicializarAleatorio);
    }
    
    public Dimension getDimension(){
        return new Dimension(datos.length, datos[0].length);
    }
    
    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        if(! a.getDimension().equals(b.getDimension())) throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");        
        int i, j, filasA, columnasA; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) { 
            for (i = 0; i < columnasA; i++) { 
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j]; 
            } 
        } 
        return matrizResultante; 
    } 

    public static Matriz Inversa(Matriz a) throws ErrorDimensiones {
        if(! a.getDimension().height.equals(a.getDimension().width)) throw new ErrorDimensiones("La matriz debe ser cuadrada");
        double det=1/determinante(a);
        Matriz adjMatriz=adjunta(a);
        multiplicarEscalar(det,adjMatriz);
        return adjMatriz;
    }

    public void multiplicarEscalar(double n, Matriz a) {
        int i, j, dimension;
        dimension=a.getDimension().height;
        for(i=0;i<dimension;i++)
                for(j=0;j<dimension;j++)
                        a.datos[i][j]*=n;
    }
 
    public static Matriz adjunta(Matriz a){
        return transpuesta(cofactores(a));
    }
 
    public Matriz cofactores(Matriz a){
        int i, j, k, l, dimension;
        dimension=a.getDimension().height;
        Matriz nm = new Matriz(dimension, dimension, false);
        for(i=0;i<dimension;i++) {
            for(j=0;j<dimension;j++) {
                double[][] det=new double[dimension-1][dimension-1];
                double detValor;
                for(k=0;k<dimension;k++) {
                    if(k!=i) {
                        for(l=0;l<dimension;l++) {
                            if(l!=j) {
                                int indice1=k<i ? k : k-1 ;
                                int indice2=l<j ? l : l-1 ;
                                det[indice1][indice2]=a.datos[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm.datos[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }
 
    public Matriz transpuesta(Matriz a){
        int i, j, dimension;
        dimension=a.getDimension().height;
        Matriz nuevam = new Matriz(dimension, dimension, false);
        for(i=0; i<dimension; i++){
            for(j=0; j<dimension; j++)
                nuevam.datos[i][j]=a.datos[j][i];
        }
        return nuevam;
    }
 
    public double determinante(Matriz a){
        int i, j, k, dimension;
        double det;
        dimension=a.getDimension().height;
        if(dimension==2){
            det=(a.datos[0][0]*a.datos[1][1])-(a.datos[1][0]*a.datos[0][1]);
            return det;
        }
        double suma=0;
        for(i=0; i<dimension; i++){
        Matriz nm = new Matriz(dimension-1, dimension-1, false);
            for(int j=0; j<dimension; j++){
                if(j!=i){
                    for(k=1; k<dimension; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                        nm.datos[indice][k-1]=a.datos[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=a.datos[i][0] * determinante(nm);
            else
                suma-=a.datos[i][0] * determinante(nm);
        }
        return suma;
    }
   
    	
	
    @Override
    public String toString(){
        String ret = "";
        ret += "[\n";
        for (int i = 0; i < getDimension().width; i++) {
            ret += "(";
            for (int j = 0; j < getDimension().height; j++) {  
                ret += String.format("%3d", datos[i][j]); 
                if (j != getDimension().height - 1) ret += ", ";
            } 
            ret += ")";
            if (i != getDimension().width - 1) ret += ",";
            ret += "\n";
        } 
        ret += "]\n";
        return ret;
    }
}
