package com.gliezse;

import java.util.Scanner;

//Este código es una basura. Recomiendo no leer si se encuentra en buena relación con su bienestar.
public class Main {

    public static void main(String[] args) {
        boolean continuar = true;
        while(continuar){
            System.out.println("Elija un numero de ejercicio del 2 al 7 o 0 para salir");

            Scanner yeet = new Scanner(System.in);
            int ej = yeet.nextInt();

            switch (ej){
                case 2:
                    ej2();
                    break;
                case 3:
                    ej3();
                    break;
                case 4:
                    ej4();
                    break;
                case 5:
                    ej5();
                    break;
                case 6:
                    ej6();
                    break;
                case 7:
                    ej7();
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

    public static void ej2(){
        int sumaTotal = 0;
        long productoTotal = 1;

        for(int i = 1 ; i <= 100; i++){
            if( i % 2 == 0){
                sumaTotal += i;
                productoTotal *= i;
                //El numero excede el límite, por lo que se imprimirá "0"
            }
        }

        System.out.println("Suma total: " + sumaTotal);
        System.out.println("Producto total: " + productoTotal);
    }
    public static void ej3(){
        System.out.println("Ingrese la cantidad de horas trabajadas");
        Scanner yeet = new Scanner(System.in);
        double horas = yeet.nextDouble();

        double horaBasica = 200;

        if ( horas <= 40){
            System.out.println("Sueldo: $" + horas * horaBasica);
        } else if ( horas - 40 <= 10 ) {
            double horasExtra = horas - 40;
            System.out.println("Sueldo: $" + ( (40*horaBasica) + (horasExtra*(horaBasica*2)) ));
        } else {
            double horasExtra = horas - 40;
            double horasExtraExtra = horas - 50;
            System.out.println("Sueldo: $" + ( (40*horaBasica) + (horasExtra*(horaBasica*2)) + (horasExtraExtra*(horaBasica*3)) ));
        }
    }
    public static void ej4(){
        double[] arr = new double[3];

        Scanner yeet = new Scanner(System.in);
        double subTotal = 0.00;
        for(int i = 0 ; i < arr.length; i++){
            System.out.println("Ingrese el valor de la pelicula " + (i+1));
            arr[i] = yeet.nextDouble();
            subTotal += arr[i];
        }

        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(int i = 0 ; i < arr.length ; i++){
                if( i != arr.length-1){
                    if( arr[i] < arr[i+1] ){
                        double aux = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = aux;
                        sorted = false;
                    }
                }
            }
        }
        System.out.println("Subtotal: " + subTotal);
        System.out.println("Descuento: " + arr[arr.length-1]);
        System.out.println("Total a pagar: " + (subTotal - arr[arr.length-1]));
    }
    public static void ej5(){
        int[] arr = new int[50];

        Scanner yeet = new Scanner(System.in);

        double suma = 0;
        double cantidadTotal = 0;

        for(int i = 0 ; i < arr.length; i++){
            System.out.println("Ingrese el numero de la posición "+i+"/"+(arr.length-1));
            arr[i] = yeet.nextInt();

            if(i % 2 == 1){
                suma += arr[i];
                cantidadTotal += 1;
            }
        }

        System.out.println("Suma de los numeros en posiciones impares: " + suma);
        System.out.println("Promedio: " + suma/cantidadTotal);
    }
    public static void ej6(){
        int[][] matrix = {
                {2, 5, 7},
                {4, 1, 5},
                {7, 3, 5}
        };

        System.out.println("Matriz: ");
        System.out.println(matrix[0][0] + " " + matrix[0][1] + " " + matrix[0][2]);
        System.out.println(matrix[1][0] + " " + matrix[1][1] + " " + matrix[1][2]);
        System.out.println(matrix[2][0] + " " + matrix[2][1] + " " + matrix[2][2]);

        double dimension = 3;
        double acumulador = 0;
        for ( int i = 0 ; i < dimension; i++){
            for( int j = 0 ; j < dimension; j++){
                if( i == j ) {
                    acumulador += matrix[i][j];
                }
            }
        }
        System.out.println("La diagonal suma " + acumulador);
        System.out.println("Promedio: " + acumulador/dimension);
    }
    public static void ej7(){
        Scanner yeet = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de candidatos: ");
        int candidatos = yeet.nextInt();

        System.out.print("Ingrese la cantidad de distritos: ");
        int distritos = yeet.nextInt();

        int[][] votos = new int[candidatos][distritos];
        int[] votosPorCandidatos = new int[candidatos];
        int votosTotal = 0;

        for(int c = 0 ; c < candidatos; c++){
            System.out.println("CANDIDATO " + (c+1));
            for(int d = 0; d < distritos; d++){
                System.out.print("Votos en distrito " + (d+1) + ": ");
                votos[c][d] = yeet.nextInt();
                votosTotal += votos[c][d];
                votosPorCandidatos[c] += votos[c][d];
            }
        }

        System.out.println("Votos totales: "+votosTotal);

        boolean ganador = false;
        int ganadorIndex = -1;

        for(int c = 0 ; c < candidatos; c++){
            System.out.println("VOTOS PARA CANDIDATO " + (c+1) + ": "+
                    votosPorCandidatos[c] + " (%"+ (double)(votosPorCandidatos[c]*100)/votosTotal + ")");

            for(int d = 0 ; d < distritos; d++){
                System.out.println("Distrito " + (d+1) + ": " + votos[c][d]);
            }

            if( (votosPorCandidatos[c]*100)/votosTotal > 50 ){
                ganador = true;
                ganadorIndex = c ;
            }
        }

        if (ganador){
            System.out.println("El candidato " + (ganadorIndex+1) + " es el ganador," +
                    "consiguiendo el %" + (votosPorCandidatos[ganadorIndex]*100)/votosTotal + " de los votos");
        } else {
            int indexPrimero = 0;
            int indexSegundo = 0;

            for(int c = 0; c < candidatos; c++){
                if(c!=0){
                    if(votosPorCandidatos[c] > votosPorCandidatos[indexPrimero]){
                        indexPrimero = c;
                    } else if(votosPorCandidatos[c] > votosPorCandidatos[indexSegundo]) {
                        indexSegundo = c;
                    }
                }
            }

            System.out.println("Los dos candidatos mas votados fueron: ");
            System.out.println("1) Candidato "+(indexPrimero+1) + " con %" +
                    (double)(votosPorCandidatos[indexPrimero]*100)/votosTotal +
                    " de los votos"
            );
            System.out.println("2) Candidato "+(indexSegundo+1) + " con %" +
                    (double)(votosPorCandidatos[indexSegundo]*100)/votosTotal+
                    " de los votos"
            );
        }
    }
}
