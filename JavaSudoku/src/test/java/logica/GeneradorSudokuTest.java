package logica;

import logica.GeneradorSudoku;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorSudokuTest {

    @Test
    public void testGenerarTableroFacil() {
        // Creamos un generador de Sudoku y pedimos un tablero con dificultad fácil
        GeneradorSudoku generador = new GeneradorSudoku();
        int[][] tablero = generador.generar("facil");

        // Verificamos que el tablero no sea nulo (que se haya creado correctamente)
        assertNotNull(tablero);

        // Contamos cuántas casillas están vacías (tienen valor 0)
        int vacias = 0;
        for (int[] fila : tablero) {
            for (int celda : fila) {
                if (celda == 0) vacias++;
            }
        }

        // Revisamos que no haya más de 30 casillas vacías en modo fácil
        assertTrue(vacias <= 30);
    }

    @Test
    public void testGenerarTableroMedio() {
        // Pedimos un tablero con dificultad media
        GeneradorSudoku generador = new GeneradorSudoku();
        int[][] tablero = generador.generar("medio");

        // Aseguramos que el tablero se haya creado
        assertNotNull(tablero);

        // Contamos cuántas casillas están vacías
        int vacias = 0;
        for (int[] fila : tablero) {
            for (int celda : fila) {
                if (celda == 0) vacias++;
            }
        }

        // Verificamos que no haya más de 40 casillas vacías en modo medio
        assertTrue(vacias <= 40);
    }

    @Test
    public void testGenerarTableroDificil() {
        // Pedimos un tablero con dificultad difícil
        GeneradorSudoku generador = new GeneradorSudoku();
        int[][] tablero = generador.generar("dificil");

        // Aseguramos que el tablero se haya creado
        assertNotNull(tablero);

        // Contamos las casillas vacías
        int vacias = 0;
        for (int[] fila : tablero) {
            for (int celda : fila) {
                if (celda == 0) vacias++;
            }
        }

        // Verificamos que no haya más de 50 casillas vacías en modo difícil
        assertTrue(vacias <= 50);
    }
}
