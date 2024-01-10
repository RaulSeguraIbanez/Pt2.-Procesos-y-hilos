package Ejercicio1_factoriales;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Ingrese la cantidad de números a factorizar:");
        int numProcesos = scanner.nextInt();

        Hilo[] factorizaciones = new Hilo[numProcesos];

        for (int i = 0; i < numProcesos; i++) {
            factorizaciones[i] = new Hilo(i + 1);
            factorizaciones[i].start();
        }
    }
}