package principal;

import logica.Sudoku;

public class Principal {
    public static void main(String[] args) {
        Sudoku juego = new Sudoku();
        juego.generarTablero("facil");
        juego.mostrarTablero();
    }
}
