// Declara el paquete correspondiente a esta clase
// Módulo 2.2 LÓGICA DEL MÉTODO DE GAUSS-SEIDEL
package Ecuaciones_lineales.Gauss_Seidel;

// Importa la función calcularError reutilizando el método de Jacobi
import static Ecuaciones_lineales.JACOBI.MetodoJacobi.calcularError;

import java.util.Locale;

public class GaussSeidelMetodo {

    public static int iteraciones = 0;
    public static boolean mostrarIteraciones = true;

    public static double[] gaussSeidel(double[][] A, double[] b,
                                       double[] xInicial, double tol, int maxIter) {
        int n = xInicial.length;
        double[] x = xInicial.clone();
        // Un solo vector que evoluciona al instante
        iteraciones = 0;

        for (int iter = 0; iter < maxIter; iter++) {
            double[] xAnt = x.clone();
            // Respaldamos el estado anterior para el error

            for (int i = 0; i < n; i++) {
                double suma = b[i];
                // Arranca con el término independiente b_i
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        suma -= A[i][j] * x[j];   // x[j] ya puede estar actualizado (j < i)
                    }
                }
                x[i] = suma / A[i][i];            // Se actualiza al instante
            }

            double error = calcularError(xAnt, x);
            iteraciones = iter + 1;
            if (mostrarIteraciones) imprimir(iteraciones, x, error);

            if (error < tol) { return x; }        // Criterio de paro
        }
        return x;
    }

    private static void imprimir(int k, double[] x, double error) {
        StringBuilder sb = new StringBuilder(String.format(Locale.US, "%3d | ", k));
        for (double v : x) sb.append(String.format(Locale.US, "%10.6f ", v));
        sb.append(String.format(Locale.US, "| %.6f", error));
        System.out.println(sb);
    }
}
