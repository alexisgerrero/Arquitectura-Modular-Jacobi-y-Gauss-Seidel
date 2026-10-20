// Módulo 3: EJECUCIÓN (inicializa datos, llama a los métodos y despliega resultados)
package Ecuaciones_lineales;

import Ecuaciones_lineales.Gauss_Seidel.GaussSeidelMetodo;
import Ecuaciones_lineales.JACOBI.MetodoJacobi;
import Ecuaciones_lineales.Sistema.SistemaEcuaciones;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        double[][] A = SistemaEcuaciones.A;
        double[] b = SistemaEcuaciones.B;
        double[] x0 = SistemaEcuaciones.X_INICIAL;
        double tol = SistemaEcuaciones.TOLERANCIA;
        int max = SistemaEcuaciones.MAX_ITER;

        String cabecera = "Iter|          x          y          z | Error";

        System.out.println("=== METODO DE JACOBI ===");
        System.out.println(cabecera);
        double[] xJ = MetodoJacobi.jacobi(A, b, x0, tol, max);
        int itJ = MetodoJacobi.iteraciones;
        resultado("Jacobi", xJ, itJ);

        System.out.println("\n=== METODO DE GAUSS-SEIDEL ===");
        System.out.println(cabecera);
        double[] xG = GaussSeidelMetodo.gaussSeidel(A, b, x0, tol, max);
        int itG = GaussSeidelMetodo.iteraciones;
        resultado("Gauss-Seidel", xG, itG);

        System.out.println("\n=== COMPARACION ===");
        System.out.println("Iteraciones Jacobi       : " + itJ);
        System.out.println("Iteraciones Gauss-Seidel : " + itG);
    }

    private static void resultado(String nombre, double[] x, int iter) {
        System.out.println(String.format(Locale.US,
            "Solucion %s: x = %.6f, y = %.6f, z = %.6f  (%d iteraciones)",
            nombre, x[0], x[1], x[2], iter));
    }
}
