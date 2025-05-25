package logica;

import java.util.Random;

public class GeneradorSudoku {
    private boolean[][] celdasFijas = new boolean[9][9];

    public int[][] generar(String dificultad) {
        int vacias;

        // Control simple de dificultad (por defecto medio)
        if ("facil".equalsIgnoreCase(dificultad)) {
            vacias = 30;
        } else if ("medio".equalsIgnoreCase(dificultad)) {
            vacias = 40;
        } else if ("dificil".equalsIgnoreCase(dificultad)) {
            vacias = 50;
        } else {
            vacias = 40; // Default medio si no reconoce
        }

        int[][] tablero = new int[9][9];
        backtrack(tablero, 0, 0);

        Random rand = new Random();
        while (vacias > 0) {
            int fila = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (tablero[fila][col] != 0) {
                tablero[fila][col] = 0;
                celdasFijas[fila][col] = false;
                vacias--;
            }
        }

        // Marca como fijas las celdas no vacías
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                celdasFijas[i][j] = tablero[i][j] != 0;
            }
        }

        return tablero;
    }

    public boolean[][] getCeldasFijas() {
        return celdasFijas;
    }

    private boolean backtrack(int[][] tablero, int fila, int col) {
        if (fila == 9) return true;
        if (col == 9) return backtrack(tablero, fila + 1, 0);

        Random rand = new Random();
        int[] numeros = rand.ints(1, 10).distinct().limit(9).toArray();

        for (int num : numeros) {
            if (esValido(tablero, fila, col, num)) {
                tablero[fila][col] = num;
                if (backtrack(tablero, fila, col + 1)) return true;
                tablero[fila][col] = 0;
            }
        }
        return false;
    }

    private boolean esValido(int[][] tablero, int fila, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num || tablero[i][col] == num)
                return false;
        }

        int inicioFila = (fila / 3) * 3;
        int inicioCol = (col / 3) * 3;
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioCol; j < inicioCol + 3; j++) {
                if (tablero[i][j] == num)
                    return false;
            }
        }
        return true;
    }
}
