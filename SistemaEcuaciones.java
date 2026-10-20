// Módulo 1: DEFINICIÓN DEL SISTEMA (solo datos, sin lógica de métodos)
package Ecuaciones_lineales.Sistema;

public class SistemaEcuaciones {

    // Matriz de coeficientes A
    public static final double[][] A = {
        {5, 1, 1},   //  5x +  y +  z = 7
        {1, 4, 1},   //   x + 4y +  z = 6
        {1, 2, 5}    //   x + 2y + 5z = 8
    };

    // Vector de términos independientes b
    public static final double[] B = {7, 6, 8};

    // Vector inicial X(0) = [0, 0, 0]^T
    public static final double[] X_INICIAL = {0, 0, 0};

    // Tolerancia del error y número máximo de iteraciones
    public static final double TOLERANCIA = 0.0001;
    public static final int MAX_ITER = 100;
}
