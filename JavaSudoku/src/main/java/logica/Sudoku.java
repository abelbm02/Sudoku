package logica;

public class Sudoku {
    private int[][] tablero = new int[9][9];
    private boolean[][] celdasFijas = new boolean[9][9];

    public void generarTablero(String dificultad) {
        GeneradorSudoku generador = new GeneradorSudoku();
        tablero = generador.generar(dificultad);
        celdasFijas = generador.getCeldasFijas();
    }

    public boolean esMovimientoValido(int fila, int columna, int valor) {
        if (celdasFijas[fila][columna] || valor < 1 || valor > 9) return false;

        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == valor || tablero[i][columna] == valor)
                return false;
        }

        int inicioFila = (fila / 3) * 3;
        int inicioCol = (columna / 3) * 3;
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioCol; j < inicioCol + 3; j++) {
                if (tablero[i][j] == valor)
                    return false;
            }
        }

        return true;
    }

    public boolean colocarNumero(int fila, int columna, int valor) {
        if (!celdasFijas[fila][columna] && esMovimientoValido(fila, columna, valor)) {
            tablero[fila][columna] = valor;
            return true;
        }
        return false;
    }

    // Ya no se usa para comprobar, queda para otro uso si quieres
    public boolean estaResuelto() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                int valor = tablero[fila][col];
                if (valor == 0) return false; // Está incompleto
                tablero[fila][col] = 0; // Temporalmente vacío para validación
                if (!esMovimientoValido(fila, col, valor)) {
                    tablero[fila][col] = valor;
                    return false; // Movimiento inválido
                }
                tablero[fila][col] = valor;
            }
        }
        return true; // Todo correcto
    }

    // Nuevo método para validar que el tablero esté completo y sin errores
    public boolean tableroCorrecto() {
        // Validar filas
        for (int fila = 0; fila < 9; fila++) {
            boolean[] checkFila = new boolean[10];
            for (int col = 0; col < 9; col++) {
                int val = tablero[fila][col];
                if (val == 0) return false; // incompleto
                if (checkFila[val]) return false; // valor repetido en fila
                checkFila[val] = true;
            }
        }

        // Validar columnas
        for (int col = 0; col < 9; col++) {
            boolean[] checkCol = new boolean[10];
            for (int fila = 0; fila < 9; fila++) {
                int val = tablero[fila][col];
                if (val == 0) return false;
                if (checkCol[val]) return false; // valor repetido en columna
                checkCol[val] = true;
            }
        }

        // Validar bloques 3x3
        for (int bloqueFila = 0; bloqueFila < 3; bloqueFila++) {
            for (int bloqueCol = 0; bloqueCol < 3; bloqueCol++) {
                boolean[] checkBloque = new boolean[10];
                for (int fila = bloqueFila * 3; fila < bloqueFila * 3 + 3; fila++) {
                    for (int col = bloqueCol * 3; col < bloqueCol * 3 + 3; col++) {
                        int val = tablero[fila][col];
                        if (val == 0) return false;
                        if (checkBloque[val]) return false; // valor repetido en bloque
                        checkBloque[val] = true;
                    }
                }
            }
        }

        return true; // tablero correcto y completo
    }

    public boolean resolverSudoku() {
        return resolver(0, 0);
    }

    private boolean resolver(int fila, int col) {
        if (fila == 9) return true;
        if (col == 9) return resolver(fila + 1, 0);
        if (celdasFijas[fila][col]) return resolver(fila, col + 1);

        for (int num = 1; num <= 9; num++) {
            if (esMovimientoValido(fila, col, num)) {
                tablero[fila][col] = num;
                if (resolver(fila, col + 1)) return true;
                tablero[fila][col] = 0; // retroceder
            }
        }
        return false;
    }

    public void mostrarTablero() {
        for (int[] fila : tablero) {
            for (int valor : fila) {
                System.out.print((valor == 0 ? "." : valor) + " ");
            }
            System.out.println();
        }
    }

    public void actualizarTablero(int[][] nuevoTablero) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                // Solo actualizamos si no es celda fija
                if (!celdasFijas[fila][col]) {
                    tablero[fila][col] = nuevoTablero[fila][col];
                }
            }
        }
    }

    public void forzarNumero(int fila, int columna, int valor) {
        if (!celdasFijas[fila][columna]) {
            tablero[fila][columna] = valor;
        }
    }

    public int[][] getTablero() {
        return tablero;
    }

    public boolean[][] getCeldasFijas() {
        return celdasFijas;
    }
}
