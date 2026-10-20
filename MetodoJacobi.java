// Módulo 2.1 LÓGICA DEL MÉTODO DE JACOBI
package Ecuaciones_lineales.JACOBI;

import java.util.Locale;

public class MetodoJacobi {

    // Número de iteraciones que realizó la última ejecución
    public static int iteraciones = 0;
    // Si es true, imprime cada iteración en consola
    public static boolean mostrarIteraciones = true;

    public static double[] jacobi(double[][] A, double[] b,
                                  double[] xInicial, double tol, int maxIter) {
        int n = xInicial.length;
        double[] x = xInicial.clone();      // Aproximación actual (iteración k)
        iteraciones = 0;

        for (int iter = 0; iter < maxIter; iter++) {
            double[] xNuevo = new double[n]; // Vector nuevo (iteración k+1)

            for (int i = 0; i < n; i++) {
                double suma = b[i];          // Arranca con el término independiente b_i
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        suma -= A[i][j] * x[j];   // Usa SOLO valores de la iteración anterior
                    }
                }
                xNuevo[i] = suma / A[i][i];       // Despeja x_i
            }

            double error = calcularError(x, xNuevo);
            iteraciones = iter + 1;
            if (mostrarIteraciones) imprimir(iteraciones, xNuevo, error);

            x = xNuevo;                      // Ahora el nuevo vector es el actual
            if (error < tol) {
                return x;                    // Convergió
            }
        }
        return x;
    }

    // Error = norma infinito de la diferencia: max |x_nuevo[i] - x_ant[i]|
    public static double calcularError(double[] xAnt, double[] xNuevo) {
        double max = 0;
        for (int i = 0; i < xAnt.length; i++) {
            max = Math.max(max, Math.abs(xNuevo[i] - xAnt[i]));
        }
        return max;
    }

    private static void imprimir(int k, double[] x, double error) {
        StringBuilder sb = new StringBuilder(String.format(Locale.US, "%3d | ", k));
        for (double v : x) sb.append(String.format(Locale.US, "%10.6f ", v));
        sb.append(String.format(Locale.US, "| %.6f", error));
        System.out.println(sb);
    }
}
