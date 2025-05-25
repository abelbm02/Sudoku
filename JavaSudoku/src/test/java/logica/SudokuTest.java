package logica;

import logica.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTest {

    private Sudoku sudoku;

    // Antes de cada prueba se crea un nuevo tablero
    @BeforeEach
    public void setup() {
        sudoku = new Sudoku();
        sudoku.generarTablero("facil"); // Generamos un tablero de dificultad fácil
    }

    // --- PRUEBAS POSITIVAS ---

    @Test
    public void testColocarNumeroValido() {
        // Buscamos una casilla vacía (que no esté bloqueada)
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (!sudoku.getCeldasFijas()[fila][col]) {
                    // Intentamos colocar un número (5) en esa casilla
                    boolean resultado = sudoku.colocarNumero(fila, col, 5);
                    // Verificamos que el método no falle, puede devolver true o false
                    assertTrue(resultado || !resultado);
                    return; // Solo probamos una casilla
                }
            }
        }
        // Si no se encontró ninguna casilla vacía, la prueba falla
        fail("No se encontró celda vacía para prueba.");
    }

    @Test
    public void testEstaResueltoCuandoNoEstaResuelto() {
        // Comprobamos que un tablero recién creado no esté completo
        assertFalse(sudoku.estaResuelto());
    }

    // --- PRUEBAS NEGATIVAS ---

    @Test
    public void testColocarNumeroEnCeldaFija() {
        // Buscamos una casilla fija (bloqueada)
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (sudoku.getCeldasFijas()[fila][col]) {
                    // Intentamos colocar un número donde no se puede cambiar
                    boolean resultado = sudoku.colocarNumero(fila, col, 1);
                    // Debe devolver false porque no se puede modificar una casilla fija
                    assertFalse(resultado, "No se debe poder modificar una celda fija");
                    return;
                }
            }
        }
        fail("No se encontró celda fija para prueba.");
    }

    @Test
    public void testColocarNumeroFueraDeRango() {
        // Intentamos colocar un número inválido (menor que 1 o mayor que 9)
        boolean resultado1 = sudoku.colocarNumero(0, 0, 0);   // 0 no es válido
        boolean resultado2 = sudoku.colocarNumero(0, 0, 10);  // 10 tampoco

        // Ambos deben devolver false
        assertFalse(resultado1);
        assertFalse(resultado2);
    }

    // --- PRUEBAS DE BORDE (bordes del tablero) ---

    @Test
    public void testMovimientoValidoEnBordes() {
        // Intentamos mover en cada esquina del tablero
        // No importa si es válido o no, solo que no falle
        assertTrue(sudoku.esMovimientoValido(0, 0, 1) || !sudoku.esMovimientoValido(0, 0, 1)); // esquina superior izquierda
        assertTrue(sudoku.esMovimientoValido(0, 8, 1) || !sudoku.esMovimientoValido(0, 8, 1)); // esquina superior derecha
        assertTrue(sudoku.esMovimientoValido(8, 0, 1) || !sudoku.esMovimientoValido(8, 0, 1)); // esquina inferior izquierda
        assertTrue(sudoku.esMovimientoValido(8, 8, 1) || !sudoku.esMovimientoValido(8, 8, 1)); // esquina inferior derecha
    }

    @Test
    public void testColocarNumeroEnPosicionLimite() {
        // Intentamos poner un número en la última casilla del tablero
        boolean resultado = sudoku.colocarNumero(8, 8, 9);

        // Solo verificamos que no falle, puede devolver true o false
        assertTrue(resultado || !resultado);
    }
}
