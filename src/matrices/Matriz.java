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

import java.util.Scanner;
 
class MatrixTranspose
{
   public static void main(String args[])
   {
      int A, B, c, d;
 
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the number of rows and columns of matrix, \n\t(Seperated by blank space)");
      A = in.nextInt();
      B = in.nextInt();
 
      int matrix[][] = new int[A][B];
 
      System.out.println("Enter the elements of matrix, (Seperated by blank space):");
 
      for ( c = 0 ; c < A ; c++ )
         for ( d = 0 ; d < B ; d++ ){
            matrix[c][d] = in.nextInt();
         }
    
      System.out.println("\nOriginal matrix:");
      
      for (int row = 0; row < matrix.length; row++) {
        for (int column = 0; column < matrix[row].length; column++) {
            System.out.print(matrix[row][column] + "\t");
        }
        System.out.println();
      }

      int tp[][] = new int[B][A];
 
      for ( c = 0 ; c < A ; c++ )
      {
         for ( d = 0 ; d < B ; d++ )               
            tp[d][c] = matrix[c][d];
      }
 
      System.out.println("Transposed matrix:");
 
      for ( c = 0 ; c < B ; c++ )
      {
         for ( d = 0 ; d < A ; d++ )
               System.out.print(tp[c][d]+"\t");
 
         System.out.print("\n");
      }
   }
}
