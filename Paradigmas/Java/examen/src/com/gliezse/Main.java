package com.gliezse;
//Ignorar el package name :)

/*
* Autor: Cristian Cruz
* Docente: Jorge Rios
* Lic. en Informática
* Primer año, segundo cuatrimestre
* 2019
* */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean continuar = true;
        while(continuar){
            System.out.println("Elija un numero de ejercicio del 3 al 5 o 0 para salir");

            Scanner tecl = new Scanner(System.in);
            int ej = tecl.nextInt();

            switch (ej){
                case 3:
                    ej3();
                    break;
                case 4:
                    ej4();
                    break;
                case 5:
                    ej5();
                    break;
                case 0:
                    System.out.println("Bye");
                    continuar = false;
                    break;
                default:
                    System.out.println("Numero Inválido");
            }
        }
    }

    static void ej3(){
        int n = 10;
        int[] conjunto = new int[n];

        Scanner input = new Scanner(System.in);
        for (int i = 0 ; i < conjunto.length ; i++) {
            System.out.print("Ingrese el número de la posición " + i + ": ");
            conjunto[i] = input.nextInt();
        }

        int sumaTotal = 0;
        for (int i = 0 ; i < conjunto.length ; i++){
            sumaTotal += conjunto[i];
        }

        System.out.println("Suma total: " + sumaTotal);
        System.out.println(sumaTotal > 50 ? "Imprimiendo indices pares" : "Imprimiendo indices impares");

        for (int i = 0 ; i < conjunto.length ; i++){
            if (sumaTotal > 50 && i % 2 == 0){
                System.out.println(conjunto[i]);
            } else if (sumaTotal < 50 && i % 2 != 0){
                System.out.println(conjunto[i]);
            }
        }
    }

    static void ej4(){
        //Matriz ejemplo
        int[][] matriz = {
                {6, 12, 53},
                {24 ,11, 74},
                {72, 63, 12}
        };

        int sumaTotal = 0;
        for (int i = 0 ; i < matriz.length ; i++){
            for (int j = 0 ; j < matriz[i].length; j++){
                sumaTotal += matriz[i][j];
            }
        }

        System.out.println("Suma total: "+ sumaTotal);
        // Al ser una matriz cuadrada, si se multiplica n * n (en este caso el length)
        // se obtiene la cantidad de datos que posee.
        System.out.println("Promedio: " + (double)sumaTotal/(matriz.length*matriz.length));
    }

    static void ej5(){
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese el valor M (<6)");
        int m = input.nextInt();
        System.out.println("Ingrese el valor N (<5)");
        int n = input.nextInt();
        int[][] matrizA = new int[m][n];

        for (int fila = 0 ; fila < m ; fila++){
            System.out.println("Ingrese los numeros para la fila "+ fila);

            for ( int col = 0 ; col < n ; col ++){
                System.out.println("Columna " + col);
                matrizA[fila][col] = input.nextInt();
            }
        }

        //Imprimir por columnas
        for (int col = 0 ; col < n ; col++){
            String columnaString = "";
            for (int fila = 0 ; fila < m ; fila++){
                columnaString += matrizA[fila][col] + " ";
            }
            System.out.println("Columna " + col + ": " + columnaString);
        }

        //Imprimir promedio
        int sumaTotal = 0;

        for (int fila = 0 ; fila < m ; fila++){
            for (int col = 0 ; col < n ; col++){
                sumaTotal += matrizA[fila][col];
            }
        }

        System.out.println("Promedio: " + sumaTotal/(m*n));

        //Vector suma x columna
        int[] vecsumcol = new int[n];

        for (int col = 0 ; col < n ; col++){
            for (int fila = 0 ; fila < m ; fila++){
                vecsumcol[col] += matrizA[fila][col];
            }
        }
        for (int col = 0 ; col < vecsumcol.length ; col++){
            System.out.println("Suma columna " + col + ": "+ vecsumcol[col]);
        }

        //Vector max por fila
        int[] vecmaxfil = new int[m];

        for (int fila = 0 ; fila < m ; fila++){
            int maxFila = 0;
            for (int col = 0 ; col < n ; col++){
                if (fila == 0 && col == 0 ){
                    maxFila = matrizA[fila][col];
                } else {
                    maxFila = Math.max(matrizA[fila][col], maxFila);
                }
            }
            vecmaxfil[fila] = maxFila;
        }
        for (int fila = 0 ; fila < vecmaxfil.length ; fila++){
            System.out.println("Máximo fila "+fila+": "+vecmaxfil[fila]);
        }
    }

}
