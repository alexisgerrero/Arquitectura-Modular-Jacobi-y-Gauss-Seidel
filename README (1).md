# Métodos Iterativos: Jacobi y Gauss-Seidel en Java

Práctica de Métodos Numéricos (Grupo A), Instituto Tecnológico Superior de Xalapa.

Este proyecto resuelve sistemas de ecuaciones lineales con dos métodos iterativos, Jacobi y Gauss-Seidel, usando una arquitectura modular: los datos, la lógica de cada método y la ejecución viven en archivos separados.

## Sistema que se resuelve

    5x +  y +  z = 7
     x + 4y +  z = 6
     x + 2y + 5z = 8

El vector inicial es X(0) = [0, 0, 0] y la tolerancia es 0.0001. La solución exacta es x = y = z = 1. El sistema es estrictamente diagonal dominante (5 > 2, 4 > 2, 5 > 3), condición que asegura que ambos métodos convergen.

## Cómo funciona cada módulo

### 1. Sistema/SistemaEcuaciones.java (definición del sistema)

Este módulo solo guarda datos y no contiene ninguna lógica. Declara la matriz de coeficientes `A`, el vector de términos independientes `B`, el vector inicial `X_INICIAL`, la tolerancia `TOLERANCIA` y el número máximo de iteraciones `MAX_ITER`. Todo se declara como constante pública estática, así cualquier otra clase puede leerlo sin crear objetos.

La ventaja de aislarlo es que para resolver otro sistema solo se edita este archivo. Los métodos y el `Main` no se tocan.

### 2. JACOBI/MetodoJacobi.java (lógica del método de Jacobi)

Contiene el método `jacobi(A, b, xInicial, tol, maxIter)`. En cada iteración calcula un vector completamente nuevo (`xNuevo`) a partir del vector de la iteración anterior (`x`). Para cada ecuación i, parte del término independiente `b[i]`, resta los productos `A[i][j] * x[j]` de todas las columnas distintas de i, y divide entre el elemento diagonal `A[i][i]`.

La característica clave es que Jacobi nunca usa valores recién calculados dentro de la misma iteración. Todas las componentes se calculan con los datos de la iteración previa, y solo al terminar se reemplaza `x` por `xNuevo`.

Este módulo también define `calcularError(xAnt, xNuevo)`, que devuelve el mayor valor absoluto de las diferencias entre dos vectores consecutivos (norma infinito). El método se detiene cuando ese error es menor que la tolerancia o cuando se alcanza el máximo de iteraciones. Además, guarda el número de iteraciones realizadas en la variable `iteraciones` y puede imprimir cada paso en consola con la bandera `mostrarIteraciones`.

### 3. Gauss_Seidel/GaussSeidelMetodo.java (lógica del método de Gauss-Seidel)

Contiene el método `gaussSeidel(A, b, xInicial, tol, maxIter)`. La fórmula de despeje es la misma que en Jacobi, pero con una diferencia importante: usa un solo vector `x` que se actualiza al instante. Cuando se calcula `x[i]`, las componentes anteriores (j < i) ya tienen su valor nuevo de esta misma iteración, y las posteriores (j > i) todavía conservan el valor viejo.

Como `x` cambia sobre la marcha, antes de empezar cada iteración se guarda una copia en `xAnt`. Esa copia sirve para calcular el error al final de la pasada.

Este módulo no vuelve a escribir `calcularError`. La reutiliza con un import estático de `MetodoJacobi`, lo que evita código duplicado y deja una sola definición del criterio de paro para ambos métodos. Igual que Jacobi, registra el número de iteraciones y puede imprimir cada paso.

### 4. Main.java (ejecución)

Es la única clase con `main`. Lee los datos desde `SistemaEcuaciones`, ejecuta primero Jacobi y después Gauss-Seidel, y para cada uno imprime la tabla de iteraciones (valores de x, y, z y error), la solución final y el número de iteraciones. Al final muestra una comparación de cuántas iteraciones necesitó cada método.

`Main` no conoce los detalles internos de los métodos y los métodos no conocen el sistema concreto. Eso es lo que hace modular la arquitectura.

## Criterio de paro

El método termina cuando

    máx |x_nuevo[i] - x_anterior[i]| < 0.0001

o cuando se llega a `MAX_ITER` iteraciones (100 por defecto).

## Resultados esperados

Con la tolerancia indicada, Jacobi converge en 16 iteraciones y Gauss-Seidel en 6, ambos con la solución x ≈ y ≈ z ≈ 1. Gauss-Seidel necesita menos iteraciones porque aprovecha de inmediato los valores que acaba de calcular.
