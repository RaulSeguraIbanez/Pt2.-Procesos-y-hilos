package Ejercicio3_Desastre;
import java.util.Random;
import java.util.Random;
import java.util.Scanner;

class BuscadorThread extends Thread {
    private int[] vector;
    private int valorBuscado;
    private int inicio;
    private int fin;
    private ResultadoBusqueda resultado;

    public BuscadorThread(int[] vector, int valorBuscado, int inicio, int fin, ResultadoBusqueda resultado) {
        this.vector = vector;
        this.valorBuscado = valorBuscado;
        this.inicio = inicio;
        this.fin = fin;
        this.resultado = resultado;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            if (vector[i] == valorBuscado) {
                resultado.setPosicion(i);
                resultado.setEncontrado(true);
                break;
            }
        }
    }
}

class ResultadoBusqueda {
    private boolean encontrado;
    private int posicion;

    public ResultadoBusqueda() {
        this.encontrado = false;
        this.posicion = -1;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
