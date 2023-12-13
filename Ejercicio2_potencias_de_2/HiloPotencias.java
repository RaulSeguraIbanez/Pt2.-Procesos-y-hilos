package Ejercicio2_potencias_de_2;

import java.util.Scanner;
import java.util.concurrent.*;

public class HiloPotencias extends Thread {
    private int exponente;
    private long resultado;

    // Constructor
    public HiloPotencias(int exponente) {
        this.exponente = exponente;
    }

    // Método run
    public void run() {
        resultado = (long) Math.pow(2, exponente);
    }

    // Getter para resultado
    public long getResultado() {
        return resultado;
    }

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


