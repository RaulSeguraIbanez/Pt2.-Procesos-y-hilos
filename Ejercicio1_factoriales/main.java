package Ejercicio1_factoriales;
import java.util.*;


public class main {
	public static Scanner s1 = new Scanner(System.in);

	public static void main(String[] args) {
        
        int numeroAFactorizar;
        
        System.out.println("Ingrese la cantidad de números a factorizar:");
        int numProcesos = main.s1.nextInt();
        
        hilo[] factorizaciones = new hilo[numProcesos];
        
        for (int i = 0; i < numProcesos; i++) {
        	factorizaciones[i] = new hilo(i + 1);
        	factorizaciones[i].start();
        }
	}
}
