package principal;

import logica.Sudoku;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrincipalTest {

    @Test
    public void testGenerarTableroFacilSinErrores() {
        // Creamos un nuevo juego de Sudoku
        Sudoku juego = new Sudoku();

        // Comprobamos que al generar un tablero con dificultad "fácil" no ocurra ningún error
        // Esta prueba asegura que el método funciona correctamente y no se bloquea
        assertDoesNotThrow(() -> juego.generarTablero("facil"));
    }
}
