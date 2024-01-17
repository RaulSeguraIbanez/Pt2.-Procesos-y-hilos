package Ejercicio3_Desastre;
import java.util.Random;
import java.util.Scanner;

public class Buscador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tamanoVector = 0;
        int valorBuscado = 0;
        int numHilos = 0;
        String vectorEscrito = "";
        String valorEscrito = "";
        String numHiloEscrito = "";
        int cambio = 0;
        
        System.out.print("Ingrese tamaño, valor y número de hilos bajo el formato de '$buscador (tamaño) (valor a buscar) (numero de hilos): ");

        String buscacion = scanner.nextLine();

        // Verificar si la cadena comienza con '$buscador'
        if (!buscacion.startsWith("$buscador ")) {
            System.out.println("Formato no válido. Asegúrate de comenzar con '$buscador ' y seguir con los parámetros.");
            System.exit(1);
        }

        // Eliminar "$buscador " al principio de la cadena
        buscacion = buscacion.substring("$buscador ".length());

        // Dividir la cadena en partes usando el espacio como delimitador
        String[] parametros = buscacion.split(" ");

        // Verificar que haya al menos tres parámetros
        if (parametros.length < 3) {
            System.out.println("Formato no válido. Deben proporcionarse al menos tres parámetros.");
            System.exit(1);
        }

        // Obtener los valores de los parámetros
        try {
             tamanoVector = Integer.parseInt(parametros[0]);
             valorBuscado = Integer.parseInt(parametros[1]);
             numHilos = Integer.parseInt(parametros[2]);

            // Resto del código usando tamanoVector, valorBuscado y numHilos...
        } catch (NumberFormatException e) {
            System.out.println("Formato no válido. Asegúrate de que los parámetros sean números enteros válidos.");
            System.exit(1);
        }
        
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
        }
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