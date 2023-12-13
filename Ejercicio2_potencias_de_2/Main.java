package Ejercicio2_potencias_de_2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de potencias de 2 que quieres calcular:");
        int numProcesos = scanner.nextInt();
        scanner.close();

        HiloPotencias[] hilos = new HiloPotencias[numProcesos];
        for (int i = 0; i < numProcesos; i++) {
            hilos[i] = new HiloPotencias(i);
            hilos[i].start();
        }

        for (int i = 0; i < numProcesos; i++) {
            try {
                hilos[i].join();
                System.out.println("2^" + i + " = " + hilos[i].getResultado());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

