package Ejercicio1_factoriales;

class Hilo extends Thread {
    private int numHilo;

    public Hilo(int posicion) {
        this.numHilo = posicion;
    }

    @Override
    public void run() {
        int numFactor;
        synchronized (Main.scanner) {
            System.out.println("Ingrese un número para que factorice el hilo " + this.numHilo);
            numFactor = Main.scanner.nextInt();
        }

        int factorizado = calcularFactorial(numFactor);
        System.out.println("El número factorizado por el hilo " + this.numHilo + " es: " + factorizado);
    }

    private int calcularFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            int resultado = 1;
            for (int i = 2; i <= n; i++) {
                resultado *= i;
            }
            return resultado;
        }
    }
}