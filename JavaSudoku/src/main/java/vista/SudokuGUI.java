package vista;

import logica.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI extends JFrame {
    private JTextField[][] celdas = new JTextField[9][9];
    private Sudoku sudoku;
    private String dificultadSeleccionada;
    private JPanel panelTablero;

    public SudokuGUI() {
        setTitle("Sudoku");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        seleccionarDificultad();
        sudoku = new Sudoku();
        sudoku.generarTablero(dificultadSeleccionada);

        construirInterfaz();
    }

    private void seleccionarDificultad() {
        String[] opciones = {"facil", "medio", "dificil"};
        dificultadSeleccionada = (String) JOptionPane.showInputDialog(
                this, "Selecciona dificultad", "Dificultad",
                JOptionPane.QUESTION_MESSAGE, null, opciones, "facil");

        if (dificultadSeleccionada == null) {
            dificultadSeleccionada = "facil";
        }
    }

    private void construirInterfaz() {
        if (panelTablero != null) remove(panelTablero);
        panelTablero = new JPanel(new GridLayout(9, 9));
        Font fuente = new Font("SansSerif", Font.BOLD, 26);

        int[][] tablero = sudoku.getTablero();
        boolean[][] fijas = sudoku.getCeldasFijas();

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JTextField celda = new JTextField();
                celda.setHorizontalAlignment(JTextField.CENTER);
                celda.setFont(fuente);
                celda.setCaretColor(Color.WHITE);

                if (tablero[fila][col] != 0) {
                    celda.setText(String.valueOf(tablero[fila][col]));
                    celda.setEditable(false);
                    celda.setBackground(new Color(0, 100, 0)); // Verde oscuro
                    celda.setForeground(Color.WHITE);
                } else {
                    celda.setBackground(new Color(120, 200, 120)); // Verde claro
                    celda.setForeground(Color.WHITE);
                    int f = fila, c = col;
                    celda.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String texto = celda.getText();
                            if (texto.matches("[1-9]")) {
                                int valor = Integer.parseInt(texto);
                                sudoku.colocarNumero(f, c, valor);
                                celda.setForeground(Color.WHITE);
                            } else if (!texto.isEmpty()) {
                                celda.setText("");
                            }
                        }
                    });
                }

                // Bordes más gruesos para bloques 3x3
                int top = (fila % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = (fila == 8) ? 2 : 1;
                int right = (col == 8) ? 2 : 1;
                celda.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                celdas[fila][col] = celda;
                panelTablero.add(celda);
            }
        }

        add(panelTablero, BorderLayout.CENTER);
        construirBotones();
        setVisible(true);
    }

    private void construirBotones() {
        JPanel panelBotones = new JPanel();

        JButton btnComprobar = new JButton("Comprobar");
        JButton btnReiniciar = new JButton("Reiniciar");

        btnComprobar.addActionListener(e -> {
            int[][] tableroDesdeGUI = leerTableroDesdeGUI();
            sudoku.actualizarTablero(tableroDesdeGUI);

            if (sudoku.tableroCorrecto()) {
                JOptionPane.showMessageDialog(this, "¡Sudoku completado correctamente!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El Sudoku aún no está completo o tiene errores.", "Incompleto", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnReiniciar.addActionListener(e -> {
            seleccionarDificultad();
            sudoku = new Sudoku();
            sudoku.generarTablero(dificultadSeleccionada);
            construirInterfaz();
            revalidate();
            repaint();
        });

        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(e -> {
            if (sudoku.resolverSudoku()) {
                actualizarTablero();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo resolver el Sudoku.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelBotones.add(btnComprobar);
        panelBotones.add(btnReiniciar);
        panelBotones.add(btnResolver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private int[][] leerTableroDesdeGUI() {
        int[][] tablero = new int[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                String texto = celdas[fila][col].getText();
                if (texto.matches("[1-9]")) {
                    tablero[fila][col] = Integer.parseInt(texto);
                } else {
                    tablero[fila][col] = 0;
                }
            }
        }
        return tablero;
    }

    private void actualizarTablero() {
        int[][] tablero = sudoku.getTablero();
        boolean[][] celdasFijas = sudoku.getCeldasFijas();

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[fila][col] != 0) {
                    celdas[fila][col].setText(String.valueOf(tablero[fila][col]));
                    celdas[fila][col].setForeground(Color.WHITE);
                    celdas[fila][col].setEditable(!celdasFijas[fila][col]);
                    celdas[fila][col].setBackground(celdasFijas[fila][col] ? new Color(0, 100, 0) : new Color(120, 200, 120));
                } else {
                    celdas[fila][col].setText("");
                    celdas[fila][col].setEditable(true);
                    celdas[fila][col].setBackground(new Color(120, 200, 120));
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGUI::new);
    }
}
