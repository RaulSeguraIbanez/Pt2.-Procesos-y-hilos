package Ejercicio1_factoriales;

class hilo extends Thread {

    private int numHilo;
    private int numFactor;

    public Hilo(int posicion) {
        this.numHilo = posicion;
    }

    @Override
    public void run() {
        System.out.println("Ingrese un número para que factorice el hilo " + this.numHilo);
        this.numFactor = main.s1.nextInt();
        int factorizado = calcularFactorial(this.numFactor);
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