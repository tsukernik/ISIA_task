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

/* Multiplicacion Matrices - Ramirez - Inicio */

import java.util.*;
class multiply
{
 int a,b,sum=0;
 void mult()
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the number of rows of the first matrix:");
      int m=in.nextInt();
      System.out.println("Enter the number of columns of the first matrix:");
      int n=in.nextInt();
      int[][] mat1 = new int[m+1][n+1];
      for(a=1;a < m+1;a++)
       {
          for(b=1;b < n+1;b++)
           {
             System.out.println("Enter element ("+a+","+b+") of the first matrix");
             mat1[a][b] = in.nextInt();
            }// end of b loop
         }//  end of a loop
      System.out.println("Enter the number of rows of the second matrix:");
      int p=in.nextInt();
      System.out.println("Enter the number of columns of the second matrix:");
      int q=in.nextInt();
      int[][] mat2 = new int[p+1][q+1];
      for(a=1;a < p+1;a++)
       {
         for(b=1;b < q+1;b++)
           {
             System.out.println("Enter element ("+a+","+b+") of the second matrix");
             mat2[a][b] = in.nextInt();
            }// end of b loop
         }// end of a loop
     if(n==q)//comparing coulmns with rows
      {
       int[][] reslt = new int[m+1][q+1];
       for ( a = 1 ; a < m+1 ; a++ )
        {
         for ( b = 1 ; b < n+1 ; b++ )
          {
            for (int k = 0 ; k < p+1 ; k++)
               sum = sum + mat1[a][k]*mat2[k][b];
            reslt[a][b] = sum;
            sum = 0;
          } // end of the loop
        } // end of the loop
       System.out.println("The result is:\n");
       for(a=1;a < m+1;a++)
        {
           for(b=1;b< q+1;b++)
            {
              System.out.print(reslt[a][b] + "  ");
             } // end of b loop
          System.out.println("\n");
        } // end of a loop
     }
    else
         System.out.println("Error, the number of columns of the first matrix must be equal to the number of rows of the second matrix");
   }//end of function
}// end of class

class MatrixMultiplication
{
   public static void main(String arg[])
     {
       Scanner in = new Scanner(System.in);
       multiply obj = new multiply();
       obj.mult();
     }
}


/* Multiplicacion Matrices - Ramirez - Fin */

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
