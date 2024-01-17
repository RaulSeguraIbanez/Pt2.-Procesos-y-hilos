package Ejercicio3_Desastre;
import java.util.Random;
import java.util.Scanner;

public class Buscador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese tamaño, valor y número de hilos bajo el formato de '$buscador (tamaño) (valor a bsucar) (numero de hilos): ");
        
        String buscacion = scanner.nextLine();
        int tamanoVector = 0;
        int valorBuscado = 0;
        int numHilos = 0;
        String vectorEscrito ="";
        String tamanoEscrito = "";
        String numHiloEscrito = "";
        int cambio = 0;
        
        for(int x = 0; x < buscacion.length(); x++) {
        	
        	if(buscacion.charAt(x) == ' ') {
        		cambio++;
        		
        	} else if(buscacion.charAt(x) != ' ' || Character.isDigit(buscacion.charAt(x))){
        		
        		switch (cambio) {
                case 1:
                	vectorEscrito = vectorEscrito + buscacion.charAt(x);
                	break;
                case 2:
                	tamanoEscrito = tamanoEscrito + buscacion.charAt(x);
                	break;	
                case 3:
                	numHiloEscrito = numHiloEscrito + buscacion.charAt(x);
                	break;	
        	}
        
        }
        	tamanoVector = Integer.parseInt(vectorEscrito);
        	valorBuscado = Integer.parseInt(tamanoEscrito);
        	numHilos = Integer.parseInt(numHiloEscrito);
        
        if (tamanoVector <= 0 || numHilos <= 0) {
            System.out.println("El tamaño del vector y el número de hilos deben ser mayores que cero.");
            System.exit(1);
        }

        int[] vector = generarVectorAleatorio(tamanoVector);

        ResultadoBusqueda resultado = new ResultadoBusqueda();

        int elementosPorHilo = tamanoVector / numHilos;
        int posicionesRestantes = tamanoVector % numHilos;

        Thread[] hilos = new Thread[numHilos];

        // Crear hilos
        for (int i = 0; i < numHilos; i++) {
            int inicio = i * elementosPorHilo;
            int fin = inicio + elementosPorHilo - 1;

            if (i == numHilos - 1) {
                // Último hilo maneja las posiciones restantes
                fin += posicionesRestantes;
            }

            hilos[i] = new BuscadorThread(vector, valorBuscado, inicio, fin, resultado);
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar resultados
        if (resultado.isEncontrado()) {
            System.out.println("El valor " + valorBuscado + " se encontró en la posición " + resultado.getPosicion());
        } else {
            System.out.println("El valor " + valorBuscado + " no se encontró en el vector.");
        }}
    }

    private static int[] generarVectorAleatorio(int tamano) {
        int[] vector = new int[tamano];
        Random random = new Random();

        for (int i = 0; i < tamano; i++) {
            vector[i] = random.nextInt(1000); // Puedes ajustar el rango según tus necesidades
        }

        return vector;
    }
}