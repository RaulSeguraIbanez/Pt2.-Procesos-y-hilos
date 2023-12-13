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
}


